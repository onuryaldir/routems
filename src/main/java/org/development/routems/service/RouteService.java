package org.development.routems.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.development.routems.configuration.AIServiceConfig;
import org.development.routems.entity.RouteDetail;
import org.development.routems.entity.RouteEntity;
import org.development.routems.model.CreateRouteRequest;
import org.development.routems.model.Route;
import org.development.routems.model.RouteResponse;
import org.development.routems.model.TrainModelRequest;
import org.development.routems.repository.RouteRepository;
import org.development.routems.statics.RequestStatus;
import org.development.routems.statics.Status;
import org.development.routems.util.RouteMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.RouteMatcher;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;
    private final AiRequestService aiRequestService;

    public RouteResponse getActiveRoute(final Integer customerId){

        RouteEntity route = routeRepository.getActiveRoute(customerId, Status.PROCESSING.toString());
        return new RouteResponse(RequestStatus.SUCCESS,RouteMapper.toModel(route));

    }

    public Route createRoute(CreateRouteRequest createRouteRequest) {
        try {
            ResponseEntity<String> response = aiRequestService.predict(createRouteRequest);
            if(response.getStatusCode() == HttpStatus.OK) {
              List<RouteDetail> routeDetailList = RouteMapper.parseRouteDetail(response.getBody());
              Route routeToBeSave = new Route();
              routeToBeSave.setRoute(routeDetailList);
              routeToBeSave.setStatus(Status.CREATED.toString());
              routeToBeSave.setCustomerId(createRouteRequest.getCustomerId());
              routeToBeSave.setCreatedAt(new Date());
             RouteEntity routeEntity = routeRepository.save(RouteMapper.toEntity(routeToBeSave));
              return RouteMapper.toModel(routeEntity);
            }

        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return null;
    }

    public RouteResponse updateRouteStatus(Integer routeId, Status status) {

        Optional<RouteEntity> route = routeRepository.findById(routeId);
        route.ifPresentOrElse(
                existingRoute ->{
                    existingRoute.setStatus(status.toString());
                    routeRepository.save(existingRoute);
    }, () -> {
            throw new Error("no route available");
        });

         return new RouteResponse(RequestStatus.SUCCESS,RouteMapper.toModel(route.get()));
    }

    public ResponseEntity<String> trainModel(TrainModelRequest trainModelRequest) {

        return aiRequestService.trainModel(trainModelRequest);
    }
}

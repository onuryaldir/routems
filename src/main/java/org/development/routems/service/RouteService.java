package org.development.routems.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.development.routems.entity.RouteEntity;
import org.development.routems.model.CreateRouteRequest;
import org.development.routems.model.RouteResponse;
import org.development.routems.repository.RouteRepository;
import org.development.routems.statics.RequestStatus;
import org.development.routems.statics.Status;
import org.development.routems.util.RouteMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RouteService {

    private final RouteMapper routeMapper;
    private final RouteRepository routeRepository;

    public RouteResponse getActiveRoute(final Integer customerId){

        RouteEntity route = routeRepository.getActiveRoute(customerId, Status.PROCESSING.toString());
        return new RouteResponse(RequestStatus.SUCCESS,RouteMapper.mapToRoute(route));

    }

    public void createRoute(CreateRouteRequest createRouteRequest) {

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

         return new RouteResponse(RequestStatus.SUCCESS,RouteMapper.mapToRoute(route.get()));
    }
}

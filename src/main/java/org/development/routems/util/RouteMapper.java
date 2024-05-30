package org.development.routems.util;

import lombok.RequiredArgsConstructor;
import org.development.routems.entity.RouteDetail;
import org.development.routems.entity.RouteEntity;
import org.development.routems.model.Route;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
public class RouteMapper {

    public static Route mapToRoute(RouteEntity routeEntity) {
        Route route = new Route();
        route.setId(routeEntity.getId());
        route.setCustomerId(routeEntity.getCustomerId());
        route.setStatus(routeEntity.getStatus());
        route.setCreatedAt(routeEntity.getCreatedAt().toString()); // Assuming createdAt is a Timestamp
        route.setRoute(
                routeEntity.getRoute()
                        .stream()
                        .map(routeDetailEntity -> {
                            RouteDetail routeDetail = new RouteDetail();
                            routeDetail.setName(routeDetailEntity.getName());
                            routeDetail.setLatitude(routeDetailEntity.getLatitude());
                            routeDetail.setLongitude(routeDetailEntity.getLongitude());
                            return routeDetail;
                        })
                        .collect(Collectors.toList())
        );
        return route;
    }
}

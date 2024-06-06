package org.development.routems.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.development.routems.entity.RouteDetail;
import org.development.routems.entity.RouteEntity;
import org.development.routems.model.Route;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
public class RouteMapper {

    public static Route mapToRoute(RouteEntity routeEntity) {
        Route route = new Route();
        route.setId(routeEntity.getId());
        route.setCustomerId(routeEntity.getCustomerId());
        route.setStatus(routeEntity.getStatus());
        route.setCreatedAt(routeEntity.getCreatedAt()); // Assuming createdAt is a Timestamp
        route.setRoute(
                routeEntity.getRoute()
                        .stream()
                        .map(routeDetailEntity -> {
                            RouteDetail routeDetail = new RouteDetail();
                            routeDetail.setLocationName(routeDetailEntity.getLocationName());
                            routeDetail.setLocationCode(routeDetailEntity.getLocationCode());
                            routeDetail.setLat(routeDetailEntity.getLat());
                            routeDetail.setLongitude(routeDetailEntity.getLongitude());
                            return routeDetail;
                        })
                        .collect(Collectors.toList())
        );
        return route;
    }

    public static List<RouteDetail> parseRouteDetail(String jsonArray) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonArray, new TypeReference<List<RouteDetail>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static RouteEntity toEntity(Route route) {
        if (route == null) {
            return null;
        }

        RouteEntity routeEntity = new RouteEntity();
        routeEntity.setId(route.getId());
        routeEntity.setCustomerId(route.getCustomerId());
        routeEntity.setStatus(route.getStatus());
        routeEntity.setCreatedAt(route.getCreatedAt());
        routeEntity.setRoute(route.getRoute());

        return routeEntity;
    }

    // Convert RouteEntity to Route
    public static Route toModel(RouteEntity routeEntity) {
        if (routeEntity == null) {
            return null;
        }

        Route route = new Route();
        route.setId(routeEntity.getId());
        route.setCustomerId(routeEntity.getCustomerId());
        route.setStatus(routeEntity.getStatus());
        route.setCreatedAt(routeEntity.getCreatedAt());
        route.setRoute(routeEntity.getRoute());

        return route;
    }

    // Convert list of Route to list of RouteEntity
    public static List<RouteEntity> toEntityList(List<Route> routes) {
        if (routes == null) {
            return null;
        }

        return routes.stream()
                .map(RouteMapper::toEntity)
                .collect(Collectors.toList());
    }

    // Convert list of RouteEntity to list of Route
    public static List<Route> toModelList(List<RouteEntity> routeEntities) {
        if (routeEntities == null) {
            return null;
        }

        return routeEntities.stream()
                .map(RouteMapper::toModel)
                .collect(Collectors.toList());
    }
}

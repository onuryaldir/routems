package org.development.routems.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.development.routems.model.CreateRouteRequest;
import org.development.routems.model.RouteResponse;
import org.development.routems.model.TrainModelRequest;
import org.development.routems.model.UpdateRouteStatusRequest;
import org.development.routems.service.RouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @PostMapping("/create")
    public void createRoute(@RequestBody @Valid final CreateRouteRequest createRouteRequest) {
        // Handle the request (e.g., save to database, process the data, etc.)
        // For this example, we'll just return a simple response

        routeService.createRoute(createRouteRequest);
    }

    @GetMapping("/get")
    public RouteResponse getStatus(
            @RequestHeader("customerId") final Integer customerId) {
        // Handle the request (e.g., retrieve data based on customerId and status)
        // For this example, we'll just return a simple response

      return routeService.getActiveRoute(customerId);
    }

    @PatchMapping("/edit")
    public RouteResponse setStatus(@RequestBody @Valid final UpdateRouteStatusRequest
                                                        updateRouteStatusRequest) {
        return routeService.updateRouteStatus(updateRouteStatusRequest.getId(),updateRouteStatusRequest.getStatus());
    }

    @GetMapping("/heartbeat")
    public ResponseEntity heartbeat() {
        // Handle the request (e.g., retrieve data based on customerId and status)
        // For this example, we'll just return a simple response

        return ResponseEntity.ok("hello");
    }
    @PostMapping("/train")
    public ResponseEntity trainModel(@RequestBody @Valid final TrainModelRequest trainModelRequest) {
        // Handle the request (e.g., save to database, process the data, etc.)
        // For this example, we'll just return a simple response

        return routeService.trainModel(trainModelRequest);
    }



}
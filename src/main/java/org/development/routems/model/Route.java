package org.development.routems.model;

import lombok.Data;
import org.development.routems.entity.RouteDetail;

import java.util.List;

@Data
public class Route {
    private Integer id;
    private Integer customerId;
    private String status;
    private String createdAt;
    private List<RouteDetail> route;

    // Constructors, getters, setters, and other methods can be added as needed
}
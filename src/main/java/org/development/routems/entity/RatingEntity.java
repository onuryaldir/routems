package org.development.routems.entity;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "ratings")
@Data
public class RatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "customer_id", nullable = false)
    private Integer customerId;

    @Min(0)
    @Max(5)
    @Column(name = "rate", nullable = false)
    private Integer rate;

    @Column(name = "route_id", nullable = false)
    private Integer routeId;
}
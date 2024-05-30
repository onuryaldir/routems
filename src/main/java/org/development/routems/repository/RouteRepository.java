package org.development.routems.repository;

import lombok.RequiredArgsConstructor;
import org.development.routems.entity.RouteEntity;
import org.development.routems.statics.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RouteRepository extends JpaRepository<RouteEntity, Integer> {
    @Query("SELECT r FROM RouteEntity r WHERE r.customerId = :customerId AND r.status = :status")
    RouteEntity getActiveRoute(Integer customerId, String status);
}
package org.development.routems.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.development.routems.statics.RequestStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteResponse {

    RequestStatus status;
    Route route;
}

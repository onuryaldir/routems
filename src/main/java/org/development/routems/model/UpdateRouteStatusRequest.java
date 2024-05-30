package org.development.routems.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.development.routems.statics.Status;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRouteStatusRequest {
    private Integer customerId;
    private Integer id;
    private Status status;
}

package org.development.routems.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRouteRequest {
    private Integer customerId;
    private List<String> selectedLetters;
}
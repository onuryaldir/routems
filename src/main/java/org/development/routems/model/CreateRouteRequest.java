package org.development.routems.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRouteRequest {
    private Integer customerId;

    @JsonProperty("selected_letters")
    private List<String> selectedLetters;
}
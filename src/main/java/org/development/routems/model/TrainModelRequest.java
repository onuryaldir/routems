package org.development.routems.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainModelRequest {

    @JsonProperty("user_country_code")
    private Integer userCountryCode;

    @JsonProperty("user_age")
    private Integer userAge;
}
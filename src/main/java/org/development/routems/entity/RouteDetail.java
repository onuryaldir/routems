package org.development.routems.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.core.annotation.AliasFor;

@Data
public class RouteDetail {


    private double lat;

    @JsonProperty("indicator")
    private String indicator;

    @JsonProperty("location_id")
    private String locationCode;

    @JsonProperty("location_name")
    private String locationName;

    @JsonProperty("long")
    private double longitude;

}

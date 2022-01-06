package com.example.domaincrawler.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NearEarthObject {
    private String id;
    @JsonProperty("neo_reference_id")
    private String referenceId;
    private String name;
    @JsonProperty("name_limited")
    private String nameLimited;
    private boolean designation;
    @JsonProperty("is_potentially_hazardous_asteroid")
    private String isHazardous;
    @JsonProperty("is_sentry_object")
    private String isSentry;

}

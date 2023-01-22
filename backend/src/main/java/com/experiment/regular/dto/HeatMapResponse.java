package com.experiment.regular.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HeatMapResponse {
    private Double x;
    private Double y;
    private String value;
}

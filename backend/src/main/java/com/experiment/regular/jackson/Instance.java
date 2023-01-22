package com.experiment.regular.jackson;

import lombok.Data;

import java.util.List;

@Data
public class Instance {
    private Double pos_x;
    private Double pos_y;
    private Double vel_x;
    private Double vel_y;
    private Double confidence;
    private List<String> sensors;
}

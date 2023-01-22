package com.experiment.regular.jackson;

import lombok.Data;

import java.util.Map;

@Data
public class Schema {
    private TimeStamp timestamp;
    private Id _id;
    private Map<String, Instance> instances;
}

package com.experiment.regular.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class DataResponse {

    private Timestamp timestamp;
    private String value;
}

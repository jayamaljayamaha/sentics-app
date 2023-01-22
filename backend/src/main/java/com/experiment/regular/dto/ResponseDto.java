package com.experiment.regular.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
public class ResponseDto<T> implements Serializable {
    List<T> response;
}

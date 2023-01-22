package com.experiment.regular.jackson;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Date {
    private String $numberLong;
    public String get$numberLong() {
        return $numberLong;
    }

    public void set$numberLong(String $numberLong) {
        this.$numberLong = $numberLong;
    }
}

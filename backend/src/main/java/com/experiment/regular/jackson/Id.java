package com.experiment.regular.jackson;

import lombok.Data;

@Data
public class Id {
    private String $oid;

    public String get$oid() {
        return $oid;
    }

    public void set$oid(String $oid) {
        this.$oid = $oid;
    }
}

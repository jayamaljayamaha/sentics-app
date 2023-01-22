package com.experiment.regular.jackson;

import lombok.Data;

@Data
public class TimeStamp {
    private Date $date;
    public Date get$date() {
        return $date;
    }

    public void set$date(Date $date) {
        this.$date = $date;
    }
}

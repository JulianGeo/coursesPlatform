package com.coursesplatform.enrroll.domain.sharedValues;

import com.coursesplatform.enrroll.generic.ValueObject;

import java.util.Objects;

public class Rate implements ValueObject <Integer> {

    private Integer value;

    public Rate(Integer value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public Integer value() {
        return value;
    }
}

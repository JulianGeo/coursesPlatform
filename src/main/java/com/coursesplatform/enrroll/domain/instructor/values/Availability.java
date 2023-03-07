package com.coursesplatform.enrroll.domain.instructor.values;

import com.coursesplatform.enrroll.generic.ValueObject;

import java.util.Objects;

public class Availability implements ValueObject<Boolean> {

    private Boolean value;

    public Availability(Boolean value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public Boolean value() {
        return value;
    }
}
package com.coursesplatform.enroll.domain.instructor.values;

import com.coursesplatform.enroll.generic.ValueObject;

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
package com.coursesplatform.enroll.domain.student.values;

import com.coursesplatform.enroll.generic.ValueObject;

import java.util.Objects;

public class PlanName implements ValueObject <String> {

    private String value;

    public PlanName(String value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public String value() {
        return value;
    }
}
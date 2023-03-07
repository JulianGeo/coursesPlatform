package com.coursesplatform.enroll.domain.course.values;

import com.coursesplatform.enroll.generic.ValueObject;

import java.util.Objects;

public class Status implements ValueObject <String> {

    private String value;

    public Status(String value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public String value() {
        return value;
    }
}
package com.coursesplatform.enroll.domain.course.values;

import com.coursesplatform.enroll.generic.ValueObject;

import java.util.Objects;

public class Description implements ValueObject<String> {

    private String value;

    public Description(String value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public String value() {
        return value;
    }
}
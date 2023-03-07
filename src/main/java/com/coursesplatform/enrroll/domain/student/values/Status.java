package com.coursesplatform.enrroll.domain.student.values;

import com.coursesplatform.enrroll.generic.ValueObject;

import java.util.Objects;

public class Status implements ValueObject<String> {

    private String value;

    public Status(String value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public String value() {
        return value;
    }
}
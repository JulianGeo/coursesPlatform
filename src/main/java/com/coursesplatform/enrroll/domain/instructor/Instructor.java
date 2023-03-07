package com.coursesplatform.enrroll.domain.instructor;

import com.coursesplatform.enrroll.generic.ValueObject;

import java.util.Objects;

public class Instructor implements ValueObject<String> {

    private String value;

    public Instructor(String value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public String value() {
        return value;
    }
}

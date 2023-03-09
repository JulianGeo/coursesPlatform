package com.coursesplatform.enroll.domain.sharedValues;

import com.coursesplatform.enroll.generic.ValueObject;

import java.util.Objects;

public class Email implements ValueObject<String> {

    private String value;

    public Email(String value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public String value() {
        return value;
    }
}
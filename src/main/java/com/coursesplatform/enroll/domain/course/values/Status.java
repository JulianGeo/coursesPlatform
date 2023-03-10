package com.coursesplatform.enroll.domain.course.values;

import com.coursesplatform.enroll.generic.ValueObject;

import java.util.Objects;

public class Status implements ValueObject <String> {

    private String value;

    public Status() {
        this.value = "Active";
    }

    public Status(String status) {
        this.value = status;
    }

    @Override
    public String value() {
        return value;
    }
}
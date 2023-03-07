package com.coursesplatform.enrroll.domain.sharedValues;

import com.coursesplatform.enrroll.generic.ValueObject;

import java.time.LocalDate;
import java.util.Objects;

public class Date implements ValueObject <LocalDate> {

    private LocalDate value;

    public Date(LocalDate value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public LocalDate value() {
        return value;
    }
}
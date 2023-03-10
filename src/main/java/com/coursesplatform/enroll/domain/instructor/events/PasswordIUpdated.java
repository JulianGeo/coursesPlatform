package com.coursesplatform.enroll.domain.instructor.events;

import com.coursesplatform.enroll.generic.DomainEvent;

public class PasswordIUpdated extends DomainEvent {
    private String password;

    public PasswordIUpdated(String password) {
        super("enroll.passwordOfInstructorChanged");
        this.password=password;
    }
}

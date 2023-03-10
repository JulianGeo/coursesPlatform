package com.coursesplatform.enroll.domain.instructor.events;

import com.coursesplatform.enroll.generic.DomainEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordIUpdated extends DomainEvent {
    private String password;

    public PasswordIUpdated(String password) {
        super("enroll.instructorPasswordChanged");
        this.password=password;
    }
}

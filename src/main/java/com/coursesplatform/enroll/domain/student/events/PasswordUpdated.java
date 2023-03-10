package com.coursesplatform.enroll.domain.student.events;

import com.coursesplatform.enroll.generic.DomainEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordUpdated extends DomainEvent {

    private String password;

    public PasswordUpdated(String password) {
        super("enroll.studentPasswordChanged");
        this.password=password;
    }
}

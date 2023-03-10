package com.coursesplatform.enroll.domain.instructor.events;

import com.coursesplatform.enroll.generic.DomainEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorRegistered extends DomainEvent {

    private String name;
    private String personalID;
    private String email;
    private String phone;
    private String user;
    private String password;

    public InstructorRegistered(
            String name,
            String personalID,
            String email,
            String phone,
            String user,
            String password) {
        super("enroll.instructorRegistered");
        this.name=name;
        this.personalID=personalID;
        this.email=email;
        this.phone=phone;
        this.user=user;
        this.password=password;
    }
}

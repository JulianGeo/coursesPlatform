package com.coursesplatform.enroll.domain.student.events;

import com.coursesplatform.enroll.generic.Command;
import com.coursesplatform.enroll.generic.DomainEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRegistered extends DomainEvent {

    private String name;
    private String personalID;
    private String email;
    private String phone;
    private String user;
    private String password;
    private String planID;
    private String planName;



    public StudentRegistered (
            String name,
            String personalID,
            String email,
            String phone,
            String user,
            String password,
            String planID,
            String planName) {
        super ("enroll.studentRegistered");
        this.name=name;
        this.personalID=personalID;
        this.email=email;
        this.phone=phone;
        this.user=user;
        this.password=password;
        this.planID=planID;
        this.planName=planName;
    }

}

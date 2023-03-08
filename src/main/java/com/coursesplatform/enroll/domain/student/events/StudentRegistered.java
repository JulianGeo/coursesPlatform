package com.coursesplatform.enroll.domain.student.events;

import com.coursesplatform.enroll.generic.Command;
import com.coursesplatform.enroll.generic.DomainEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRegistered extends DomainEvent {

    private String studentID;
    private String name;
    private String personalID;
    private String email;
    private String phone;
    private String user;
    private String password;
    private String planID;
    private String planName;



    public StudentRegistered (String studentID, String name, String personalID, String email, String phone, String user, String password) {
        super ("enroll.studentRegistered");
        this.studentID=studentID;
        this.name=name;
        this.personalID=personalID;
        this.email=email;
        this.phone=phone;
        this.user=user;
        this.password=password;
    }

}

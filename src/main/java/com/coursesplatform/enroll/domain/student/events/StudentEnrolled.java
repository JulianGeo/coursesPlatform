package com.coursesplatform.enroll.domain.student.events;

import com.coursesplatform.enroll.generic.DomainEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentEnrolled extends DomainEvent {

    private String studentID;
    private String enrollmentID;

    public StudentEnrolled (String type) {
        super (type);
    }
}

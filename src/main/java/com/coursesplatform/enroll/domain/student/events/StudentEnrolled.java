package com.coursesplatform.enroll.domain.student.events;

import com.coursesplatform.enroll.generic.DomainEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentEnrolled extends DomainEvent {

    private String enrollmentID;
    private String courseID;


    public StudentEnrolled(String enrollmentID, String courseID) {
        super("enroll.studentEnrolled");
        this.enrollmentID=enrollmentID;
        this.courseID = courseID;
    }
}

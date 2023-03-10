package com.coursesplatform.enroll.domain.student.events;

import com.coursesplatform.enroll.generic.DomainEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentUnenrolled extends DomainEvent {
    private String enrollmentID;
    private String courseID;

    public StudentUnenrolled(
            String enrollmentID,
            String courseID) {
        super("enroll.StudentUnenrolled");
        this.enrollmentID=enrollmentID;
        this.courseID=courseID;
    }
}

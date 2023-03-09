package com.coursesplatform.enroll.domain.student.events;

import com.coursesplatform.enroll.generic.DomainEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentUnenrolled extends DomainEvent {

    private String studentID;
    private String enrollmentID;
    private String courseManagerID;

    public StudentUnenrolled(
            String studentID,
            String enrollmentID,
            String courseManagerID) {
        super("enroll.StudentUnenrolled");
        this.studentID=studentID;
        this.enrollmentID=enrollmentID;
        this.courseManagerID=courseManagerID;
    }
}

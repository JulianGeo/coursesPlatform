package com.coursesplatform.enroll.domain.course.events;

import com.coursesplatform.enroll.generic.DomainEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentEnrolledFromStudent extends DomainEvent {

    private String studentID;
    private String enrollmentID;
    private String studentManagerID;

    public StudentEnrolledFromStudent(String studentID, String enrollmentID) {
        super("enroll.studentEnrolledFromStudent");
        this.studentID=studentID;
        this.enrollmentID=enrollmentID;
    }
}

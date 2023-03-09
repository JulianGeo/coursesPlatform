package com.coursesplatform.enroll.domain.course.events;

import com.coursesplatform.enroll.generic.DomainEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentUnenrolledFromStudent extends DomainEvent {
    private String enrollmentID;

    public StudentUnenrolledFromStudent(String enrollmentID) {
        super("enroll.studentUnenrolledFromStudent");
        this.enrollmentID=enrollmentID;
    }
}

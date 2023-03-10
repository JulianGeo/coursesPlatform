package com.coursesplatform.enroll.domain.course.events;

import com.coursesplatform.enroll.generic.DomainEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseFinishedFromStudent extends DomainEvent {
    private String enrollmentID;

    public CourseFinishedFromStudent(String enrollmentID) {
        super("enroll.courseFinishedFromStudent");
        this.enrollmentID=enrollmentID;
    }
}

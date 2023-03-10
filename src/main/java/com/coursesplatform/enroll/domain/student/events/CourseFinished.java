package com.coursesplatform.enroll.domain.student.events;

import com.coursesplatform.enroll.generic.DomainEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseFinished extends DomainEvent {
    private String courseID;
    private String enrollmentID;

    public CourseFinished(String courseID, String enrollmentID) {
        super("enroll.courseFinished");
        this.courseID=courseID;
        this.enrollmentID=enrollmentID;
    }
}

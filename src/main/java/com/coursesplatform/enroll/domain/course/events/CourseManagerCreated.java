package com.coursesplatform.enroll.domain.course.events;

import com.coursesplatform.enroll.generic.DomainEvent;

public class CourseManagerCreated extends DomainEvent {
    public CourseManagerCreated () {
        super ("enroll.courseManagerCreated");
    }
}
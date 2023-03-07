package com.coursesplatform.enroll.domain.instructor.events;

import com.coursesplatform.enroll.generic.DomainEvent;

public class InstructorManagerCreated extends DomainEvent {
    public InstructorManagerCreated () {
        super ("enroll.instructorManagerCreated");
    }
}

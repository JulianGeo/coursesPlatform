package com.coursesplatform.enroll.domain.student.events;

import com.coursesplatform.enroll.generic.DomainEvent;

public class StudentManagerCreated extends DomainEvent {

    public StudentManagerCreated () {
        super ("enroll.StudentManagerCreated");
    }
}

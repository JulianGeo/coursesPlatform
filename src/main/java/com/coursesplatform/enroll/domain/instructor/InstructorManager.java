package com.coursesplatform.enroll.domain.instructor;

import com.coursesplatform.enroll.domain.instructor.events.InstructorManagerCreated;
import com.coursesplatform.enroll.domain.instructor.values.InstructorManagerID;
import com.coursesplatform.enroll.domain.student.StudentManager;
import com.coursesplatform.enroll.domain.student.values.StudentManagerID;
import com.coursesplatform.enroll.generic.AggregateRoot;
import com.coursesplatform.enroll.generic.DomainEvent;

import java.util.List;

public class InstructorManager extends AggregateRoot <InstructorManagerID> {

    protected List<Instructor> instructors;

    public InstructorManager (InstructorManagerID enityID) {
        super (enityID);
        subscribe(new InstructorManagerChange(this));
        appendChange(new InstructorManagerCreated ()).apply();
    }

    public static InstructorManager from(InstructorManagerID id, List <DomainEvent> events){
        InstructorManager instructorManager = new InstructorManager(id);
        events.forEach(event -> instructorManager.applyEvent(event));
        return instructorManager;
    }

}

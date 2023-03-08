package com.coursesplatform.enroll.domain.student;

import com.coursesplatform.enroll.domain.student.events.StudentManagerCreated;
import com.coursesplatform.enroll.domain.student.values.StudentID;
import com.coursesplatform.enroll.domain.student.values.StudentManagerID;
import com.coursesplatform.enroll.generic.AggregateRoot;
import com.coursesplatform.enroll.generic.DomainEvent;

import java.util.List;
import java.util.Map;

public class StudentManager extends AggregateRoot <StudentManagerID> {
    protected Map <StudentID, Plan> studentPlan;
    protected List<Student> students;

    //TODO: should be private?
    public StudentManager (StudentManagerID entityID) {
        super (entityID);
        subscribe(new StudentManagerChange (this));
        appendChange(new StudentManagerCreated ()).apply();
    }

    public static StudentManager from(StudentManagerID id, List <DomainEvent> events){
        StudentManager studentManager = new StudentManager(id);
        events.forEach(event -> studentManager.applyEvent(event));
        return studentManager;
    }
}

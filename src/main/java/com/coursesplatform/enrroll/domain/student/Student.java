package com.coursesplatform.enrroll.domain.student;

import com.coursesplatform.enrroll.domain.course.values.EnrollmentID;
import com.coursesplatform.enrroll.domain.sharedValues.Account;
import com.coursesplatform.enrroll.domain.sharedValues.Data;
import com.coursesplatform.enrroll.domain.student.values.StudentID;
import com.coursesplatform.enrroll.generic.AggregateRoot;
import com.coursesplatform.enrroll.generic.DomainEvent;

import java.util.List;

public class Student extends AggregateRoot<StudentID> {

    protected Account account;
    protected Data personalData;
    protected Plan plan;
    protected List<EnrollmentID> enrollments;

    public Student (StudentID entityID) {
        super (entityID);
        //subscribe(new InventoryChange(this));
        //appendChange(new InventoryCreated()).apply();
    }

    public static Student from(StudentID id, List<DomainEvent> events){
        Student student = new Student(id);
        events.forEach(event -> student.applyEvent(event));
        return student;
    }


}




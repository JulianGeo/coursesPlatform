package com.coursesplatform.enrroll.domain.instructor;

import com.coursesplatform.enrroll.domain.instructor.values.Availability;
import com.coursesplatform.enrroll.domain.instructor.values.InstructorID;
import com.coursesplatform.enrroll.domain.sharedValues.Account;
import com.coursesplatform.enrroll.domain.sharedValues.Data;
import com.coursesplatform.enrroll.domain.student.Student;
import com.coursesplatform.enrroll.domain.student.values.StudentID;
import com.coursesplatform.enrroll.generic.AggregateRoot;
import com.coursesplatform.enrroll.generic.DomainEvent;
import com.coursesplatform.enrroll.generic.ValueObject;

import java.util.List;
import java.util.Objects;

public class Instructor extends AggregateRoot<InstructorID> {

    protected Account account;
    protected Data personalData;
    protected Availability availability;
    protected List<Review> reviews;
    protected List<Rating> ratings;

    public Instructor (InstructorID aggregateID) {
        super (aggregateID);
        //subscribe(new InventoryChange(this));
        //appendChange(new InventoryCreated()).apply();
    }

    public static Instructor from(InstructorID aggregateID, List<DomainEvent> events){
        Instructor instructor = new Instructor(aggregateID);
        events.forEach(event -> instructor.applyEvent(event));
        return instructor;
    }
}

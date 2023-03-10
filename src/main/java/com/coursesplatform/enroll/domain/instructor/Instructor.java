package com.coursesplatform.enroll.domain.instructor;

import com.coursesplatform.enroll.domain.instructor.events.AvailableMade;
import com.coursesplatform.enroll.domain.instructor.events.InstructorRegistered;
import com.coursesplatform.enroll.domain.instructor.events.PasswordIUpdated;
import com.coursesplatform.enroll.domain.instructor.events.UnavailableMade;
import com.coursesplatform.enroll.domain.instructor.values.Availability;
import com.coursesplatform.enroll.domain.instructor.values.InstructorID;
import com.coursesplatform.enroll.domain.sharedValues.Account;
import com.coursesplatform.enroll.domain.sharedValues.Data;
import com.coursesplatform.enroll.domain.student.events.PasswordUpdated;
import com.coursesplatform.enroll.generic.AggregateRoot;
import com.coursesplatform.enroll.generic.DomainEvent;

import java.util.List;
import java.util.Objects;

public class Instructor extends AggregateRoot<InstructorID> {

    protected Account account;
    protected Data personalData;
    protected Availability availability;
    protected List<Review> reviews;
    protected List<Rating> ratings;


    public Instructor(InstructorID id) {
        super(id);
    }

    public Instructor (InstructorID entityID, Account account, Data personalData) {
        super (entityID);
        this.account=account;
        this.personalData=personalData;
        this.availability = new Availability(false);
        subscribe(new InstructorChange(this));
        appendChange(new InstructorRegistered(
                personalData.value().name().value(),
                personalData.value().personalID().value(),
                personalData.value().email().value(),
                personalData.value().phone().value(),
                account.value().user().value(),
                account.value().password().value())).apply();
    }

    public static Instructor from(InstructorID id, List <DomainEvent> events){
        Instructor instructor = new Instructor(id);
        events.forEach(event -> instructor.applyEvent(event));
        return instructor;
    }

    public Account account () {
        return account;
    }

    public Data personalData () {
        return personalData;
    }

    public Availability availability () {
        return availability;
    }

    public List <Review> reviews () {
        return reviews;
    }

    public List <Rating> ratings () {
        return ratings;
    }

    public void makeAvailable(Boolean isAvailable){
        Objects.requireNonNull(isAvailable);
        appendChange(new AvailableMade()).apply();
    }

    public void makeUnavailable(Boolean isAvailable){
        Objects.requireNonNull(isAvailable);
        appendChange(new UnavailableMade()).apply();
    }

    public void changePassword(String newPassword){
        Objects.requireNonNull(newPassword);
        appendChange(new PasswordIUpdated(newPassword)).apply();
    }
}

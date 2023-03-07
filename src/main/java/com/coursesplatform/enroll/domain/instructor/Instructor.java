package com.coursesplatform.enroll.domain.instructor;

import com.coursesplatform.enroll.domain.instructor.events.InstructorManagerCreated;
import com.coursesplatform.enroll.domain.instructor.values.Availability;
import com.coursesplatform.enroll.domain.instructor.values.InstructorID;
import com.coursesplatform.enroll.domain.sharedValues.Account;
import com.coursesplatform.enroll.domain.sharedValues.Data;
import com.coursesplatform.enroll.generic.AggregateRoot;
import com.coursesplatform.enroll.generic.DomainEvent;

import java.util.List;

public class Instructor extends AggregateRoot<InstructorID> {

    protected Account account;
    protected Data personalData;
    protected Availability availability;
    protected List<Review> reviews;
    protected List<Rating> ratings;


    public Instructor (InstructorID entityID, Account account, Data personalData, Availability availability, List <Review> reviews, List <Rating> ratings) {
        super (entityID);
        this.account=account;
        this.personalData=personalData;
        this.availability=availability;
        this.reviews=reviews;
        this.ratings=ratings;
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
}

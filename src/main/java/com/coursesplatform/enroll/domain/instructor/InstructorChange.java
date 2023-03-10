package com.coursesplatform.enroll.domain.instructor;

import com.coursesplatform.enroll.domain.instructor.events.AvailableMade;
import com.coursesplatform.enroll.domain.instructor.events.InstructorRegistered;
import com.coursesplatform.enroll.domain.instructor.events.PasswordIUpdated;
import com.coursesplatform.enroll.domain.instructor.events.UnavailableMade;
import com.coursesplatform.enroll.domain.instructor.values.Availability;
import com.coursesplatform.enroll.domain.sharedValues.*;
import com.coursesplatform.enroll.domain.student.events.PasswordUpdated;
import com.coursesplatform.enroll.generic.EventChange;

import java.util.ArrayList;

public class InstructorChange extends EventChange {

    public InstructorChange(Instructor instructor) {
        apply ((InstructorRegistered event) -> {
            instructor.account=new Account(new User(event.getUser()), new Password(event.getPassword()));
            instructor.personalData = new Data(
                    new PersonalID(event.getPersonalID()),
                    new Name(event.getName()),
                    new Email(event.getEmail()),
                    new Phone(event.getPhone()));
            instructor.reviews = new ArrayList <> ();
            instructor.ratings = new ArrayList <> ();
        });

        apply((AvailableMade event) -> {
            instructor.availability = new Availability(event.isAvailable());
        });

        apply((UnavailableMade event) -> {
            instructor.availability = new Availability(event.isAvailable());
        });

        apply((PasswordIUpdated event) -> {
            Account oldAccount = instructor.account;
            instructor.account =  new Account(
                    oldAccount.value().user(),
                    new Password(event.getPassword()));
        });


    }
}

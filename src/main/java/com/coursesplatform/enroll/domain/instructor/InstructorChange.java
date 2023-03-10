package com.coursesplatform.enroll.domain.instructor;

import com.coursesplatform.enroll.domain.instructor.events.InstructorRegistered;
import com.coursesplatform.enroll.generic.EventChange;

import java.util.ArrayList;

public class InstructorChange extends EventChange {

    public InstructorChange(Instructor instructor) {
        apply ((InstructorRegistered event) -> {
            instructor.reviews = new ArrayList <> ();
            instructor.ratings = new ArrayList <> ();
        });


    }
}

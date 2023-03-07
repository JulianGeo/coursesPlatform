package com.coursesplatform.enroll.domain.instructor;

import com.coursesplatform.enroll.domain.instructor.events.InstructorManagerCreated;
import com.coursesplatform.enroll.domain.instructor.values.Availability;
import com.coursesplatform.enroll.generic.EventChange;

import java.util.ArrayList;

public class InstructorManagerChange extends EventChange {

    public InstructorManagerChange (InstructorManager instructorManager) {
        apply ((InstructorManagerCreated event) -> {
            instructorManager.instructors = new ArrayList <> ();
        });
    }
}

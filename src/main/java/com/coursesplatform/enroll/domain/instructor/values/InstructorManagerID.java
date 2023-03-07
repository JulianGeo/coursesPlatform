package com.coursesplatform.enroll.domain.instructor.values;

import com.coursesplatform.enroll.generic.Identity;

public class InstructorManagerID extends Identity {

    public InstructorManagerID() {
    }

    private InstructorManagerID(String instructorManagerID) {
        super(instructorManagerID);
    }

    public static InstructorManagerID of(String instructorManagerID){
        return new InstructorManagerID(instructorManagerID);
    }
}
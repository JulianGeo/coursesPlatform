package com.coursesplatform.enroll.domain.instructor.values;

import com.coursesplatform.enroll.generic.Identity;

public class InstructorID extends Identity {

    public InstructorID() {
    }

    private InstructorID(String instructorID) {
        super(instructorID);
    }

    public static InstructorID of(String instructorID){
        return new InstructorID(instructorID);
    }
}
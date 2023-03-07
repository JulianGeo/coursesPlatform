package com.coursesplatform.enrroll.domain.instructor.values;

import com.coursesplatform.enrroll.generic.Identity;

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
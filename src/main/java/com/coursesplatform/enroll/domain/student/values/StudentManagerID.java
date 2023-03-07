package com.coursesplatform.enroll.domain.student.values;

import com.coursesplatform.enroll.generic.Identity;

public class StudentManagerID extends Identity {

    public StudentManagerID() {
    }

    private StudentManagerID(String studentManagerID) {
        super(studentManagerID);
    }

    public static StudentManagerID of(String studentManagerID){
        return new StudentManagerID(studentManagerID);
    }
}
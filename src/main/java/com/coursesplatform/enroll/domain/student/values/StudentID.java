package com.coursesplatform.enroll.domain.student.values;

import com.coursesplatform.enroll.generic.Identity;

public class StudentID extends Identity {

    public StudentID() {
    }

    private StudentID(String studentID) {
        super(studentID);
    }

    public static StudentID of(String studentID){
        return new StudentID(studentID);
    }
}
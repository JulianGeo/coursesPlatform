package com.coursesplatform.enrroll.domain.student.values;

import com.coursesplatform.enrroll.domain.sharedValues.ReviewID;
import com.coursesplatform.enrroll.generic.Identity;

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
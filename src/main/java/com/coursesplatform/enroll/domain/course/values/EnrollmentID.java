package com.coursesplatform.enroll.domain.course.values;


import com.coursesplatform.enroll.generic.Identity;

public class EnrollmentID extends Identity {

    public EnrollmentID () {
    }

    private EnrollmentID (String tuitionID) {
        super(tuitionID);
    }

    public static EnrollmentID of(String tuitionID){
        return new EnrollmentID (tuitionID);
    }
}
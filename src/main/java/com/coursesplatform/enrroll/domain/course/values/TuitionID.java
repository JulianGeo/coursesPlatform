package com.coursesplatform.enrroll.domain.course.values;


import com.coursesplatform.enrroll.generic.Identity;

public class TuitionID extends Identity {

    public TuitionID() {
    }

    private TuitionID(String tuitionID) {
        super(tuitionID);
    }

    public static TuitionID of(String tuitionID){
        return new TuitionID(tuitionID);
    }
}
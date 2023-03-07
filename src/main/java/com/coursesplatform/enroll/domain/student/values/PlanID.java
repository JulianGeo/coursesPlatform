package com.coursesplatform.enroll.domain.student.values;

import com.coursesplatform.enroll.generic.Identity;

public class PlanID extends Identity {

    public PlanID() {
    }

    private PlanID(String planID) {
        super(planID);
    }

    public static PlanID of(String planID){
        return new PlanID(planID);
    }
}
package com.coursesplatform.enrroll.domain.student.values;

import com.coursesplatform.enrroll.generic.Identity;

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
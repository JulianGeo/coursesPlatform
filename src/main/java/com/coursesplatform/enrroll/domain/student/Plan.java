package com.coursesplatform.enrroll.domain.student;

import com.coursesplatform.enrroll.domain.student.values.PlanID;
import com.coursesplatform.enrroll.domain.student.values.PlanName;
import com.coursesplatform.enrroll.domain.student.values.StudentID;
import com.coursesplatform.enrroll.generic.Entity;

public class Plan extends Entity<PlanID> {
    private PlanName planName;
    private StudentID studentID;

    public Plan (PlanID entityID, PlanName planName, StudentID studentID) {
        super (entityID);
        this.planName=planName;
        this.studentID=studentID;
    }

    public PlanName planName () {
        return planName;
    }

    public StudentID studentID () {
        return studentID;
    }
}

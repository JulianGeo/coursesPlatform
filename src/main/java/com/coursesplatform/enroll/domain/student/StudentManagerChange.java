package com.coursesplatform.enroll.domain.student;

import com.coursesplatform.enroll.domain.student.events.StudentManagerCreated;
import com.coursesplatform.enroll.generic.EventChange;

import java.util.HashMap;

public class StudentManagerChange extends EventChange {

    public StudentManagerChange (StudentManager studentManager){
        apply ((StudentManagerCreated event) -> {
            studentManager.students = new HashMap <> ();
        });
    }
}

package com.coursesplatform.enroll.domain.course;

import com.coursesplatform.enroll.domain.course.events.CourseManagerCreated;
import com.coursesplatform.enroll.generic.EventChange;

import java.util.ArrayList;

public class CourseManagerChange extends EventChange {

    public CourseManagerChange (CourseManager courseManager) {
        apply ((CourseManagerCreated event) -> {
            courseManager.enrollmentList = new ArrayList<> ();
            courseManager.courses = new ArrayList<> ();
        });
    }
}

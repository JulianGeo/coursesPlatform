package com.coursesplatform.enroll.domain.course.values;

import com.coursesplatform.enroll.generic.Identity;

public class CourseManagerID extends Identity {

    public CourseManagerID() {
    }

    private CourseManagerID(String courseManagerID) {
        super(courseManagerID);
    }

    public static CourseManagerID of(String courseManagerID){
        return new CourseManagerID(courseManagerID);
    }
}
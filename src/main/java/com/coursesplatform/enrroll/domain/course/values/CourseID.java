package com.coursesplatform.enrroll.domain.course.values;

import com.coursesplatform.enrroll.generic.Identity;

public class CourseID extends Identity {

    public CourseID() {
    }

    private CourseID(String courseID) {
        super(courseID);
    }

    public static CourseID of(String courseID){
        return new CourseID(courseID);
    }
}
package com.coursesplatform.enroll.domain.course;

import com.coursesplatform.enroll.domain.course.values.CourseID;
import com.coursesplatform.enroll.domain.course.values.Description;
import com.coursesplatform.enroll.domain.instructor.values.InstructorID;
import com.coursesplatform.enroll.generic.Entity;

import java.util.List;

public class Course extends Entity <CourseID> {

    private InstructorID instructorID;
    private Description description;
    private List <Review> reviews;
    private List<Rating> ratings;

    public Course (CourseID entityID, InstructorID instructorID, Description description, List <Review> reviews, List <Rating> ratings) {
        super (entityID);
        this.instructorID=instructorID;
        this.description=description;
        this.reviews=reviews;
        this.ratings=ratings;
    }

    public InstructorID instructorID () {
        return instructorID;
    }

    public Description description () {
        return description;
    }

    public List <Review> reviews () {
        return reviews;
    }

    public List <Rating> ratings () {
        return ratings;
    }
}

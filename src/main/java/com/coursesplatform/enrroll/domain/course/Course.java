package com.coursesplatform.enrroll.domain.course;

import com.coursesplatform.enrroll.domain.course.values.CourseID;
import com.coursesplatform.enrroll.domain.course.values.Description;
import com.coursesplatform.enrroll.domain.instructor.Instructor;
import com.coursesplatform.enrroll.domain.instructor.values.InstructorID;
import com.coursesplatform.enrroll.generic.AggregateRoot;
import com.coursesplatform.enrroll.generic.DomainEvent;

import java.util.List;

public class Course extends AggregateRoot <CourseID> {

    protected InstructorID instructorID;
    protected Description description;
    protected List <Review> reviews;
    protected List<Rating> ratings;
    protected List<Enrollment> enrollments;

    public Course (CourseID aggregateID) {
        super (aggregateID);
        //subscribe(new InventoryChange(this));
        //appendChange(new InventoryCreated()).apply();
    }

    public static Course from(CourseID aggregateID, List<DomainEvent> events){
        Course course = new Course(aggregateID);
        events.forEach(event -> course.applyEvent(event));
        return course;
    }
}

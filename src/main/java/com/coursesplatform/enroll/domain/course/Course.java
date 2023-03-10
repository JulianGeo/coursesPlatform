package com.coursesplatform.enroll.domain.course;

import com.coursesplatform.enroll.domain.course.events.*;
import com.coursesplatform.enroll.domain.course.values.CourseID;
import com.coursesplatform.enroll.domain.course.values.Description;
import com.coursesplatform.enroll.domain.instructor.values.InstructorID;
import com.coursesplatform.enroll.generic.AggregateRoot;
import com.coursesplatform.enroll.generic.DomainEvent;

import java.util.List;
import java.util.Objects;

public class Course extends AggregateRoot<CourseID> {

    protected InstructorID instructorID;
    protected Description description;
    protected List <Review> reviews;
    protected List<Rating> ratings;
    protected List <Enrollment> enrollments;

    private Course(CourseID entityID) {
        super(entityID);
        subscribe(new CourseChange(this));
    }

    public Course(CourseID entityID, InstructorID instructorID, Description description){
        super(entityID);
        this.instructorID=instructorID;
        this.description=description;
        subscribe(new CourseChange(this));
        appendChange(new CourseCreated(instructorID.value(), description.value())).apply();
    }

    public static Course from(CourseID aggregateID, List<DomainEvent> events){
        Course course = new Course(aggregateID);
        events.forEach(event -> course.applyEvent(event));
        return course;
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

    public List<Enrollment> enrollments() {
        return enrollments;
    }

    public void enrollStudent(String studentID, String enrollmentID, String courseID) {
        Objects.requireNonNull(studentID);
        Objects.requireNonNull(enrollmentID);
        Objects.requireNonNull(courseID);
        appendChange(new StudentEnrolledFromStudent(studentID, enrollmentID)).apply();
    }
    public void unenenrollStudent(String enrollmentID) {
        Objects.requireNonNull(enrollmentID);
        appendChange(new StudentUnenrolledFromStudent(enrollmentID)).apply();
    }

    public void changeDescription(String newDescription){
        Objects.requireNonNull(newDescription);
        appendChange(new DescriptionChanged(newDescription)).apply();
    }

    public void finishCourse(String enrollmentID){
        Objects.requireNonNull(enrollmentID);
        appendChange(new CourseFinishedFromStudent(enrollmentID)).apply();
    }
}

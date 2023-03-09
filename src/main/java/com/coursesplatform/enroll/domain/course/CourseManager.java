package com.coursesplatform.enroll.domain.course;

import com.coursesplatform.enroll.domain.course.events.CourseManagerCreated;
import com.coursesplatform.enroll.domain.course.events.StudentEnrolledFromStudent;
import com.coursesplatform.enroll.domain.course.values.CourseManagerID;
import com.coursesplatform.enroll.domain.student.events.StudentEnrolled;
import com.coursesplatform.enroll.generic.AggregateRoot;
import com.coursesplatform.enroll.generic.DomainEvent;

import java.util.List;
import java.util.Objects;

public class CourseManager extends AggregateRoot<CourseManagerID> {

    protected List <Enrollment> enrollmentList;
    protected List<Course> courses;

    public CourseManager (CourseManagerID aggregateID) {
        super (aggregateID);
        subscribe(new CourseManagerChange (this));
        appendChange(new CourseManagerCreated ()).apply();
    }

    public static CourseManager from(CourseManagerID aggregateID, List<DomainEvent> events){
        CourseManager courseManager = new CourseManager(aggregateID);
        events.forEach(event -> courseManager.applyEvent(event));
        return courseManager;
    }
    public void enrollStudent(String studentID, String enrollmentID, String courseID) {
        //TODO: are these requireNonNull needed?
        Objects.requireNonNull(studentID);
        Objects.requireNonNull(enrollmentID);
        Objects.requireNonNull(courseID);
        appendChange(new StudentEnrolledFromStudent(studentID, enrollmentID, courseID)).apply();
    }
}

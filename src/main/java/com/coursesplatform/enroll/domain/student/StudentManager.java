package com.coursesplatform.enroll.domain.student;

import com.coursesplatform.enroll.domain.student.events.*;
import com.coursesplatform.enroll.domain.student.values.StudentID;
import com.coursesplatform.enroll.domain.student.values.StudentManagerID;
import com.coursesplatform.enroll.generic.AggregateRoot;
import com.coursesplatform.enroll.generic.DomainEvent;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class StudentManager extends AggregateRoot <StudentManagerID> {
    protected Map <StudentID, Plan> studentPlan;
    protected List<Student> students;

    //TODO: should be private?
    public StudentManager (StudentManagerID entityID) {
        super (entityID);
        subscribe(new StudentManagerChange (this));
        appendChange(new StudentManagerCreated()).apply();
    }

    public static StudentManager from(StudentManagerID id, List <DomainEvent> events){
        StudentManager studentManager = new StudentManager(id);
        events.forEach(event -> studentManager.applyEvent(event));
        return studentManager;
    }

    public void registerStudent(String studentID, String name, String personalID, String email, String phone, String user, String password) {
        //TODO: are these requireNonNull needed?
        Objects.requireNonNull(studentID);
        Objects.requireNonNull(name);
        Objects.requireNonNull(personalID);
        Objects.requireNonNull(email);
        Objects.requireNonNull(phone);
        Objects.requireNonNull(user);
        Objects.requireNonNull(password);
        appendChange(new StudentRegistered(studentID, name, personalID, email, phone, user, password)).apply();
    }

    public void unregisterStudent(String studentID){
        Objects.requireNonNull(studentID);
        appendChange(new StudentUnregistered(studentID)).apply();
    }

    public void enrollStudent(String studentID, String enrollmentID, String courseManagerID, String courseID) {
        //TODO: are these requireNonNull needed?
        Objects.requireNonNull(studentID);
        Objects.requireNonNull(enrollmentID);
        Objects.requireNonNull(courseManagerID);
        Objects.requireNonNull(courseID);
        appendChange(new StudentEnrolled(studentID, enrollmentID, courseManagerID, courseID)).apply();
    }

    public void unenrollStudent(String studentID, String enrollmentID, String courseManagerID) {
        //TODO: are these requireNonNull needed?
        Objects.requireNonNull(studentID);
        Objects.requireNonNull(enrollmentID);
        Objects.requireNonNull(courseManagerID);
        appendChange(new StudentUnenrolled(studentID, enrollmentID, courseManagerID)).apply();
    }

}

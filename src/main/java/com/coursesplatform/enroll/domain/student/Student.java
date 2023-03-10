package com.coursesplatform.enroll.domain.student;

import com.coursesplatform.enroll.domain.course.CourseChange;
import com.coursesplatform.enroll.domain.course.values.EnrollmentID;
import com.coursesplatform.enroll.domain.sharedValues.Account;
import com.coursesplatform.enroll.domain.sharedValues.Data;
import com.coursesplatform.enroll.domain.student.events.*;
import com.coursesplatform.enroll.domain.student.values.StudentID;
import com.coursesplatform.enroll.generic.AggregateRoot;
import com.coursesplatform.enroll.generic.DomainEvent;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


public class Student extends AggregateRoot<StudentID> {

    protected Account account;
    protected Data personalData;
    protected Plan plan;
    protected Set<EnrollmentID> enrollments;


    private Student(StudentID entityID) {
        super(entityID);
        subscribe(new StudentChange(this));
    }

    public Student (
            StudentID entityID,
            Account account,
            Data personalData,
            Plan plan) {
        super (entityID);
        this.account=account;
        this.personalData=personalData;
        this.enrollments= new HashSet<>();
        this.plan = plan;
        subscribe(new StudentChange(this));
        appendChange(new StudentRegistered(
                personalData.value().name().value(),
                personalData.value().personalID().value(),
                personalData.value().email().value(),
                personalData.value().phone().value(),
                account.value().user().value(),
                account.value().password().value(),
                plan.identity().value(),
                plan.planName().value())).apply();
    }

    public static Student from(StudentID id, List<DomainEvent> events){
        Student student = new Student(id);
        events.forEach(event -> student.applyEvent(event));
        return student;
    }

    public Account account() {
        return account;
    }

    public Data personalData() {
        return personalData;
    }

    public Plan plan() {
        return plan;
    }

    public Set<EnrollmentID> enrollments() {
        return enrollments;
    }

    public void enroll (EnrollmentID enrollmentID){
        enrollments.add(enrollmentID);
    }

    public void unenroll (EnrollmentID enrollmentID){
        enrollments.remove(enrollmentID);
    }


    /* TODO: equivalent to remove from the db, create a method in the repository
    public void unregisterStudent(String studentID){
        Objects.requireNonNull(studentID);
        appendChange(new StudentUnregistered(studentID)).apply();
    }*/

    public void enrollStudent(String enrollmentID, String courseID) {
        Objects.requireNonNull(enrollmentID);
        Objects.requireNonNull(courseID);
        appendChange(new StudentEnrolled(enrollmentID, courseID)).apply();
    }

    public void unenrollStudent(String enrollmentID, String courseID) {
        Objects.requireNonNull(enrollmentID);
        Objects.requireNonNull(courseID);
        appendChange(new StudentUnenrolled(enrollmentID, courseID)).apply();
    }

    public void changePassword(String newPassword){
        Objects.requireNonNull(newPassword);
        appendChange(new PasswordUpdated(newPassword)).apply();
    }





}




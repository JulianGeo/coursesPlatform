package com.coursesplatform.enroll.domain.student;

import com.coursesplatform.enroll.domain.course.values.EnrollmentID;
import com.coursesplatform.enroll.domain.instructor.events.PasswordIUpdated;
import com.coursesplatform.enroll.domain.sharedValues.*;
import com.coursesplatform.enroll.domain.student.events.*;
import com.coursesplatform.enroll.domain.student.values.PlanID;
import com.coursesplatform.enroll.domain.student.values.PlanName;
import com.coursesplatform.enroll.domain.student.values.StudentID;
import com.coursesplatform.enroll.generic.EventChange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class StudentChange extends EventChange {

    public StudentChange(Student student) {
        apply((StudentRegistered event) -> {
            student.account=new Account(new User(event.getUser()), new Password(event.getPassword()));
            student.personalData = new Data(
                    new PersonalID(event.getPersonalID()),
                    new Name(event.getName()),
                    new Email(event.getEmail()),
                    new Phone(event.getPhone()));
            student.plan=new Plan(PlanID.of(event.getPlanID()), new PlanName(event.getPlanName()), StudentID.of(event.aggregateRootId()));
            student.enrollments=new HashSet<>();
        });

        /* TODO: this would be with a remove to the database
        apply((StudentUnregistered event) -> {
            Student studentToRemove = studentManager.students.stream()
                    .filter(student -> student.identity().equals(StudentID.of(event.getStudentID())))
                    .findFirst()
                    .orElseThrow();
            studentManager.students.remove(studentToRemove);
        });*/

        apply((StudentEnrolled event) -> {
            student.enroll(EnrollmentID.of(event.getEnrollmentID()));
        });

        apply((StudentUnenrolled event) -> {
            student.unenroll(EnrollmentID.of(event.getEnrollmentID()));
        });

        apply((PasswordUpdated event)-> {
            Account oldAccount = student.account;
            student.account =  new Account(
                    oldAccount.value().user(),
                    new Password(event.getPassword()));
        });

    }


}

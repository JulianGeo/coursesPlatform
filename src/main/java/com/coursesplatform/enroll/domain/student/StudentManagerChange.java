package com.coursesplatform.enroll.domain.student;

import com.coursesplatform.enroll.domain.course.values.EnrollmentID;
import com.coursesplatform.enroll.domain.sharedValues.*;
import com.coursesplatform.enroll.domain.student.events.StudentEnrolled;
import com.coursesplatform.enroll.domain.student.events.StudentManagerCreated;
import com.coursesplatform.enroll.domain.student.events.StudentRegistered;
import com.coursesplatform.enroll.domain.student.values.PlanID;
import com.coursesplatform.enroll.domain.student.values.PlanName;
import com.coursesplatform.enroll.domain.student.values.StudentID;
import com.coursesplatform.enroll.generic.EventChange;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentManagerChange extends EventChange {

    public StudentManagerChange(StudentManager studentManager) {
        apply((StudentManagerCreated event) -> {
            studentManager.studentPlan=new HashMap<>();
            studentManager.students=new ArrayList<>();
        });

        apply((StudentRegistered event) -> {
            Student student=new Student(StudentID.of(event.getStudentID()),
                    new Account(new User(event.getUser()),new Password(event.getPassword())),
                    new Data(new PersonalID(event.getPersonalID()),
                            new Name(event.getName()),
                            new Email(event.getEmail()),
                            new Phone(event.getPhone())));
            Plan plan=new Plan(PlanID.of(event.getPlanID()), new PlanName(event.getPlanName()), StudentID.of(event.getStudentID()));
            studentManager.students.add(student);
            studentManager.studentPlan.put(student.identity(), plan);

        });

        apply((StudentEnrolled event) -> {
            Student student=studentManager.students.stream()
                            .filter(studentFiltered -> studentFiltered.identity().equals(event.getStudentID()))
                            .findFirst().orElseThrow();
            student.enroll(EnrollmentID.of(event.getEnrollmentID()));
        });
    }


}

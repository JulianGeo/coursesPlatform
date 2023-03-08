package com.coursesplatform.enroll.domain.student;

import com.coursesplatform.enroll.domain.course.values.CourseID;
import com.coursesplatform.enroll.domain.course.values.EnrollmentID;
import com.coursesplatform.enroll.domain.sharedValues.Account;
import com.coursesplatform.enroll.domain.sharedValues.Data;
import com.coursesplatform.enroll.domain.student.values.StudentID;
import com.coursesplatform.enroll.generic.Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Student extends Entity<StudentID> {

    private Account account;
    private Data personalData;
    private Set<EnrollmentID> enrollments;

    public Student (StudentID entityID, Account account, Data personalData) {
        super (entityID);
        this.account=account;
        this.personalData=personalData;
        this.enrollments= new HashSet<>();
    }

    public void enroll (EnrollmentID enrollmentID){
        enrollments.add(enrollmentID);
    }

    public void unenroll (EnrollmentID enrollmentID){
        enrollments.remove(enrollmentID);
    }




}




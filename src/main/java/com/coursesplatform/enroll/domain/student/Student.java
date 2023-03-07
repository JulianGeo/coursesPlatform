package com.coursesplatform.enroll.domain.student;

import com.coursesplatform.enroll.domain.course.values.EnrollmentID;
import com.coursesplatform.enroll.domain.sharedValues.Account;
import com.coursesplatform.enroll.domain.sharedValues.Data;
import com.coursesplatform.enroll.domain.student.values.StudentID;
import com.coursesplatform.enroll.generic.Entity;

import java.util.List;

public class Student extends Entity<StudentID> {

    private Account account;
    private Data personalData;
    private List<EnrollmentID> enrollments;

    public Student (StudentID entityID, Account account, Data personalData, List <EnrollmentID> enrollments) {
        super (entityID);
        this.account=account;
        this.personalData=personalData;
        this.enrollments=enrollments;
    }


}




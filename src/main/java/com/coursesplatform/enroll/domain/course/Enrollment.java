package com.coursesplatform.enroll.domain.course;

import com.coursesplatform.enroll.domain.course.values.CourseID;
import com.coursesplatform.enroll.domain.course.values.Status;
import com.coursesplatform.enroll.domain.course.values.EnrollmentID;
import com.coursesplatform.enroll.domain.sharedValues.Date;
import com.coursesplatform.enroll.domain.student.values.StudentID;
import com.coursesplatform.enroll.generic.Entity;

public class Enrollment extends Entity<EnrollmentID> {

    private Date enrollmentDate;
    private Status enrollmentStatus;
    private StudentID studentID;
    private CourseID courseID;

    public Enrollment (EnrollmentID entityID, Date enrollmentDate, Status enrollmentStatus, StudentID studentID, CourseID courseID) {
        super (entityID);
        this.enrollmentDate=enrollmentDate;
        this.enrollmentStatus=enrollmentStatus;
        this.studentID=studentID;
        this.courseID=courseID;
    }

    public Date enrollmentDate () {
        return enrollmentDate;
    }

    public Status enrollmentStatus () {
        return enrollmentStatus;
    }

    public StudentID studentID () {
        return studentID;
    }

    public CourseID courseID () {
        return courseID;
    }
}

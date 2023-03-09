package com.coursesplatform.enroll.domain.course;

import com.coursesplatform.enroll.domain.course.events.CourseManagerCreated;
import com.coursesplatform.enroll.domain.course.events.StudentEnrolledFromStudent;
import com.coursesplatform.enroll.domain.course.events.StudentUnenrolledFromStudent;
import com.coursesplatform.enroll.domain.course.values.CourseID;
import com.coursesplatform.enroll.domain.course.values.EnrollmentID;
import com.coursesplatform.enroll.domain.student.Student;
import com.coursesplatform.enroll.domain.student.events.StudentEnrolled;
import com.coursesplatform.enroll.domain.student.values.StudentID;
import com.coursesplatform.enroll.generic.EventChange;

import java.util.ArrayList;

public class CourseManagerChange extends EventChange {

    public CourseManagerChange (CourseManager courseManager) {
        apply ((CourseManagerCreated event) -> {
            courseManager.enrollmentList = new ArrayList<> ();
            courseManager.courses = new ArrayList<> ();
        });

        apply ((StudentEnrolledFromStudent event) ->{
            courseManager.enrollmentList.add(new Enrollment(
                    EnrollmentID.of(event.getEnrollmentID()),
                    StudentID.of(event.getStudentID()),
                    CourseID.of(event.getCourseID())
                    ));
        });

        apply ((StudentUnenrolledFromStudent event) ->{
            Enrollment enrollment=courseManager.enrollmentList.stream()
                    .filter(enrollmentFiltered -> enrollmentFiltered.identity().equals(event.getEnrollmentID()))
                    .findFirst().orElseThrow();
            courseManager.enrollmentList.remove(enrollment);
        });
    }


}

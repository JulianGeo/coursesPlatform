package com.coursesplatform.enroll.domain.course;

import com.coursesplatform.enroll.domain.course.events.CourseCreated;
import com.coursesplatform.enroll.domain.course.events.DescriptionChanged;
import com.coursesplatform.enroll.domain.course.events.StudentEnrolledFromStudent;
import com.coursesplatform.enroll.domain.course.events.StudentUnenrolledFromStudent;
import com.coursesplatform.enroll.domain.course.values.CourseID;
import com.coursesplatform.enroll.domain.course.values.Description;
import com.coursesplatform.enroll.domain.course.values.EnrollmentID;
import com.coursesplatform.enroll.domain.instructor.values.InstructorID;
import com.coursesplatform.enroll.domain.student.values.StudentID;
import com.coursesplatform.enroll.generic.EventChange;

import java.util.ArrayList;

public class CourseChange extends EventChange {

    public CourseChange(Course course) {
        apply ((CourseCreated event) -> {
            course.instructorID = InstructorID.of(event.getInstructorID());
            course.description = new Description(event.getDescription());
            course.enrollments = new ArrayList<> ();
            course.ratings = new ArrayList<> ();
            course.reviews  = new ArrayList<> ();
        });

        apply ((StudentEnrolledFromStudent event) ->{
            course.enrollments.add(new Enrollment(
                    EnrollmentID.of(event.getEnrollmentID()),
                    StudentID.of(event.getStudentID()),
                    CourseID.of(event.aggregateRootId())
                    ));
        });

        apply ((StudentUnenrolledFromStudent event) ->{
            Enrollment enrollment=course.enrollments.stream()
                    .filter(enrollmentFiltered -> enrollmentFiltered.identity().value().equals(event.getEnrollmentID()))
                    .findFirst().orElseThrow();
            course.enrollments.remove(enrollment);
        });

        apply ((DescriptionChanged event) -> {
            course.description = new Description(event.getDescription());
        });
    }


}

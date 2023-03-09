package com.coursesplatform.enroll.business.course;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.business.commons.UseCaseForCommand;
import com.coursesplatform.enroll.business.commons.UseCaseForEvent;
import com.coursesplatform.enroll.domain.course.CourseManager;
import com.coursesplatform.enroll.domain.course.values.CourseManagerID;
import com.coursesplatform.enroll.domain.student.StudentManager;
import com.coursesplatform.enroll.domain.student.commands.CreateStudentManagerCommand;
import com.coursesplatform.enroll.domain.student.events.StudentEnrolled;
import com.coursesplatform.enroll.domain.student.values.StudentManagerID;
import com.coursesplatform.enroll.generic.DomainEvent;

import java.util.List;

public class StudentEnrolledUseCase implements UseCaseForEvent<StudentEnrolled> {

    private final EventsRepository eventsRepository;

    public StudentEnrolledUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(StudentEnrolled event) {

        List<DomainEvent> userEvents =  eventsRepository.findByAggregatedRootId(event.getCourseManagerID());
        CourseManager courseManager=CourseManager.from(CourseManagerID.of((event.getCourseManagerID())), userEvents);
        courseManager.enrollStudent(event.getStudentID(), event.getEnrollmentID(), event.getCourseID());
        return courseManager.getUncommittedChanges().stream().map(eventUncommitted->eventsRepository.saveEvent(eventUncommitted)).toList();

    }
}

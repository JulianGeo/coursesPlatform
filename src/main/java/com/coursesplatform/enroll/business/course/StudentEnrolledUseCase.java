package com.coursesplatform.enroll.business.course;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.business.commons.UseCaseForEvent;
import com.coursesplatform.enroll.domain.course.CourseManager;
import com.coursesplatform.enroll.domain.course.values.CourseManagerID;
import com.coursesplatform.enroll.domain.student.events.StudentEnrolled;
import com.coursesplatform.enroll.generic.DomainEvent;

import java.util.List;

public class StudentEnrolledUseCase implements UseCaseForEvent<StudentEnrolled> {

    private final EventsRepository eventsRepository;

    public StudentEnrolledUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(StudentEnrolled event) {

        List<DomainEvent> courseEvents =  eventsRepository.findByAggregatedRootId(event.getCourseManagerID());
        CourseManager courseManager=CourseManager.from(CourseManagerID.of((event.getCourseManagerID())), courseEvents);
        courseManager.enrollStudent(event.getStudentID(), event.getEnrollmentID(), event.getCourseID());
        return courseManager.getUncommittedChanges().stream().map(eventUncommitted->eventsRepository.saveEvent(eventUncommitted)).toList();

    }
}

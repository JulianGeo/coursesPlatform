package com.coursesplatform.enroll.business.course;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.business.commons.UseCaseForEvent;
import com.coursesplatform.enroll.domain.course.CourseManager;
import com.coursesplatform.enroll.domain.course.values.CourseManagerID;
import com.coursesplatform.enroll.domain.student.events.StudentEnrolled;
import com.coursesplatform.enroll.domain.student.events.StudentUnenrolled;
import com.coursesplatform.enroll.generic.DomainEvent;

import java.util.List;

public class StudentUnenrolledUseCase implements UseCaseForEvent<StudentUnenrolled> {

    private final EventsRepository eventsRepository;

    public StudentUnenrolledUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(StudentUnenrolled event) {

        List<DomainEvent> courseEvents =  eventsRepository.findByAggregatedRootId(event.getCourseManagerID());
        CourseManager courseManager=CourseManager.from(CourseManagerID.of((event.getCourseManagerID())), courseEvents);
        courseManager.unenenrollStudent(event.getEnrollmentID());
        return courseManager.getUncommittedChanges().stream().map(eventUncommitted->eventsRepository.saveEvent(eventUncommitted)).toList();

    }
}

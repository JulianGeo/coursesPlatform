package com.coursesplatform.enroll.business.course;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.business.commons.UseCaseForEvent;
import com.coursesplatform.enroll.domain.course.Course;
import com.coursesplatform.enroll.domain.course.values.CourseID;
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

        List<DomainEvent> courseEvents =  eventsRepository.findByAggregatedRootId(event.getCourseID());
        Course course=Course.from(CourseID.of((event.getCourseID())), courseEvents);
        course.unenenrollStudent(event.getEnrollmentID());
        return course.getUncommittedChanges().stream().map(eventUncommitted->eventsRepository.saveEvent(eventUncommitted)).toList();

    }
}

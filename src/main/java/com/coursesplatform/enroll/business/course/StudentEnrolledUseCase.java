package com.coursesplatform.enroll.business.course;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.business.commons.UseCaseForEvent;
import com.coursesplatform.enroll.domain.course.Course;
import com.coursesplatform.enroll.domain.course.values.CourseID;
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

        List<DomainEvent> courseEvents =  eventsRepository.findByAggregatedRootId(event.getCourseID());
        Course course=Course.from(CourseID.of((event.getCourseID())), courseEvents);
        course.enrollStudent(event.aggregateRootId(), event.getEnrollmentID(), event.getCourseID());
        return course.getUncommittedChanges().stream().map(eventUncommitted->eventsRepository.saveEvent(eventUncommitted)).toList();
    }
}

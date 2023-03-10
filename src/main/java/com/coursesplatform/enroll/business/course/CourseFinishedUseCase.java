package com.coursesplatform.enroll.business.course;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.business.commons.UseCaseForEvent;
import com.coursesplatform.enroll.domain.course.Course;
import com.coursesplatform.enroll.domain.course.values.CourseID;
import com.coursesplatform.enroll.domain.student.events.CourseFinished;
import com.coursesplatform.enroll.generic.DomainEvent;

import java.util.List;

public class CourseFinishedUseCase implements UseCaseForEvent<CourseFinished> {
    private final EventsRepository eventsRepository;

    public CourseFinishedUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(CourseFinished event) {
        List<DomainEvent> courseEvents =  eventsRepository.findByAggregatedRootId(event.getCourseID());
        Course course=Course.from(CourseID.of((event.getCourseID())), courseEvents);
        course.finishCourse(event.getEnrollmentID());
        return course.getUncommittedChanges().stream().map(newEvents->eventsRepository.saveEvent(newEvents)).toList();
    }
}

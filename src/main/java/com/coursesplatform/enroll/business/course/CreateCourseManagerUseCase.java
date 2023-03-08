package com.coursesplatform.enroll.business.course;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.business.commons.UseCaseForCommand;
import com.coursesplatform.enroll.domain.course.CourseManager;
import com.coursesplatform.enroll.domain.course.commands.CreateCourseManagerCommand;
import com.coursesplatform.enroll.domain.course.values.CourseManagerID;
import com.coursesplatform.enroll.generic.DomainEvent;

import java.util.List;

public class CreateCourseManagerUseCase implements UseCaseForCommand <CreateCourseManagerCommand> {

        private final EventsRepository eventsRepository;

        public CreateCourseManagerUseCase(EventsRepository eventsRepository) {
            this.eventsRepository = eventsRepository;
        }

        @Override
        public List <DomainEvent> apply(CreateCourseManagerCommand command) {

            CourseManager courseManager = new CourseManager (CourseManagerID.of(command.getCourseManagerID ()));
            return courseManager.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).toList();
        }
}


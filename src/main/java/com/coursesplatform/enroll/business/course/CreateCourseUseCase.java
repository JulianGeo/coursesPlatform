package com.coursesplatform.enroll.business.course;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.business.commons.UseCaseForCommand;
import com.coursesplatform.enroll.domain.course.Course;
import com.coursesplatform.enroll.domain.course.commands.CreateCourseCommand;
import com.coursesplatform.enroll.domain.course.values.CourseID;
import com.coursesplatform.enroll.domain.course.values.Description;
import com.coursesplatform.enroll.domain.instructor.values.InstructorID;
import com.coursesplatform.enroll.generic.DomainEvent;

import java.util.List;

public class CreateCourseUseCase implements UseCaseForCommand <CreateCourseCommand> {

        private final EventsRepository eventsRepository;

        public CreateCourseUseCase(EventsRepository eventsRepository) {
            this.eventsRepository = eventsRepository;
        }

        @Override
        public List <DomainEvent> apply(CreateCourseCommand command) {

            Course course = new Course (
                    CourseID.of((command.getCourseID ())),
                    InstructorID.of(command.getInstructorID()),
                    new Description(command.getDescription()));

            return course.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).toList();
        }
}


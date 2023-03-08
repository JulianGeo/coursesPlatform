package com.coursesplatform.enroll.business.instructor;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.business.commons.UseCaseForCommand;
import com.coursesplatform.enroll.domain.course.commands.CreateCourseManagerCommand;
import com.coursesplatform.enroll.domain.instructor.InstructorManager;
import com.coursesplatform.enroll.domain.instructor.commands.CreateInstructorManagerCommand;
import com.coursesplatform.enroll.domain.instructor.values.InstructorManagerID;
import com.coursesplatform.enroll.generic.DomainEvent;

import java.util.List;

public class CreateInstructorManagerUseCase implements UseCaseForCommand <CreateInstructorManagerCommand> {

private final EventsRepository eventsRepository;

public CreateInstructorManagerUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
}

@Override
public List <DomainEvent> apply(CreateInstructorManagerCommand command) {

        InstructorManager instructorManager = new InstructorManager (InstructorManagerID.of(command.getInstructorManagerID ()));
        return instructorManager.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).toList();
        }
}

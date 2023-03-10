package com.coursesplatform.enroll.business.instructor;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.business.commons.UseCaseForCommand;
import com.coursesplatform.enroll.domain.instructor.Instructor;
import com.coursesplatform.enroll.domain.instructor.commands.UpdatePasswordICommand;
import com.coursesplatform.enroll.domain.instructor.values.InstructorID;
import com.coursesplatform.enroll.generic.DomainEvent;

import java.util.List;

public class UpdatePasswordIUseCase implements UseCaseForCommand<UpdatePasswordICommand> {
    private final EventsRepository eventsRepository;
    public UpdatePasswordIUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(UpdatePasswordICommand command) {
        List<DomainEvent> studentEvents =  eventsRepository.findByAggregatedRootId(command.getInstructorID());
        Instructor instructor=Instructor.from(InstructorID.of((command.getInstructorID())), studentEvents);
        instructor.changePassword(command.getPassword());
        return instructor.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).toList();
    }


}

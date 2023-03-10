package com.coursesplatform.enroll.business.instructor;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.business.commons.UseCaseForCommand;
import com.coursesplatform.enroll.domain.instructor.Instructor;
import com.coursesplatform.enroll.domain.instructor.commands.MakeUnavailableCommand;
import com.coursesplatform.enroll.domain.instructor.commands.UpdatePasswordICommand;
import com.coursesplatform.enroll.domain.instructor.values.InstructorID;
import com.coursesplatform.enroll.generic.DomainEvent;

import java.util.List;

public class MakeUnavailableUseCase implements UseCaseForCommand<MakeUnavailableCommand> {

    private final EventsRepository eventsRepository;
    public MakeUnavailableUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(MakeUnavailableCommand command) {
        List<DomainEvent> studentEvents =  eventsRepository.findByAggregatedRootId(command.getInstructorID());
        Instructor instructor=Instructor.from(InstructorID.of((command.getInstructorID())), studentEvents);
        instructor.makeUnavailable(command.isAvailable());
        return instructor.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).toList();
    }
}

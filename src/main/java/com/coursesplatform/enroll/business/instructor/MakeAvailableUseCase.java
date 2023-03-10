package com.coursesplatform.enroll.business.instructor;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.business.commons.UseCaseForCommand;
import com.coursesplatform.enroll.domain.instructor.Instructor;
import com.coursesplatform.enroll.domain.instructor.commands.MakeAvailableCommand;
import com.coursesplatform.enroll.domain.instructor.commands.MakeUnavailableCommand;
import com.coursesplatform.enroll.domain.instructor.values.InstructorID;
import com.coursesplatform.enroll.generic.DomainEvent;

import java.util.List;

public class MakeAvailableUseCase implements UseCaseForCommand<MakeAvailableCommand> {
    private final EventsRepository eventsRepository;
    public MakeAvailableUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(MakeAvailableCommand command) {
        List<DomainEvent> studentEvents =  eventsRepository.findByAggregatedRootId(command.getInstructorID());
        Instructor instructor=Instructor.from(InstructorID.of((command.getInstructorID())), studentEvents);
        instructor.makeAvailable(command.isAvailable());
        return instructor.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).toList();
    }
}

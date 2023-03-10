package com.coursesplatform.enroll.business.instructor;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.business.commons.UseCaseForCommand;
import com.coursesplatform.enroll.domain.instructor.Instructor;
import com.coursesplatform.enroll.domain.instructor.commands.RegisterInstructorCommand;
import com.coursesplatform.enroll.domain.instructor.values.InstructorID;
import com.coursesplatform.enroll.domain.sharedValues.*;
import com.coursesplatform.enroll.generic.DomainEvent;

import java.util.List;

public class RegisterInstructorUseCase implements UseCaseForCommand <RegisterInstructorCommand> {

private final EventsRepository eventsRepository;

public RegisterInstructorUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
}

@Override
public List <DomainEvent> apply(RegisterInstructorCommand command) {

        Instructor instructor = new Instructor (
                InstructorID.of((command.getInstructorID())),
                new Account(new User(command.getUser()), new Password(command.getPassword())),
                new Data(new PersonalID(command.getPersonalID()),
                        new Name(command.getName()),
                        new Email(command.getEmail()),
                        new Phone(command.getPhone())));

        return instructor.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).toList();
        }
}

package com.coursesplatform.enroll.business.student;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.business.commons.UseCaseForCommand;
import com.coursesplatform.enroll.domain.sharedValues.*;
import com.coursesplatform.enroll.domain.student.Plan;
import com.coursesplatform.enroll.domain.student.Student;
import com.coursesplatform.enroll.domain.student.commands.RegisterStudentCommand;
import com.coursesplatform.enroll.domain.student.values.PlanID;
import com.coursesplatform.enroll.domain.student.values.PlanName;
import com.coursesplatform.enroll.domain.student.values.StudentID;
import com.coursesplatform.enroll.domain.student.values.StudentManagerID;
import com.coursesplatform.enroll.generic.DomainEvent;

import java.util.List;

public class RegisterStudentUseCase implements UseCaseForCommand<RegisterStudentCommand> {
    private final EventsRepository eventsRepository;

    public RegisterStudentUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(RegisterStudentCommand command) {
        Student student= new Student(
                StudentID.of(command.getStudentID()),
                new Account( new User(command.getUser()), new Password(command.getPassword())),
                new Data(new PersonalID(command.getPersonalID()),
                        new Name(command.getName()),
                        new Email(command.getEmail()),
                        new Phone(command.getPhone())),
                new Plan(PlanID.of(command.getPlanID()),
                        new PlanName(command.getPlanName()),
                        StudentID.of(command.getStudentID())));

        return student.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).toList();
    }


}
package com.coursesplatform.enroll.business.student;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.business.commons.UseCaseForCommand;
import com.coursesplatform.enroll.domain.student.StudentManager;
import com.coursesplatform.enroll.domain.student.commands.RegisterStudentCommand;
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
        List<DomainEvent> userEvents =  eventsRepository.findByAggregatedRootId(command.getStudentManagerID());
        StudentManager studentManager=StudentManager.from(StudentManagerID.of((command.getStudentManagerID())), userEvents);

        studentManager.registerStudent(command.getStudentID(), command.getName(), command.getPersonalID(), command.getEmail(), command.getPhone(), command.getUser(), command.getPassword());
        return studentManager.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).toList();
    }


}
package com.coursesplatform.enroll.business.student;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.business.commons.UseCaseForCommand;
import com.coursesplatform.enroll.domain.student.commands.UnregisterStudentCommand;
import com.coursesplatform.enroll.domain.student.values.StudentManagerID;
import com.coursesplatform.enroll.generic.DomainEvent;

import java.util.List;
/* TODO: implements deleting from the DB
public class UnregisterStudentUseCase implements UseCaseForCommand<UnregisterStudentCommand> {
    private final EventsRepository eventsRepository;

    public UnregisterStudentUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(UnregisterStudentCommand command) {
        List<DomainEvent> studentEvents =  eventsRepository.findByAggregatedRootId(command.getStudentManagerID());
        StudentManager studentManager=StudentManager.from(StudentManagerID.of((command.getStudentManagerID())), studentEvents);

        studentManager.unregisterStudent(command.getStudentID());
        return studentManager.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).toList();
    }
}*/
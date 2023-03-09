package com.coursesplatform.enroll.business.student;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.business.commons.UseCaseForCommand;
import com.coursesplatform.enroll.domain.student.StudentManager;
import com.coursesplatform.enroll.domain.student.commands.EnrollStudentCommand;
import com.coursesplatform.enroll.domain.student.commands.RegisterStudentCommand;
import com.coursesplatform.enroll.domain.student.values.StudentManagerID;
import com.coursesplatform.enroll.generic.DomainEvent;

import java.util.List;

public class EnrollStudentUseCase implements UseCaseForCommand<EnrollStudentCommand> {
    private final EventsRepository eventsRepository;

    public EnrollStudentUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(EnrollStudentCommand command) {
        List<DomainEvent> userEvents =  eventsRepository.findByAggregatedRootId(command.getStudentManagerID());
        StudentManager studentManager=StudentManager.from(StudentManagerID.of((command.getStudentManagerID())), userEvents);
        studentManager.enrollStudent(command.getStudentID(), command.getEnrollmentID(), command.getCourseManagerID(), command.getCourseID());
        return studentManager.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).toList();
    }


}
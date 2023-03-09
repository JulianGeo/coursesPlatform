package com.coursesplatform.enroll.business.student;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.business.commons.UseCaseForCommand;
import com.coursesplatform.enroll.domain.student.StudentManager;
import com.coursesplatform.enroll.domain.student.commands.EnrollStudentCommand;
import com.coursesplatform.enroll.domain.student.commands.UnenrollStudentCommand;
import com.coursesplatform.enroll.domain.student.values.StudentManagerID;
import com.coursesplatform.enroll.generic.DomainEvent;

import java.util.List;

public class UnenrollStudentUseCase implements UseCaseForCommand<UnenrollStudentCommand> {
    private final EventsRepository eventsRepository;

    public UnenrollStudentUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(UnenrollStudentCommand command) {
        List<DomainEvent> studentEvents =  eventsRepository.findByAggregatedRootId(command.getStudentManagerID());
        StudentManager studentManager=StudentManager.from(StudentManagerID.of((command.getStudentManagerID())), studentEvents);
        studentManager.unenrollStudent(command.getStudentID(), command.getEnrollmentID(), command.getCourseManagerID());
        return studentManager.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).toList();
    }


}
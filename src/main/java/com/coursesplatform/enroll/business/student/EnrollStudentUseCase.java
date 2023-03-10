package com.coursesplatform.enroll.business.student;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.business.commons.UseCaseForCommand;
import com.coursesplatform.enroll.domain.student.Student;
import com.coursesplatform.enroll.domain.student.commands.EnrollStudentCommand;
import com.coursesplatform.enroll.domain.student.values.StudentID;
import com.coursesplatform.enroll.generic.DomainEvent;

import java.util.List;

public class EnrollStudentUseCase implements UseCaseForCommand<EnrollStudentCommand> {
    private final EventsRepository eventsRepository;

    public EnrollStudentUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(EnrollStudentCommand command) {
        List<DomainEvent> studentEvents =  eventsRepository.findByAggregatedRootId(command.getStudentID());
        Student student=Student.from(StudentID.of((command.getStudentID())), studentEvents);
        student.enrollStudent(command.getEnrollmentID(), command.getCourseID());
        return student.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).toList();
    }
}
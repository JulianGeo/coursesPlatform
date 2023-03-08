package com.coursesplatform.enroll.business.student;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.business.commons.UseCaseForCommand;
import com.coursesplatform.enroll.domain.instructor.InstructorManager;
import com.coursesplatform.enroll.domain.instructor.commands.CreateInstructorManagerCommand;
import com.coursesplatform.enroll.domain.instructor.values.InstructorManagerID;
import com.coursesplatform.enroll.domain.student.StudentManager;
import com.coursesplatform.enroll.domain.student.commands.CreateStudentManagerCommand;
import com.coursesplatform.enroll.domain.student.values.StudentManagerID;
import com.coursesplatform.enroll.generic.DomainEvent;

import java.util.List;

public class CreateStudentManagerUseCase implements UseCaseForCommand <CreateStudentManagerCommand> {

    private final EventsRepository eventsRepository;

    public CreateStudentManagerUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List <DomainEvent> apply(CreateStudentManagerCommand command) {

        StudentManager studentManager = new StudentManager (StudentManagerID.of(command.getStudentManagerID ()));
        return studentManager.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).toList();
    }
}


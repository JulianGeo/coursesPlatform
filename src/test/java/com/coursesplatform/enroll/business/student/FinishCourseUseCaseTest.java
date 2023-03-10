package com.coursesplatform.enroll.business.student;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.domain.student.commands.FinishCourseCommand;
import com.coursesplatform.enroll.domain.student.commands.UpdatePasswordCommand;
import com.coursesplatform.enroll.domain.student.events.CourseFinished;
import com.coursesplatform.enroll.domain.student.events.PasswordUpdated;
import com.coursesplatform.enroll.domain.student.events.StudentEnrolled;
import com.coursesplatform.enroll.domain.student.events.StudentRegistered;
import com.coursesplatform.enroll.generic.DomainEvent;
import com.coursesplatform.enroll.utils.MocksGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FinishCourseUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;
    private FinishCourseUseCase finishCourseUseCase;

    @BeforeEach
    void setup(){
        finishCourseUseCase = new FinishCourseUseCase(eventsRepository);
    }


    @Test
    void successfulScenario() {

        //Mocking events related with the aggregateRoot retrieved from DB
        StudentRegistered studentRegistered = MocksGenerator.studentRegistered();
        studentRegistered.setAggregateRootId("StudentID");

        StudentEnrolled studentEnrolled = new StudentEnrolled("EnrollmentID", "CourseID");
        studentEnrolled.setAggregateRootId("StudentID");

        List<DomainEvent> courseEvents = new ArrayList<>();
        courseEvents.add(studentRegistered);
        courseEvents.add(studentEnrolled);

        //Mocking the input command of use case
        FinishCourseCommand finishCourseCommand = new FinishCourseCommand( "StudentID", "CourseID","EnrollmentID");

        Mockito.when(eventsRepository.findByAggregatedRootId(ArgumentMatchers.any(String.class)))
                .thenReturn(courseEvents);

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(DomainEvent.class)))
                .thenAnswer( invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        //Action
        List<DomainEvent> domainEventList = finishCourseUseCase.apply(finishCourseCommand);

        Assertions.assertEquals(1,domainEventList.size());
        Assertions.assertEquals("EnrollmentID",
                (((CourseFinished)domainEventList.get(0))).getEnrollmentID());
    }

}
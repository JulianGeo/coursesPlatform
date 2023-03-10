package com.coursesplatform.enroll.business.student;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.domain.student.commands.EnrollStudentCommand;
import com.coursesplatform.enroll.domain.student.commands.UnenrollStudentCommand;
import com.coursesplatform.enroll.domain.student.events.StudentEnrolled;
import com.coursesplatform.enroll.domain.student.events.StudentRegistered;
import com.coursesplatform.enroll.domain.student.events.StudentUnenrolled;
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
class UnenrollStudentUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;
    private UnenrollStudentUseCase unenrollStudentUseCase;

    @BeforeEach
    void setup(){
        unenrollStudentUseCase = new UnenrollStudentUseCase(eventsRepository);
    }


    @Test
    void successfulScenario(){

        StudentRegistered studentRegistered = MocksGenerator.studentRegistered();
        studentRegistered.setAggregateRootId("StudentID");

        StudentEnrolled studentEnrolled = new StudentEnrolled("EnrollmentID", "CourseID");
        studentEnrolled.setAggregateRootId("StudentID");

        StudentEnrolled studentEnrolled2 = new StudentEnrolled("EnrollmentID2", "CourseID");
        studentEnrolled.setAggregateRootId("StudentID");


        UnenrollStudentCommand unenrollStudentCommand = new UnenrollStudentCommand(
                "StudentID",
                "EnrollmentID",
                "CourseID"
        );

        //Mocking events related with the aggregateRoot retrieved from DB
        List<DomainEvent> studentEvents = new ArrayList<>();
        studentEvents.add(studentRegistered);
        studentEvents.add(studentEnrolled);
        studentEvents.add(studentEnrolled2);


        Mockito.when(eventsRepository.findByAggregatedRootId(ArgumentMatchers.any(String.class)))
                .thenReturn(studentEvents);

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(DomainEvent.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = unenrollStudentUseCase.apply(unenrollStudentCommand);

        Assertions.assertEquals(1,domainEventList.size());
        Assertions.assertEquals("EnrollmentID",
                ((StudentUnenrolled)domainEventList.get(0)).getEnrollmentID());

    }
}
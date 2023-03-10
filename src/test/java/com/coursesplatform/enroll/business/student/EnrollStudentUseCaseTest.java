package com.coursesplatform.enroll.business.student;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.domain.student.commands.EnrollStudentCommand;
import com.coursesplatform.enroll.domain.student.commands.RegisterStudentCommand;
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

//TODO: this doesnt work yet
@ExtendWith(MockitoExtension.class)
class EnrollStudentUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;
    private EnrollStudentUseCase enrollStudentUseCase;

    @BeforeEach
    void setup(){
        enrollStudentUseCase = new EnrollStudentUseCase(eventsRepository);
    }


    @Test
    void successfulScenario(){

        StudentRegistered studentRegistered = MocksGenerator.studentRegistered();
        studentRegistered.setAggregateRootId("StudentID");

        EnrollStudentCommand enrollStudentCommand = new EnrollStudentCommand(
                "StudentID",
                "EnrollmentID",
                "CourseID"
        );

        //Mocking events related with the aggregateRoot retrieved from DB
        List<DomainEvent> studentEvents = new ArrayList<>();
        studentEvents.add(studentRegistered);


        Mockito.when(eventsRepository.findByAggregatedRootId(ArgumentMatchers.any(String.class)))
                .thenReturn(studentEvents);

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(StudentEnrolled.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = enrollStudentUseCase.apply(enrollStudentCommand);

        Assertions.assertEquals(1,domainEventList.size());
        Assertions.assertEquals("EnrollmentID",
                ((StudentEnrolled)domainEventList.get(0)).getEnrollmentID());

    }


}
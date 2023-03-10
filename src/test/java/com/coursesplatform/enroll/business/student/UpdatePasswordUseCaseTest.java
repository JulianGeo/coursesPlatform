package com.coursesplatform.enroll.business.student;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.domain.student.commands.UpdatePasswordCommand;
import com.coursesplatform.enroll.domain.student.events.PasswordUpdated;
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
class UpdatePasswordUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;
    private UpdatePasswordUseCase updatePasswordUseCase;

    @BeforeEach
    void setup(){
        updatePasswordUseCase = new UpdatePasswordUseCase(eventsRepository);
    }


    @Test
    void successfulScenario() {

        //Mocking the course creation event
        StudentRegistered studentRegistered = MocksGenerator.studentRegistered();
        studentRegistered.setAggregateRootId("StudentID");

        //Mocking the input command of the event driven use case
        UpdatePasswordCommand updatePasswordCommand = new UpdatePasswordCommand( "StudentID", "New password");

        //Mocking events related with the aggregateRoot retrieved from DB
        List<DomainEvent> courseEvents = new ArrayList<>();
        courseEvents.add(studentRegistered);

        Mockito.when(eventsRepository.findByAggregatedRootId(ArgumentMatchers.any(String.class)))
                .thenReturn(courseEvents);

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(DomainEvent.class)))
                .thenAnswer( invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        //Action
        List<DomainEvent> domainEventList = updatePasswordUseCase.apply(updatePasswordCommand);

        Assertions.assertEquals(1,domainEventList.size());
        Assertions.assertEquals("New password",
                (((PasswordUpdated)domainEventList.get(0))).getPassword());
    }



}
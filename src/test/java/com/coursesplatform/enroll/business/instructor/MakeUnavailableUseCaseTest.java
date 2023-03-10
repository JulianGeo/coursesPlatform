package com.coursesplatform.enroll.business.instructor;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.domain.instructor.commands.MakeUnavailableCommand;
import com.coursesplatform.enroll.domain.instructor.commands.UpdatePasswordICommand;
import com.coursesplatform.enroll.domain.instructor.events.InstructorRegistered;
import com.coursesplatform.enroll.domain.instructor.events.PasswordIUpdated;
import com.coursesplatform.enroll.domain.instructor.events.UnavailableMade;
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
class MakeUnavailableUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;
    private MakeUnavailableUseCase makeUnavailableUseCase;

    @BeforeEach
    void setup(){
        makeUnavailableUseCase = new MakeUnavailableUseCase(eventsRepository);
    }


    @Test
    void successfulScenario() {

        //Mocking the course creation event
        InstructorRegistered instructorRegistered = MocksGenerator.instructorRegistered();
        instructorRegistered.setAggregateRootId("InstructorID");

        //Mocking the input command of the event driven use case
        MakeUnavailableCommand makeUnavailableCommand = new MakeUnavailableCommand( "InstructorID", false);

        //Mocking events related with the aggregateRoot retrieved from DB
        List<DomainEvent> courseEvents = new ArrayList<>();
        courseEvents.add(instructorRegistered);

        Mockito.when(eventsRepository.findByAggregatedRootId(ArgumentMatchers.any(String.class)))
                .thenReturn(courseEvents);

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(DomainEvent.class)))
                .thenAnswer( invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        //Action
        List<DomainEvent> domainEventList = makeUnavailableUseCase.apply(makeUnavailableCommand);

        Assertions.assertEquals(1,domainEventList.size());
        Assertions.assertEquals(false,
                (((UnavailableMade)domainEventList.get(0))).isAvailable());
    }

}
package com.coursesplatform.enroll.business.instructor;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.domain.instructor.commands.RegisterInstructorCommand;
import com.coursesplatform.enroll.domain.instructor.events.InstructorRegistered;
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

import java.util.List;

@ExtendWith(MockitoExtension.class)
class RegisterInstructorUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;
    private RegisterInstructorUseCase registerInstructorUseCase;

    @BeforeEach
    void setup(){
        registerInstructorUseCase= new RegisterInstructorUseCase(eventsRepository);
    }

    @Test
    void successfulScenario(){

        RegisterInstructorCommand registerInstructorCommand =MocksGenerator.registerInstructorCommand();

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(InstructorRegistered.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = registerInstructorUseCase.apply(registerInstructorCommand);

        Assertions.assertEquals(1,domainEventList.size());
        Assertions.assertEquals("InstructorID",domainEventList.get(0).aggregateRootId());

    }
}
package com.coursesplatform.enroll.business.student;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.domain.student.commands.RegisterStudentCommand;
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

import java.util.List;

@ExtendWith(MockitoExtension.class)
class RegisterStudentUseCaseTest {


    @Mock
    private EventsRepository eventsRepository;
    private RegisterStudentUseCase registerStudentUseCase;

    @BeforeEach
    void setup(){
        registerStudentUseCase = new RegisterStudentUseCase(eventsRepository);
    }

    @Test
    void successfulScenario(){

        RegisterStudentCommand registerStudentCommand = MocksGenerator.registerStudentCommand();


        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(StudentRegistered.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = registerStudentUseCase.apply(registerStudentCommand);

        Assertions.assertEquals(1,domainEventList.size());
        Assertions.assertEquals("StudentID",domainEventList.get(0).aggregateRootId());

    }
}
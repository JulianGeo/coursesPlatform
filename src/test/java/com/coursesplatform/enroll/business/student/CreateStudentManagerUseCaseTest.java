package com.coursesplatform.enroll.business.student;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.domain.student.commands.CreateStudentManagerCommand;
import com.coursesplatform.enroll.domain.student.events.StudentManagerCreated;
import com.coursesplatform.enroll.generic.DomainEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateStudentManagerUseCaseTest {


    @Mock
    private EventsRepository eventsRepository;
    private CreateStudentManagerUseCase createStudentManagerUseCase;

    @BeforeEach
    void setup(){
        createStudentManagerUseCase = new CreateStudentManagerUseCase(eventsRepository);
    }

    @Test
    void successfulScenario(){

        CreateStudentManagerCommand createStudentManagerCommand = new CreateStudentManagerCommand("StudentManagerID");
        StudentManagerCreated studentManagerCreated = new StudentManagerCreated();
        studentManagerCreated.setAggregateRootId("StudentManagerID");

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(StudentManagerCreated.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });
        List<DomainEvent> domainEventList = createStudentManagerUseCase.apply(createStudentManagerCommand);

        Assertions.assertEquals(1,domainEventList.size());
        Assertions.assertEquals("StudentManagerID",domainEventList.get(0).aggregateRootId());

    }
}
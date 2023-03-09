package com.coursesplatform.enroll.business.instructor;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.business.student.CreateStudentManagerUseCase;
import com.coursesplatform.enroll.domain.instructor.commands.CreateInstructorManagerCommand;
import com.coursesplatform.enroll.domain.instructor.events.InstructorManagerCreated;
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
class CreateInstructorManagerUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;
    private CreateInstructorManagerUseCase createInstructorManagerUseCase;

    @BeforeEach
    void setup(){
        createInstructorManagerUseCase = new CreateInstructorManagerUseCase(eventsRepository);
    }

    @Test
    void successfulScenario(){

        CreateInstructorManagerCommand createInstructorManagerCommand = new CreateInstructorManagerCommand("InstructorManagerID");
        InstructorManagerCreated instructorManagerCreated = new InstructorManagerCreated();
        instructorManagerCreated.setAggregateRootId("InstructorManagerID");

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(InstructorManagerCreated.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = createInstructorManagerUseCase.apply(createInstructorManagerCommand);

        Assertions.assertEquals(1,domainEventList.size());
        Assertions.assertEquals("InstructorManagerID",domainEventList.get(0).aggregateRootId());

    }
}
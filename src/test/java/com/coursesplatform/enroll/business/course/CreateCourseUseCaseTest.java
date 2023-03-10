package com.coursesplatform.enroll.business.course;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.domain.course.commands.CreateCourseCommand;
import com.coursesplatform.enroll.domain.course.events.CourseCreated;
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
class CreateCourseUseCaseTest {


    @Mock
    private EventsRepository eventsRepository;
    private CreateCourseUseCase createCourseUseCase;

    @BeforeEach
    void setup(){
        createCourseUseCase= new CreateCourseUseCase(eventsRepository);
    }

    @Test
    void successfulScenario(){

        CreateCourseCommand createCourseCommand=MocksGenerator.createCourseCommand();

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(CourseCreated.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = createCourseUseCase.apply(createCourseCommand);

        Assertions.assertEquals(1,domainEventList.size());
        Assertions.assertEquals("CourseID",domainEventList.get(0).aggregateRootId());

    }
}
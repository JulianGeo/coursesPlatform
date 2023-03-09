package com.coursesplatform.enroll.business.course;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.business.student.CreateStudentManagerUseCase;
import com.coursesplatform.enroll.domain.course.commands.CreateCourseManagerCommand;
import com.coursesplatform.enroll.domain.course.events.CourseManagerCreated;
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
class CreateCourseManagerUseCaseTest {


    @Mock
    private EventsRepository eventsRepository;
    private CreateCourseManagerUseCase createCourseManagerUseCase;

    @BeforeEach
    void setup(){
        createCourseManagerUseCase = new CreateCourseManagerUseCase(eventsRepository);
    }

    @Test
    void successfulScenario(){

        CreateCourseManagerCommand createCourseManagerCommand = new CreateCourseManagerCommand("CourseManagerID");
        CourseManagerCreated courseManagerCreated = new CourseManagerCreated();
        courseManagerCreated.setAggregateRootId("CourseManagerID");

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(CourseManagerCreated.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = createCourseManagerUseCase.apply(createCourseManagerCommand);

        Assertions.assertEquals(1,domainEventList.size());
        Assertions.assertEquals("CourseManagerID",domainEventList.get(0).aggregateRootId());

    }
}
package com.coursesplatform.enroll.business.course;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.domain.course.commands.ChangeDescriptionCommand;
import com.coursesplatform.enroll.domain.course.events.CourseCreated;
import com.coursesplatform.enroll.domain.course.events.DescriptionChanged;
import com.coursesplatform.enroll.domain.student.events.StudentEnrolled;
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
class ChangeDescriptionUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;
    private ChangeDescriptionUseCase changeDescriptionUseCase;

    @BeforeEach
    void setup(){
        changeDescriptionUseCase = new ChangeDescriptionUseCase(eventsRepository);
    }

    @Test
    void successfulScenario() {

        //Mocking the course creation event
        CourseCreated courseCreated = MocksGenerator.courseCreated();
        courseCreated.setAggregateRootId("CourseID");

        //Mocking the input command of the event driven use case
        ChangeDescriptionCommand changeDescriptionCommand = new ChangeDescriptionCommand( "CourseID", "New description");

        //Mocking events related with the aggregateRoot retrieved from DB
        List<DomainEvent> courseEvents = new ArrayList<>();
        courseEvents.add(courseCreated);


        Mockito.when(eventsRepository.findByAggregatedRootId(ArgumentMatchers.any(String.class)))
                .thenReturn(courseEvents);

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(DomainEvent.class)))
                .thenAnswer( invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        //Action
        List<DomainEvent> domainEventList = changeDescriptionUseCase.apply(changeDescriptionCommand);

        Assertions.assertEquals(1,domainEventList.size());
        Assertions.assertEquals("New description",
                (((DescriptionChanged)domainEventList.get(0))).getDescription());
    }
}
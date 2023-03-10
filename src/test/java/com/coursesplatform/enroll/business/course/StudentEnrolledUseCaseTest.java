package com.coursesplatform.enroll.business.course;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.domain.course.events.CourseCreated;
import com.coursesplatform.enroll.domain.course.events.StudentEnrolledFromStudent;
import com.coursesplatform.enroll.domain.student.events.StudentEnrolled;
import com.coursesplatform.enroll.generic.DomainEvent;
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

@ExtendWith(MockitoExtension.class)
class StudentEnrolledUseCaseTest {


    @Mock
    private EventsRepository eventsRepository;
    private StudentEnrolledUseCase studentEnrolledUseCase;

    @BeforeEach
    void setup(){
        studentEnrolledUseCase = new StudentEnrolledUseCase(eventsRepository);
    }

    @Test
    void successfulScenario() {

        //Mocking the input event of the event driven use case
        StudentEnrolled studentEnrolled = new StudentEnrolled( "EnrollmentID", "CourseID");

        //Mocking events related with the aggregateRoot retrieved from DB
        List<DomainEvent> courseManagerEvents = new ArrayList<>();
        CourseCreated courseCreated = new CourseCreated("InstructorID", "Description");
        courseCreated.setAggregateRootId("CourseID");
        courseManagerEvents.add(courseCreated);

        Mockito.when(eventsRepository.findByAggregatedRootId(ArgumentMatchers.any(String.class)))
                .thenReturn(courseManagerEvents);

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(DomainEvent.class)))
                .thenAnswer( invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });


        //Action
        List<DomainEvent> domainEventList = studentEnrolledUseCase.apply(studentEnrolled);

        Assertions.assertEquals(2,domainEventList.size());
        Assertions.assertTrue(domainEventList.size()>1);
        //Assertions.assertEquals("CourseID",
               // ((StudentEnrolledFromStudent)(domainEventList.get(domainEventList.size()-1))).get());
    }

}





package com.coursesplatform.enroll.business.course;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.domain.course.events.CourseCreated;
import com.coursesplatform.enroll.domain.course.events.StudentEnrolledFromStudent;
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

//TODO: This doesnt work yet
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

        //Mocking the course creation event
        CourseCreated courseCreated = MocksGenerator.courseCreated();
        courseCreated.setAggregateRootId("CourseID");

        //Mocking the input event of the event driven use case
        StudentEnrolled studentEnrolled = new StudentEnrolled( "EnrollmentID", "CourseID");
        studentEnrolled.setAggregateRootId("StudentID");

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
        List<DomainEvent> domainEventList = studentEnrolledUseCase.apply(studentEnrolled);

        Assertions.assertEquals(1,domainEventList.size());
        //Assertions.assertTrue(domainEventList.size()>1);
        Assertions.assertEquals("CourseID",
                ((domainEventList.get(domainEventList.size()-1))).type);
    }

}





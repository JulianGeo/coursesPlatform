package com.coursesplatform.enroll.business.course;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.domain.course.events.CourseCreated;
import com.coursesplatform.enroll.domain.student.events.StudentEnrolled;
import com.coursesplatform.enroll.domain.student.events.StudentUnenrolled;
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
class StudentUnenrolledUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;
    private StudentUnenrolledUseCase studentUnenrolledUseCase;

    @BeforeEach
    void setup(){
        studentUnenrolledUseCase = new StudentUnenrolledUseCase(eventsRepository);
    }

    @Test
    void successfulScenario() {

        //Mocking the input event of the event driven use case
        StudentUnenrolled studentUnenrolled = new StudentUnenrolled("EnrollmentID", "CourseID");

        //Mocking events related with the aggregateRoot retrieved from DB
        List<DomainEvent> courseManagerEvents = new ArrayList<>();
        CourseCreated courseCreated = new CourseCreated("InstructorID", "Description");
        courseCreated.setAggregateRootId("CourseID");
        courseManagerEvents.add(courseCreated);
        courseManagerEvents.add(new StudentEnrolled("EnrollmentID","CourseID"));


        Mockito.when(eventsRepository.findByAggregatedRootId(ArgumentMatchers.any(String.class)))
                .thenReturn(courseManagerEvents);

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(DomainEvent.class)))
                .thenAnswer( invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });


        //Action
        List<DomainEvent> domainEventList = studentUnenrolledUseCase.apply(studentUnenrolled);

        Assertions.assertEquals(3,domainEventList.size());
        //Assertions.assertEquals("enroll.studentEnrolledFromStudent",
               // ((StudentEnrolledFromStudent)(domainEventList.get(domainEventList.size()-2))).type);
        //Assertions.assertEquals("enroll.studentUnenrolledFromStudent",
               // ((StudentUnenrolled)(domainEventList.get(domainEventList.size()-1))).type);
    }

}
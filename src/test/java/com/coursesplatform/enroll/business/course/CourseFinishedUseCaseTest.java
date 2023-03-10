package com.coursesplatform.enroll.business.course;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.domain.course.events.CourseCreated;
import com.coursesplatform.enroll.domain.course.events.CourseFinishedFromStudent;
import com.coursesplatform.enroll.domain.course.events.StudentEnrolledFromStudent;
import com.coursesplatform.enroll.domain.course.events.StudentUnenrolledFromStudent;
import com.coursesplatform.enroll.domain.student.events.CourseFinished;
import com.coursesplatform.enroll.domain.student.events.StudentUnenrolled;
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
class CourseFinishedUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;
    private CourseFinishedUseCase courseFinishedUseCase;

    @BeforeEach
    void setup(){
        courseFinishedUseCase = new CourseFinishedUseCase(eventsRepository);
    }


    @Test
    void successfulScenario() {

        //Mocking the input event of the event driven use case
        CourseFinished courseFinished = new CourseFinished("CourseID", "EnrollmentID");
        courseFinished.setAggregateRootId("StudentID");

        //Mocking events related with the aggregateRoot retrieved from DB
        List<DomainEvent> courseManagerEvents = new ArrayList<>();

        CourseCreated courseCreated =MocksGenerator.courseCreated();
        courseCreated.setAggregateRootId("CourseID");

        StudentEnrolledFromStudent studentEnrolledFromStudent = new StudentEnrolledFromStudent( "StudentID", "EnrollmentID");
        studentEnrolledFromStudent.setAggregateRootId("CourseID");

        StudentEnrolledFromStudent studentEnrolledFromStudent2 = new StudentEnrolledFromStudent( "StudentID2", "EnrollmentID2");
        studentEnrolledFromStudent.setAggregateRootId("CourseID");

        courseManagerEvents.add(courseCreated);
        courseManagerEvents.add(studentEnrolledFromStudent);
        courseManagerEvents.add(studentEnrolledFromStudent2);

        Mockito.when(eventsRepository.findByAggregatedRootId(ArgumentMatchers.any(String.class)))
                .thenReturn(courseManagerEvents);

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(CourseFinishedFromStudent.class)))
                .thenAnswer( invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        //Action
        List<DomainEvent> domainEventList = courseFinishedUseCase.apply(courseFinished);

        Assertions.assertEquals(1,domainEventList.size());
        Assertions.assertEquals("EnrollmentID",
                ((CourseFinishedFromStudent)(domainEventList.get(0))).getEnrollmentID());
    }
}
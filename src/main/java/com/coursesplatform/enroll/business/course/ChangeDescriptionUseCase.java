package com.coursesplatform.enroll.business.course;

import com.coursesplatform.enroll.business.commons.EventsRepository;
import com.coursesplatform.enroll.business.commons.UseCaseForCommand;
import com.coursesplatform.enroll.domain.course.Course;
import com.coursesplatform.enroll.domain.course.commands.ChangeDescriptionCommand;
import com.coursesplatform.enroll.domain.course.values.CourseID;
import com.coursesplatform.enroll.generic.DomainEvent;

import java.util.List;

public class ChangeDescriptionUseCase implements UseCaseForCommand<ChangeDescriptionCommand> {

    private final EventsRepository eventsRepository;
    public ChangeDescriptionUseCase(EventsRepository eventsRepository){
        this.eventsRepository=eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(ChangeDescriptionCommand command) {
        List<DomainEvent> courseEvents =  eventsRepository.findByAggregatedRootId(command.getCourseID());
        Course course=Course.from(CourseID.of((command.getCourseID())), courseEvents);
        course.changeDescription(command.getDescription());
        return course.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).toList();
    }


}

package com.coursesplatform.enroll.domain.course.events;

import com.coursesplatform.enroll.domain.course.values.CourseID;
import com.coursesplatform.enroll.generic.DomainEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseCreated extends DomainEvent {

    private String instructorID;
    private String description;

    public CourseCreated(
            String instructorID,
            String description) {
        super("enroll.courseCreated");
        this.instructorID=instructorID;
        this.description=description;
    }
}
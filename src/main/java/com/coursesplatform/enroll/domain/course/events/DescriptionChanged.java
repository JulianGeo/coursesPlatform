package com.coursesplatform.enroll.domain.course.events;

import com.coursesplatform.enroll.generic.DomainEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DescriptionChanged extends DomainEvent {
    private String description;

    public DescriptionChanged(String description) {
        super("enroll.courseDescriptionChanged");
        this.description=description;
    }
}

package com.coursesplatform.enroll.domain.instructor.events;

import com.coursesplatform.enroll.generic.DomainEvent;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AvailableMade extends DomainEvent {
    private boolean available;

    public AvailableMade() {
        super("enroll.availableMade");
        this.available=true;
    }
}

package com.coursesplatform.enroll.domain.instructor.events;

import com.coursesplatform.enroll.generic.DomainEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnavailableMade extends DomainEvent {
    private boolean available;

    public UnavailableMade() {
        super("enroll.availableMade");
        this.available=false;
    }
}

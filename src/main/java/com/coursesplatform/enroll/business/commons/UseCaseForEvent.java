package com.coursesplatform.enroll.business.commons;

import com.coursesplatform.enroll.generic.DomainEvent;

import java.util.List;

public interface UseCaseForEvent<T extends DomainEvent>{

    List<DomainEvent> apply(T event);

}
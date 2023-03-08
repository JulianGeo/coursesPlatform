package com.coursesplatform.enroll.business.commons;

import com.coursesplatform.enroll.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EventsRepository {

    DomainEvent saveEvent(DomainEvent event);

    List<DomainEvent> findByAggregatedRootId(String aggregatedRootId);

}
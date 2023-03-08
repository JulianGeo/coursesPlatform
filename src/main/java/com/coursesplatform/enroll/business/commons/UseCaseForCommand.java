package com.coursesplatform.enroll.business.commons;

import com.coursesplatform.enroll.generic.Command;
import com.coursesplatform.enroll.generic.DomainEvent;

import java.util.List;

public interface UseCaseForCommand <T extends Command>{

    List<DomainEvent> apply(T command);

}
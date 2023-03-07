package com.coursesplatform.enroll.domain.instructor.commands;

import com.coursesplatform.enroll.generic.Command;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateInstructorManager extends Command {

    private String instructorManagerID;

}

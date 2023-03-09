package com.coursesplatform.enroll.domain.instructor.commands;

import com.coursesplatform.enroll.generic.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInstructorManagerCommand extends Command {

    private String instructorManagerID;

}

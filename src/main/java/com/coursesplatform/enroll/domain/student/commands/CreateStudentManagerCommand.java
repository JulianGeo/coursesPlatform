package com.coursesplatform.enroll.domain.student.commands;

import com.coursesplatform.enroll.generic.Command;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateStudentManagerCommand extends Command {
    private String studentManagerID;
}

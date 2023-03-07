package com.coursesplatform.enroll.domain.course.commands;

import com.coursesplatform.enroll.generic.Command;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateCourseManager extends Command {
    private String courseManagerID;
}

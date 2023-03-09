package com.coursesplatform.enroll.domain.course.commands;

import com.coursesplatform.enroll.generic.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCourseManagerCommand extends Command {
    private String courseManagerID;
}

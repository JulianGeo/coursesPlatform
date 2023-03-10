package com.coursesplatform.enroll.domain.course.commands;

import com.coursesplatform.enroll.generic.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeDescriptionCommand extends Command {
    private String courseID;
    private String description;
}

package com.coursesplatform.enroll.domain.student.commands;

import com.coursesplatform.enroll.generic.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnenrollStudentCommand extends Command {

    private String studentManagerID;
    private String studentID;
    private String enrollmentID;
    private String courseManagerID;

}

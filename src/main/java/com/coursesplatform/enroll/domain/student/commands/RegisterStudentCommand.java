package com.coursesplatform.enroll.domain.student.commands;

import com.coursesplatform.enroll.generic.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterStudentCommand extends Command {

    private String studentID;
    private String name;
    private String personalID;
    private String email;
    private String phone;
    private String user;
    private String password;
    private String planID;
    private String planName;

}

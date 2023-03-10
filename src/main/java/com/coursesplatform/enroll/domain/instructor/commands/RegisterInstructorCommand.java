package com.coursesplatform.enroll.domain.instructor.commands;

import com.coursesplatform.enroll.generic.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterInstructorCommand extends Command {
    private String instructorID;
    private String name;
    private String personalID;
    private String email;
    private String phone;
    private String user;
    private String password;

}

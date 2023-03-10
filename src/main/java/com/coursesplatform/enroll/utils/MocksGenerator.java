package com.coursesplatform.enroll.utils;

import com.coursesplatform.enroll.domain.course.commands.CreateCourseCommand;
import com.coursesplatform.enroll.domain.course.events.CourseCreated;
import com.coursesplatform.enroll.domain.instructor.commands.RegisterInstructorCommand;
import com.coursesplatform.enroll.domain.instructor.events.InstructorRegistered;
import com.coursesplatform.enroll.domain.student.commands.RegisterStudentCommand;
import com.coursesplatform.enroll.domain.student.events.StudentRegistered;

public class MocksGenerator {

    public static RegisterInstructorCommand registerInstructorCommand(){
        return new RegisterInstructorCommand("InstructorID",
                "Name",
                "PersonalID",
                "Email",
                "Phone",
                "User",
                "Password")   ;
    }

    public static InstructorRegistered instructorRegistered(){
        return new InstructorRegistered( "Name",
                "PersonalID",
                "Email",
                "Phone",
                "User",
                "Password");
    }

    public static RegisterStudentCommand registerStudentCommand(){
        return new RegisterStudentCommand(
                "StudentID",
                "Name",
                "PersonalID",
                "Email",
                "Phone",
                "User",
                "Password",
                "PlanID",
                "PlanName");
    }

    public static StudentRegistered studentRegistered(){
        return new StudentRegistered(
                "Name",
                "PersonalID",
                "Email",
                "Phone",
                "User",
                "Password",
                "PlanID",
                "PlanName");
    }

    public static CreateCourseCommand createCourseCommand(){
        return new CreateCourseCommand(
                "CourseID",
                "InstructorID",
                "Description");
    }

    public static CourseCreated courseCreated(){
        return new CourseCreated(
                "InstructorID",
                "Description");
    }

}

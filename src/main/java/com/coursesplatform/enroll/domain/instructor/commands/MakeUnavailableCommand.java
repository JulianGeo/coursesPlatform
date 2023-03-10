package com.coursesplatform.enroll.domain.instructor.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MakeUnavailableCommand {
    private String instructorID;
    private boolean available;
}

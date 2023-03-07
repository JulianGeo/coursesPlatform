package com.coursesplatform.enroll.domain.instructor;

import com.coursesplatform.enroll.domain.sharedValues.Date;
import com.coursesplatform.enroll.domain.sharedValues.Rate;
import com.coursesplatform.enroll.domain.sharedValues.RatingID;
import com.coursesplatform.enroll.domain.student.values.StudentID;
import com.coursesplatform.enroll.generic.Entity;

public class Rating extends Entity <RatingID> {

    private Rate rate;
    private StudentID studentID;
    private Date ratingDate;

    public Rating (RatingID entityID, Rate rate, StudentID studentID, Date ratingDate) {
        super (entityID);
        this.rate=rate;
        this.studentID=studentID;
        this.ratingDate=ratingDate;
    }

    public Rate rate () {
        return rate;
    }

    public StudentID studentID () {
        return studentID;
    }

    public Date ratingDate () {
        return ratingDate;
    }
}

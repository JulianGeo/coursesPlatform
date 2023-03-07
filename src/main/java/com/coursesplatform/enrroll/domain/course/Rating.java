package com.coursesplatform.enrroll.domain.course;

import com.coursesplatform.enrroll.domain.sharedValues.Date;
import com.coursesplatform.enrroll.domain.sharedValues.Rate;
import com.coursesplatform.enrroll.domain.sharedValues.RatingID;
import com.coursesplatform.enrroll.domain.student.values.StudentID;
import com.coursesplatform.enrroll.generic.Entity;

public class Rating extends Entity<RatingID> {

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

package com.coursesplatform.enrroll.domain.instructor;

import com.coursesplatform.enrroll.domain.sharedValues.Comment;
import com.coursesplatform.enrroll.domain.sharedValues.Date;
import com.coursesplatform.enrroll.domain.sharedValues.RatingID;
import com.coursesplatform.enrroll.domain.sharedValues.ReviewID;
import com.coursesplatform.enrroll.domain.student.values.StudentID;
import com.coursesplatform.enrroll.generic.Entity;

public class Review extends Entity <ReviewID> {

    private Comment comment;
    private StudentID studentID;
    private Date ratingDate;

    public Review (ReviewID entityID, Comment comment, StudentID studentID, Date ratingDate) {
        super (entityID);
        this.comment=comment;
        this.studentID=studentID;
        this.ratingDate=ratingDate;
    }

    public Comment comment () {
        return comment;
    }

    public StudentID studentID () {
        return studentID;
    }

    public Date ratingDate () {
        return ratingDate;
    }
}
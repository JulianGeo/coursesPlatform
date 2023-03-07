package com.coursesplatform.enroll.domain.instructor;

import com.coursesplatform.enroll.domain.sharedValues.Comment;
import com.coursesplatform.enroll.domain.sharedValues.Date;
import com.coursesplatform.enroll.domain.sharedValues.ReviewID;
import com.coursesplatform.enroll.domain.student.values.StudentID;
import com.coursesplatform.enroll.generic.Entity;

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
package com.coursesplatform.enrroll.domain.sharedValues;

import com.coursesplatform.enrroll.generic.Identity;

public class ReviewID extends Identity {

    public ReviewID() {
    }

    private ReviewID(String reviewID) {
        super(reviewID);
    }

    public static ReviewID of(String reviewID){
        return new ReviewID(reviewID);
    }
}
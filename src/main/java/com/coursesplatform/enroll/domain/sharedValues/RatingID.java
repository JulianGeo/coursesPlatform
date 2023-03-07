package com.coursesplatform.enroll.domain.sharedValues;

import com.coursesplatform.enroll.generic.Identity;

public class RatingID extends Identity {

    public RatingID() {
    }

    private RatingID(String ratingID) {
        super(ratingID);
    }

    public static RatingID of(String ratingID){
        return new RatingID(ratingID);
    }
}

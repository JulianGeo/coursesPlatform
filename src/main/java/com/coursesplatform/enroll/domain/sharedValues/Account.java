package com.coursesplatform.enroll.domain.sharedValues;

import com.coursesplatform.enroll.generic.ValueObject;

public class Account implements ValueObject<Account.Props> {
    private User user;
    private Password password;

    public Account(User user, Password password) {
        this.user = user;
        this.password = password;
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public User user() {
                return user;
            }

            @Override
            public Password password() {
                return password;
            }

        };
    }

    public interface Props {
        User user();
        Password password();
}
}

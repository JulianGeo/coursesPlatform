package com.coursesplatform.enrroll.domain.sharedValues;

import com.coursesplatform.enrroll.generic.ValueObject;

public class Data implements ValueObject<Data.Props> {

    private PersonalID personalID;
    private Name name;
    private Email email;
    private Phone phone;

    public Data(PersonalID personalID, Name name, Email email, Phone phone) {
        this.personalID = personalID;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public PersonalID personalID() {
                return personalID;
            }

            @Override
            public Name name() {
                return name;
            }

            @Override
            public Email email() {
                return email;
            }

            @Override
            public Phone phone() {
                return phone;
            }
        };
    }


    public interface Props {

        PersonalID personalID();
        Name name();
        Email email();
        Phone phone();

    }
}

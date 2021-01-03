package com.epam.university.model.user;

public class Enrollee {

    private final String name;
    private final String surname;
    private final int balAmount;

    public Enrollee(String name, String surname, int balAmount) {

        this.name = name;
        this.surname = surname;
        this.balAmount = balAmount;

    }

    public String getName() {

        return name;
    }

    public String getSurname() {

        return surname;
    }

    public int getBalAmount() {

        return balAmount;
    }


}
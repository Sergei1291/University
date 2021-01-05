package com.epam.university.model.user;

import com.epam.university.model.Identifiable;

public final class Enrollee implements Identifiable {

    private static final long serialVersionUID = 1L;

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

    @Override
    public String toString() {
        return "Enrollee{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", balAmount=" + balAmount +
                '}';
    }

}
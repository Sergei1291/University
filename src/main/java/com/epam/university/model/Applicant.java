package com.epam.university.model;

import java.io.Serializable;

public final class Applicant implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int id;
    private final int balAmount;

    public Applicant(int id, int balAmount) {
        this.id = id;
        this.balAmount = balAmount;
    }

    public int getId() {
        return id;
    }

    public int getBalAmount() {
        return balAmount;
    }

}
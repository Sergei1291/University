package com.epam.university.model;

public final class Subject implements Identifiable {

    private static final long serialVersionUID = 1L;

    private final int id;
    private final SubjectName name;

    public Subject(int id, SubjectName name) {

        this.id = id;
        this.name = name;

    }

    public static long getSerialVersionUID() {

        return serialVersionUID;
    }

    public int getId() {

        return id;
    }

    public SubjectName getName() {

        return name;
    }

}
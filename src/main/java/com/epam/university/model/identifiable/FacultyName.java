package com.epam.university.model.identifiable;

public enum FacultyName {

    MATHEMATICAL(1), LINGUISTIC(2), PHYSICAL(3), ECONOMIC(4),
    BIOLOGICAL(5), GEOGRAPHICAL(6);

    private int id;

    private FacultyName(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
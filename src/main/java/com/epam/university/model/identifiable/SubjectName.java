package com.epam.university.model.identifiable;

public enum SubjectName {

    LANGUAGE(1), MATHEMATICS(2), PHYSICS(3),
    FOREIGN_LANGUAGE(4), CHEMISTRY(5), BIOLOGY(6), GEOGRAPHY(7);

    private int id;

    private SubjectName(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
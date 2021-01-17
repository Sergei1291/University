package com.epam.university.model.identifiable;

public final class FacultyDto implements Identifiable {

    private static final long serialVersionUID = 1L;

    private final int id;
    private final FacultyName name;
    private final int recruitment;

    public FacultyDto(int id, FacultyName name, int recruitment) {
        this.id = id;
        this.name = name;
        this.recruitment = recruitment;
    }

    @Override
    public int getId() {
        return id;
    }

    public FacultyName getName() {
        return name;
    }

    public int getRecruitment() {
        return recruitment;
    }

}
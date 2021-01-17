package com.epam.university.model.identifiable;

import java.util.ArrayList;
import java.util.List;

public final class Faculty implements Identifiable {

    private static final long serialVersionUID = 1L;

    private final int id;
    private final FacultyName name;
    private final int recruitment;
    private final List<Subject> subjects;

    public Faculty(int id, FacultyName name, int recruitment, List<Subject> subjects) {
        this.id = id;
        this.name = name;
        this.subjects = subjects;
        this.recruitment = recruitment;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public List<Subject> getSubjects() {
        return new ArrayList<>(subjects);
    }

}
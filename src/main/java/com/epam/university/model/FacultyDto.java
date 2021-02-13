package com.epam.university.model;

import com.epam.university.model.identifiable.FacultyName;
import com.epam.university.model.identifiable.Subject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class FacultyDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int id;
    private final FacultyName name;
    private final int recruitment;
    private final List<Subject> subjects;

    public FacultyDto(int id, FacultyName name, int recruitment, List<Subject> subjects) {
        this.id = id;
        this.name = name;
        this.subjects = subjects;
        this.recruitment = recruitment;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FacultyDto faculty = (FacultyDto) o;
        if (id != faculty.id) {
            return false;
        }
        if (recruitment != faculty.recruitment) {
            return false;
        }
        if (name != faculty.name) {
            return false;
        }
        return Objects.equals(subjects, faculty.subjects);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + recruitment;
        result = 31 * result + (subjects != null ? subjects.hashCode() : 0);
        return result;
    }

}
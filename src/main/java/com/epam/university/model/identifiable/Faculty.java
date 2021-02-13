package com.epam.university.model.identifiable;

public final class Faculty implements Identifiable {

    private static final long serialVersionUID = 1L;

    private final int id;
    private final FacultyName name;
    private final int recruitment;

    public Faculty(int id, FacultyName name, int recruitment) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Faculty that = (Faculty) o;
        if (id != that.id) {
            return false;
        }
        if (recruitment != that.recruitment) {
            return false;
        }
        return name == that.name;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + recruitment;
        return result;
    }

}
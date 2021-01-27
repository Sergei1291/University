package com.epam.university.model.identifiable;

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

    @Override
    public int getId() {
        return id;
    }

    public SubjectName getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Subject subject = (Subject) o;
        if (id != subject.id) {
            return false;
        }
        return name == subject.name;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

}
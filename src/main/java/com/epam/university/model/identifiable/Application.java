package com.epam.university.model.identifiable;

public final class Application implements Identifiable {

    private static final long serialVersionUID = 1L;

    private final int id;
    private final int user;
    private final int faculty;
    private final int averageMark;
    private final ApplicationStatus status;

    public Application(int id, int user, int faculty, int averageMark, ApplicationStatus status) {
        this.id = id;
        this.user = user;
        this.faculty = faculty;
        this.averageMark = averageMark;
        this.status = status;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public int getId() {
        return id;
    }

    public int getUser() {
        return user;
    }

    public int getFaculty() {
        return faculty;
    }

    public int getAverageMark() {
        return averageMark;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Application that = (Application) o;
        if (id != that.id) {
            return false;
        }
        if (user != that.user) {
            return false;
        }
        if (faculty != that.faculty) {
            return false;
        }
        if (averageMark != that.averageMark) {
            return false;
        }
        return status == that.status;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + user;
        result = 31 * result + faculty;
        result = 31 * result + averageMark;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

}
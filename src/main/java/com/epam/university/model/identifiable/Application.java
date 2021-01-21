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

}
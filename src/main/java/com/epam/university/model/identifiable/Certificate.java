package com.epam.university.model.identifiable;

public final class Certificate implements Identifiable {

    private static final long serialVersionUID = 1L;

    private final int id;
    private final int application;
    private final int subject;
    private final int mark;

    public Certificate(int id, int application, int subject, int mark) {
        this.id = id;
        this.application = application;
        this.subject = subject;
        this.mark = mark;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public int getId() {
        return id;
    }

    public int getApplication() {
        return application;
    }

    public int getSubject() {
        return subject;
    }

    public int getMark() {
        return mark;
    }

}
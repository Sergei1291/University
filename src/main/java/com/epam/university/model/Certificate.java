package com.epam.university.model;

public final class Certificate implements Identifiable {

    private static final long serialVersionUID = 1L;

    private final int user;
    private final int subject;
    private final int mark;

    public Certificate(int user, int subject, int mark) {

        this.user = user;
        this.subject = subject;
        this.mark = mark;

    }

    public static long getSerialVersionUID() {

        return serialVersionUID;
    }

    public int getUser() {

        return user;
    }

    public int getSubject() {

        return subject;
    }

    public int getMark() {

        return mark;
    }

}
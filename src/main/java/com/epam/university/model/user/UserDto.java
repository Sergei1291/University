package com.epam.university.model.user;

import com.epam.university.model.Identifiable;

public final class UserDto implements Identifiable {

    private static final long serialVersionUID = 1L;

    private final int id;
    private final Role role;
    private final String name;
    private final String surname;
    private final Integer faculty;
    private final Integer averageMark;
    private final ApplicationStatus applicationStatus;

    public UserDto(int id, Role role, String name, String surname,
                   Integer faculty, Integer averageMark, ApplicationStatus applicationStatus) {

        this.id = id;
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.faculty = faculty;
        this.averageMark = averageMark;
        this.applicationStatus = applicationStatus;

    }

    public static long getSerialVersionUID() {

        return serialVersionUID;
    }

    public int getId() {

        return id;
    }

    public Role getRole() {

        return role;
    }

    public String getName() {

        return name;
    }

    public String getSurname() {

        return surname;
    }

    public Integer getFaculty() {

        return faculty;
    }

    public Integer getAverageMark() {

        return averageMark;
    }

    public ApplicationStatus getApplicationStatus() {

        return applicationStatus;
    }

}
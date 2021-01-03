package com.epam.university.model.user;

import com.epam.university.model.Identifiable;

public class UserDto implements Identifiable {

    private static final long serialVersionUID = 1L;

    private final int id;
    private final Role role;
    private final String name;
    private final String surname;
    private Integer faculty;
    private Integer averageMark;
    private ApplicationStatus applicationStatus;

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

    public void setFaculty(Integer faculty) {

        this.faculty = faculty;

    }

    public Integer getAverageMark() {

        return averageMark;
    }

    public void setAverageMark(Integer averageMark) {

        this.averageMark = averageMark;

    }

    public ApplicationStatus getApplicationStatus() {

        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {

        this.applicationStatus = applicationStatus;

    }

}
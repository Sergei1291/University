package com.epam.university.model.identifiable;

public final class UserDto implements Identifiable {

    private static final long serialVersionUID = 1L;

    private final int id;
    private final UserRole role;
    private final String name;
    private final String surname;

    public UserDto(int id, UserRole role, String name, String surname) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.surname = surname;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public int getId() {
        return id;
    }

    public UserRole getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

}
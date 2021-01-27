package com.epam.university.model.identifiable;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDto userDto = (UserDto) o;
        if (id != userDto.id) {
            return false;
        }
        if (role != userDto.role) {
            return false;
        }
        if (!Objects.equals(name, userDto.name)) {
            return false;
        }
        return Objects.equals(surname, userDto.surname);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }

}
package com.epam.university.model;

import java.io.Serializable;

public final class ApplicationDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int id;
    private final int marksAmount;

    public ApplicationDto(int id, int marksAmount) {
        this.id = id;
        this.marksAmount = marksAmount;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public int getMarksAmount() {
        return marksAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApplicationDto that = (ApplicationDto) o;
        if (id != that.id) {
            return false;
        }
        return marksAmount == that.marksAmount;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + marksAmount;
        return result;
    }

}
package com.epam.university.model;

import java.io.Serializable;

public final class ApplicationDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int applicationId;
    private final int marksAmount;

    public ApplicationDto(int applicationId, int marksAmount) {
        this.applicationId = applicationId;
        this.marksAmount = marksAmount;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public int getMarksAmount() {
        return marksAmount;
    }

}
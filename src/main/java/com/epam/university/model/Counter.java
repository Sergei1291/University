package com.epam.university.model;

import java.io.Serializable;

public class Counter implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int minBorder;
    private final int maxBorder;
    private int value;

    public Counter(int minBorder, int maxBorder) {
        this.minBorder = minBorder;
        this.maxBorder = maxBorder;
        this.value = 0;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getMinBorder() {
        return minBorder;
    }

    public int getMaxBorder() {
        return maxBorder;
    }

    public int getValue() {
        return value;
    }

    public void plusValue() {
        value++;
    }

}
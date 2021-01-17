package com.epam.university.model;

public class Counter {

    private final int minBorder;
    private final int maxBorder;
    private int value;

    public Counter(int minBorder, int maxBorder) {
        this.minBorder = minBorder;
        this.maxBorder=maxBorder;
        this.value = 0;
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
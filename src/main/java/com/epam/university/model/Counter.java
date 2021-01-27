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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Counter counter = (Counter) o;
        if (minBorder != counter.minBorder) {
            return false;
        }
        if (maxBorder != counter.maxBorder) {
            return false;
        }
        return value == counter.value;
    }

    @Override
    public int hashCode() {
        int result = minBorder;
        result = 31 * result + maxBorder;
        result = 31 * result + value;
        return result;
    }

}
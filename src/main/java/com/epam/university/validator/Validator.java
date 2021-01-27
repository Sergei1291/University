package com.epam.university.validator;

public interface Validator<T> {

    /**
     * This method is used to check object type T on conditions.
     *
     * @param data
     * @return
     */
    boolean isValid(T data);

}
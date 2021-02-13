package com.epam.university.service.api;

import com.epam.university.service.ServiceException;

import java.util.Map;

public interface EntranceCompanyService extends FacultyDtoService {

    /**
     * This method is used to finding numbers active applications on faculty.
     * The result of method is map, which contains: key - faculty id, value -
     * number of active applications. Active application don't have status
     * 'cancelled'.
     *
     * @return
     * @throws ServiceException
     */
    Map<Integer, Integer> findNumbersApplications() throws ServiceException;

}
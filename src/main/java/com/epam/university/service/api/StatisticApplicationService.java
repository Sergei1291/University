package com.epam.university.service.api;

import com.epam.university.model.Counter;
import com.epam.university.service.ServiceException;

import java.util.List;

public interface StatisticApplicationService {

    /**
     * This method is used to finding information of quantity applications
     * by faculty. This information is list counters. All the possible amounts
     * of marks divided into several intervals. Counter contains min value, max
     * value and number applications of interval. The method returns empty list
     * if param facultyId is not valid.
     *
     * @param facultyId
     * @return
     * @throws ServiceException
     */
    List<Counter> findFacultyStatisticApplications(int facultyId) throws ServiceException;

}
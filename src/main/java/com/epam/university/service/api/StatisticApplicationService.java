package com.epam.university.service.api;

import com.epam.university.model.Counter;
import com.epam.university.service.ServiceException;

import java.util.List;

public interface StatisticApplicationService {

    List<Counter> findFacultyStatisticApplications(int facultyId) throws ServiceException;

}
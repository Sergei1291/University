package com.epam.university.service.api;

import com.epam.university.model.Counter;
import com.epam.university.service.ServiceException;

import java.util.List;

public interface StatisticApplicantService {

    List<Counter> findFacultyStatisticApplicantsMark(int facultyId) throws ServiceException;

}
package com.epam.university.service.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.dao.persistent.api.ApplicationDao;
import com.epam.university.model.identifiable.Faculty;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.EntranceCompanyService;
import com.epam.university.service.api.FacultySubjectService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntranceCompanyServiceImpl extends FacultyDtoServiceImpl implements EntranceCompanyService {

    public EntranceCompanyServiceImpl(FacultySubjectService facultySubjectService,
                                      DaoHelperCreator daoHelperCreator) {
        super(daoHelperCreator, facultySubjectService);
    }

    @Override
    public Map<Integer, Integer> findNumbersApplications()
            throws ServiceException {
        List<Faculty> faculties = findFaculties();
        Map<Integer, Integer> numbersApplications = new HashMap<>();
        for (Faculty faculty : faculties) {
            int facultyId = faculty.getId();
            int numberApplications = findNumberApplicationsByFaculty(facultyId);
            numbersApplications.put(facultyId, numberApplications);
        }
        return numbersApplications;
    }

    private int findNumberApplicationsByFaculty(int facultyId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperCreator.create()) {
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            return applicationDao.findNumberApplicationsByFacultyAndStatusNotCancelled(facultyId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
package com.epam.university.service.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.api.FacultyDtoDao;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.model.identifiable.FacultyDto;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.FacultyDtoService;

import java.util.List;

public class FacultyDtoServiceImpl implements FacultyDtoService {

    protected DaoHelperCreator daoHelperCreator;

    public FacultyDtoServiceImpl(DaoHelperCreator daoHelperCreator) {
        this.daoHelperCreator = daoHelperCreator;
    }

    @Override
    public List<FacultyDto> findFacultiesDto() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperCreator.create()) {
            FacultyDtoDao facultyDtoDao = daoHelper.createFacultyDtoDao();
            return facultyDtoDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
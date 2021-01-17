package com.epam.university.service.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.dao.persistent.api.CertificateDao;
import com.epam.university.dao.persistent.api.UserDtoDao;
import com.epam.university.model.identifiable.Certificate;
import com.epam.university.model.identifiable.user.UserDto;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.FacultyApplicantService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacultyApplicantServiceImpl implements FacultyApplicantService {

    private DaoHelperCreator daoHelperCreator;

    public FacultyApplicantServiceImpl(DaoHelperCreator daoHelperCreator) {
        this.daoHelperCreator = daoHelperCreator;
    }

    @Override
    public Map<UserDto, List<Certificate>> findFacultyApplicantsInfo(int facultyId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperCreator.create()) {
            UserDtoDao userDtoDao = daoHelper.createUserDtoDao();
            List<UserDto> usersDto = userDtoDao.findAllByFacultyIdAndApplicationStatusNotNull(facultyId);

            Map<UserDto, List<Certificate>> applicantsInfo = new HashMap<>();
            CertificateDao certificateDao = daoHelper.createCertificateDao();
            for (UserDto userDto : usersDto) {
                int userId = userDto.getId();
                List<Certificate> certificates = certificateDao.findALlByUserId(userId);
                applicantsInfo.put(userDto, certificates);
            }
            return applicantsInfo;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
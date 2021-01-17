package com.epam.university.service.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.dao.persistent.api.CertificateDao;
import com.epam.university.dao.persistent.api.UserDtoDao;
import com.epam.university.model.identifiable.Certificate;
import com.epam.university.model.identifiable.user.ApplicationStatus;
import com.epam.university.model.identifiable.user.Role;
import com.epam.university.model.identifiable.user.UserDto;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.EnrolleeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class EnrolleeServiceImpl extends RegistrationServiceImpl implements EnrolleeService {

    private final static Logger LOGGER = LogManager.getLogger();

    private final static ApplicationStatus WAITING = ApplicationStatus.WAITING;

    public EnrolleeServiceImpl(DaoHelperCreator daoHelperCreator) {
        super(daoHelperCreator);
    }

    @Override
    public UserDto apply(UserDto userDto, int facultyId, int averageMark,
                         Map<Integer, Integer> subjectsMarks) throws ServiceException {
        DaoHelper daoHelper = null;
        try {
            daoHelper = daoHelperCreator.create();
            daoHelper.startTransaction();

            CertificateDao certificateDao = daoHelper.createCertificateDao();
            saveCertificates(certificateDao, userDto, subjectsMarks);

            UserDtoDao userDtoDao = daoHelper.createUserDtoDao();
            UserDto userDtoApplied =
                    updateUserApplication(userDtoDao, userDto, facultyId, averageMark, WAITING);

            daoHelper.commitTransaction();
            return userDtoApplied;
        } catch (DaoException e) {
            try {
                daoHelper.rollBackTransaction();
            } catch (DaoException daoException) {
                LOGGER.error(daoException.getMessage(), daoException);
            }
            throw new ServiceException(e.getMessage(), e);
        } finally {
            if (daoHelper != null) {
                try {
                    daoHelper.finishTransaction();
                } catch (DaoException e) {
                    LOGGER.error(e.getMessage(), e);
                }
                daoHelper.close();
            }
        }
    }

    @Override
    public UserDto cancelApplication(UserDto userDto) throws ServiceException {
        DaoHelper daoHelper = null;
        try {
            daoHelper = daoHelperCreator.create();
            daoHelper.startTransaction();

            CertificateDao certificateDao = daoHelper.createCertificateDao();
            removeCertificates(certificateDao, userDto);

            UserDtoDao userDtoDao = daoHelper.createUserDtoDao();
            UserDto userDtoCancelled =
                    updateUserApplication(userDtoDao, userDto, null, null, null);

            daoHelper.commitTransaction();
            return userDtoCancelled;
        } catch (DaoException e) {
            try {
                daoHelper.rollBackTransaction();
            } catch (DaoException daoException) {
                LOGGER.error(daoException.getMessage(), daoException);
            }
            throw new ServiceException(e.getMessage(), e);
        } finally {
            if (daoHelper != null) {
                try {
                    daoHelper.finishTransaction();
                } catch (DaoException e) {
                    LOGGER.error(e.getMessage(), e);
                }
                daoHelper.close();
            }
        }
    }

    private void saveCertificates(CertificateDao certificateDao, UserDto userDto, Map<Integer, Integer> subjectsMarks)
            throws DaoException {
        int userId = userDto.getId();
        Set<Integer> idSubjects = subjectsMarks.keySet();
        Iterator<Integer> iterator = idSubjects.iterator();
        while (iterator.hasNext()) {
            Integer idSubject = iterator.next();
            Integer mark = subjectsMarks.get(idSubject);
            Certificate certificate = new Certificate(0, userId, idSubject, mark);
            certificateDao.save(certificate);
        }
    }

    private void removeCertificates(CertificateDao certificateDao, UserDto userDto) throws DaoException {
        int userId = userDto.getId();
        certificateDao.removeByUserId(userId);
    }


    private UserDto updateUserApplication(UserDtoDao userDtoDao, UserDto userDto, Integer facultyId,
                                          Integer averageMark, ApplicationStatus status) throws DaoException {
        UserDto updatedUserDto = updateUserDto(userDto, facultyId, averageMark, status);
        userDtoDao.save(updatedUserDto);
        int userId = userDto.getId();
        Optional<UserDto> optionalUserDto = userDtoDao.findById(userId);
        return optionalUserDto.get();
    }

    private UserDto updateUserDto(UserDto userDto, Integer facultyId,
                                  Integer averageMark, ApplicationStatus status) {
        int id = userDto.getId();
        Role role = userDto.getRole();
        String name = userDto.getName();
        String surname = userDto.getSurname();
        return new UserDto(id, role, name, surname, facultyId, averageMark, status);
    }

}
package com.epam.university.service.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.api.FacultyDtoDao;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.dao.persistent.api.CertificateDao;
import com.epam.university.dao.persistent.api.UserDtoDao;
import com.epam.university.model.Applicant;
import com.epam.university.model.identifiable.FacultyDto;
import com.epam.university.model.identifiable.user.ApplicationStatus;
import com.epam.university.model.identifiable.user.UserDto;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.CommitteeService;
import com.epam.university.service.impl.comparator.ApplicantBalAmountComparator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommitteeServiceImpl extends RegistrationServiceImpl implements CommitteeService {

    private final static Logger LOGGER = LogManager.getLogger();

    private final static ApplicationStatus WAITING = ApplicationStatus.WAITING;
    private final static ApplicationStatus REGISTERED = ApplicationStatus.REGISTERED;
    private final static ApplicationStatus ENTERED = ApplicationStatus.ENTERED;

    public CommitteeServiceImpl(DaoHelperCreator daoHelperCreator) {
        super(daoHelperCreator);
    }

    @Override
    public void closeRegistration() throws ServiceException {
        DaoHelper daoHelper = null;
        try {
            daoHelper = daoHelperCreator.create();
            daoHelper.startTransaction();

            UserDtoDao userDtoDao = daoHelper.createUserDtoDao();
            userDtoDao.changeAllApplicationStatus(WAITING, REGISTERED);

            FacultyDtoDao facultyDtoDao = daoHelper.createFacultyDtoDao();
            List<FacultyDto> facultiesDto = facultyDtoDao.findAll();

            CertificateDao certificateDao = daoHelper.createCertificateDao();
            for (FacultyDto facultyDto : facultiesDto) {
                int facultyId = facultyDto.getId();
                List<UserDto> registeredUsers =
                        userDtoDao.findAllByApplicationStatusAndFacultyId(REGISTERED, facultyId);
                List<Applicant> registeredApplicants = transform(certificateDao, registeredUsers);
                List<Applicant> enteredApplicants = findEnteredApplicants(facultyDto, registeredApplicants);

                for (Applicant applicant : enteredApplicants) {
                    int userId = applicant.getId();
                    userDtoDao.changeApplicationStatusByUserId(userId, ENTERED);
                }
            }
            daoHelper.commitTransaction();
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

    private List<Applicant> transform(CertificateDao certificateDao, List<UserDto> usersDto)
            throws DaoException {
        List<Applicant> applicants = new ArrayList<>();
        for (UserDto userDto : usersDto) {
            int userId = userDto.getId();
            int balAmount = findBalAmount(certificateDao, userDto);
            applicants.add(new Applicant(userId, balAmount));
        }
        return applicants;
    }

    private int findBalAmount(CertificateDao certificateDao, UserDto userDto) throws DaoException {
        int userId = userDto.getId();
        int marksSum = certificateDao.findSumMarksByUserId(userId);
        int averageMark = userDto.getAverageMark();
        return marksSum + averageMark;
    }

    private List<Applicant> findEnteredApplicants(FacultyDto facultyDto, List<Applicant> applicants) {
        int recruitment = facultyDto.getRecruitment();
        if (applicants.size() <= recruitment) {
            return new ArrayList<>(applicants);
        }
        List<Applicant> sortedApplicants = new ArrayList<>(applicants);
        Collections.sort(sortedApplicants, new ApplicantBalAmountComparator());
        return new ArrayList<>(sortedApplicants.subList(0, recruitment));
    }

}
package com.epam.university.dao.persistent.api;

import com.epam.university.dao.DaoException;
import com.epam.university.model.identifiable.Application;
import com.epam.university.model.identifiable.ApplicationStatus;

import java.util.List;
import java.util.Optional;

public interface ApplicationDao extends PersistentDao<Application> {

    List<Application> findAllByStatusAndFaculty(ApplicationStatus status,
                                                int facultyId) throws DaoException;

    List<Application> findAllByFaculty(int facultyId) throws DaoException;

    Optional<Application> findByUserAndStatus(int userId, ApplicationStatus status)
            throws DaoException;

    Optional<Application> findActualByUser(int userId) throws DaoException;

    void replaceAllStatus(ApplicationStatus dataStatus,
                          ApplicationStatus replaceStatus) throws DaoException;

    void changeStatusById(int id, ApplicationStatus status) throws DaoException;

    int findCountByStatus(ApplicationStatus status) throws DaoException;

    int findNumberActualApplicationsByFaculty(int facultyId) throws DaoException;

    List<Integer> findActualAmountsAverageMarkAndCertificatesByFaculty(int facultyId)
            throws DaoException;

}
package com.epam.university.dao.persistent.api;

import com.epam.university.dao.DaoException;
import com.epam.university.model.identifiable.Application;
import com.epam.university.model.identifiable.ApplicationStatus;

import java.util.List;
import java.util.Optional;

public interface ApplicationDao extends PersistentDao<Application> {

    /**
     * This method is used to find all objects type Application by parameters
     * having name status, faculty and equal values status, facultyId from
     * data warehouse. This method will return empty list, if data warehouse
     * does not contain objects with equal values status and facultyId.
     *
     * @param status
     * @param facultyId
     * @return
     * @throws DaoException
     */
    List<Application> findAllByStatusAndFaculty(ApplicationStatus status,
                                                int facultyId) throws DaoException;

    /**
     * This method is used to find all objects type Application by parameters
     * having name faculty and equal value facultyId from data warehouse.
     * This method will return empty list, if data warehouse does not contain
     * objects with equal values status and facultyId.
     *
     * @param facultyId
     * @return
     * @throws DaoException
     */
    List<Application> findAllByFaculty(int facultyId) throws DaoException;

    /**
     * This method is used to find object type Application by parameters having
     * name user, status and equal values userId, status from data warehouse.
     * This method will return empty optional, if data warehouse does not contain
     * objects with equal values userId and status.
     *
     * @param userId
     * @param status
     * @return
     * @throws DaoException
     */
    Optional<Application> findByUserAndStatus(int userId, ApplicationStatus status)
            throws DaoException;

    /**
     * This method is used to find object type Application by parameter having
     * name user and equal value userId from data warehouse. Parameter status
     * of Application object can't be CANCELLED. This method will return empty
     * optional, if data warehouse does not contain objects with equal value
     * userId or which status equal value CANCELLED.
     *
     * @param userId
     * @return
     * @throws DaoException
     */
    Optional<Application> findByUserAndStatusNotCancelled(int userId) throws DaoException;

    /**
     * This method is used to change all applications where status equal value
     * dataStatus, on status equal value replaceStatus.
     *
     * @param dataStatus
     * @param replaceStatus
     * @throws DaoException
     */
    void replaceAllStatus(ApplicationStatus dataStatus,
                          ApplicationStatus replaceStatus) throws DaoException;

    /**
     * This method is used to change application field status where field id
     * equal value id, on status equal value status.
     *
     * @param id
     * @param status
     * @throws DaoException
     */
    void changeStatusById(int id, ApplicationStatus status) throws DaoException;

    /**
     * This method is used to find number of applications where field status
     * equal value of param status.
     *
     * @param status
     * @return
     * @throws DaoException
     */
    int findCountByStatus(ApplicationStatus status) throws DaoException;

    /**
     * This method is used to find number of applications by faculty where
     * status of application is not equal CANCELLED.
     *
     * @param facultyId
     * @return
     * @throws DaoException
     */
    int findNumberApplicationsByFacultyAndStatusNotCancelled(int facultyId) throws DaoException;

    /**
     * This method is used to find list values. Every value equal amount average
     * mark of application and amount marks of certificates, which are relevant
     * to this application. Application must have field faculty equal facultyId
     * and status not equal CANCELLED. This method will return empty list, if
     * data warehouse does not contain objects with described higher conditions.
     *
     * @param facultyId
     * @return
     * @throws DaoException
     */
    List<Integer> findAmountsAverageMarkAndCertificatesByFacultyAndStatusNotCancelled(int facultyId)
            throws DaoException;

}
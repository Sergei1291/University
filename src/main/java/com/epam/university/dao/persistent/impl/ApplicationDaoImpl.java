package com.epam.university.dao.persistent.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.mapper.impl.ApplicationRowMapper;
import com.epam.university.dao.persistent.api.AbstractPersistentDao;
import com.epam.university.dao.persistent.api.ApplicationDao;
import com.epam.university.dao.persistent.extractor.impl.ApplicationFieldExtractor;
import com.epam.university.dao.persistent.helper.DaoPersistentHelperImpl;
import com.epam.university.model.identifiable.Application;
import com.epam.university.model.identifiable.ApplicationStatus;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ApplicationDaoImpl extends AbstractPersistentDao<Application> implements ApplicationDao {

    private static final String TABLE_NAME = "application";

    private static final String FUNCTION_COUNT = "count";
    private static final String FUNCTION_AMOUNT = "amount";

    private static final String FIND_ALL_BY_STATUS_AND_FACULTY =
            "select * from application where status = ? and faculty = ?;";
    private static final String FIND_ALL_BY_FACULTY =
            "select * from application where faculty = ?;";
    private static final String FIND_BY_USER_AND_STATUS =
            "select * from application where user = ? and status = ?;";
    private static final String FIND_ACTUAL_BY_USER =
            "select * from application where user = ? and status != 'cancelled';";
    private static final String REPLACE_ALL_STATUS =
            "update application set status = ? where status = ?;";
    private static final String CHANGE_STATUS_BY_ID =
            "update application set status = ? where id = ?;";
    private static final String FIND_COUNT_BY_STATUS =
            "select COUNT(*) as count from application where status = ?;";
    private static final String FIND_NUMBER_ACTUAL_APPLICATIONS_BY_FACULTY =
            "select COUNT(*) as count from application where faculty = ? and status != 'cancelled';";
    private static final String FIND_ACTUAL_APPLICATIONS_AMOUNTS_AVERAGE_MARK_AND_CERTIFICATES_BY_FACULTY =
            "select (application.average_mark + marks.sum) as amount from application " +
                    "inner join (select application, sum(mark) as sum from certificate " +
                    "group by application) marks on marks.application=application.id" +
                    " where application.faculty = ? and application.status != 'cancelled';";

    public ApplicationDaoImpl(Connection connection) {
        super(connection, new ApplicationRowMapper(), TABLE_NAME,
                new ApplicationFieldExtractor(), new DaoPersistentHelperImpl());
    }

    @Override
    public List<Application> findAllByStatusAndFaculty(ApplicationStatus status,
                                                       int facultyId) throws DaoException {
        String statusName = status.name();
        return executeQuery(FIND_ALL_BY_STATUS_AND_FACULTY, statusName, facultyId);
    }

    @Override
    public List<Application> findAllByFaculty(int facultyId) throws DaoException {
        return executeQuery(FIND_ALL_BY_FACULTY, facultyId);
    }

    @Override
    public Optional<Application> findByUserAndStatus(int userId, ApplicationStatus status)
            throws DaoException {
        String statusName = status.name();
        return executeForSingleResult(FIND_BY_USER_AND_STATUS, userId, statusName);
    }

    @Override
    public Optional<Application> findActualByUser(int userId) throws DaoException {
        return executeForSingleResult(FIND_ACTUAL_BY_USER, userId);
    }

    @Override
    public void replaceAllStatus(ApplicationStatus dataStatus,
                                 ApplicationStatus replaceStatus) throws DaoException {
        String dataStatusName = dataStatus.name();
        String replaceStatusName = replaceStatus.name();
        executeUpdate(REPLACE_ALL_STATUS, replaceStatusName, dataStatusName);
    }

    @Override
    public void changeStatusById(int id, ApplicationStatus status)
            throws DaoException {
        String statusName = status.name();
        executeUpdate(CHANGE_STATUS_BY_ID, statusName, id);
    }

    @Override
    public int findCountByStatus(ApplicationStatus status) throws DaoException {
        String statusName = status.name();
        return executeForSingleFunctionResult(FUNCTION_COUNT,
                FIND_COUNT_BY_STATUS, statusName);
    }

    @Override
    public int findNumberActualApplicationsByFaculty(int facultyId) throws DaoException {
        return executeForSingleFunctionResult(FUNCTION_COUNT,
                FIND_NUMBER_ACTUAL_APPLICATIONS_BY_FACULTY, facultyId);
    }

    @Override
    public List<Integer> findActualAmountsAverageMarkAndCertificatesByFaculty(int facultyId)
            throws DaoException {
        return executeForFunctionResults(FUNCTION_AMOUNT,
                FIND_ACTUAL_APPLICATIONS_AMOUNTS_AVERAGE_MARK_AND_CERTIFICATES_BY_FACULTY, facultyId);
    }

}
package com.epam.university.dao.persistent.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.mapper.impl.UserDtoRowMapper;
import com.epam.university.dao.persistent.api.AbstractPersistentDao;
import com.epam.university.dao.persistent.api.UserDtoDao;
import com.epam.university.dao.persistent.extractor.impl.UserDtoFieldExtractor;
import com.epam.university.dao.persistent.helper.DaoPersistentHelperImpl;
import com.epam.university.model.identifiable.user.ApplicationStatus;
import com.epam.university.model.identifiable.user.UserDto;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserDtoDaoImpl extends AbstractPersistentDao<UserDto> implements UserDtoDao {

    private static final String TABLE_NAME = "user";
    private static final String FUNCTION_COUNT = "count";
    private static final String FUNCTION_AMOUNT = "amount";

    private static final String FIND_NUMBER_USERS_BY_APPLICATION_STATUS =
            "select COUNT(*) as count from user where application_status = ?;";
    private static final String FIND_NUMBER_USERS_BY_FACULTY_ID =
            "select COUNT(*) as count from user where faculty = ?;";
    private static final String FIND_BY_LOGIN_AND_PASSWORD =
            "select * from user where login = ? and password = sha1(?);";
    private static final String CHANGE_ALL_APPLICATION_STATUS =
            "update user set application_status = ? where application_status = ?;";
    private static final String FIND_ALL_BY_APPLICATION_STATUS_AND_FACULTY_ID =
            "select * from user where application_status = ? and faculty = ?;";
    private static final String CHANGE_APPLICATION_STATUS_BY_USER_ID =
            "update user set application_status = ? where id = ?;";
    private static final String FIND_ALL_BY_FACULTY_ID_AND_APPLICATION_STATUS_NOT_NULL =
            "select * from user where application_status is not null and faculty = ?;";
    private static final String FIND_USERS_AMOUNTS_AVERAGE_MARK_AND_CERTIFICATE_BY_FACULTY_ID =
            "select (user.average_mark + marks.sum) as amount from user " +
                    "inner join (select user, sum(mark) as sum from certificate group by user) marks " +
                    "on marks.user=user.id where user.faculty = ?;";

    public UserDtoDaoImpl(Connection connection) {
        super(connection, new UserDtoRowMapper(), TABLE_NAME,
                new UserDtoFieldExtractor(), new DaoPersistentHelperImpl());
    }

    @Override
    public int findNumberUsersByApplicationStatus(ApplicationStatus status) throws DaoException {
        List<Integer> results = executeForFunctionResults(FUNCTION_COUNT,
                FIND_NUMBER_USERS_BY_APPLICATION_STATUS, status.name());
        return results.get(0);
    }

    @Override
    public int findNumberUsersByFacultyId(Integer facultyId) throws DaoException {
        List<Integer> results = executeForFunctionResults(FUNCTION_COUNT,
                FIND_NUMBER_USERS_BY_FACULTY_ID, facultyId);
        return results.get(0);
    }

    @Override
    public Optional<UserDto> findByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD, login, password);
    }

    @Override
    public void changeAllApplicationStatus(ApplicationStatus dataStatus,
                                           ApplicationStatus replaceStatus) throws DaoException {
        executeUpdate(CHANGE_ALL_APPLICATION_STATUS, replaceStatus.name(), dataStatus.name());
    }

    @Override
    public List<UserDto> findAllByApplicationStatusAndFacultyId(ApplicationStatus status,
                                                                Integer facultyId) throws DaoException {
        return executeQuery(FIND_ALL_BY_APPLICATION_STATUS_AND_FACULTY_ID, status.name(), facultyId);
    }

    @Override
    public void changeApplicationStatusByUserId(int userId, ApplicationStatus applicationStatus)
            throws DaoException {
        executeUpdate(CHANGE_APPLICATION_STATUS_BY_USER_ID, applicationStatus.name(), userId);
    }

    @Override
    public List<UserDto> findAllByFacultyIdAndApplicationStatusNotNull(Integer facultyId)
            throws DaoException {
        return executeQuery(FIND_ALL_BY_FACULTY_ID_AND_APPLICATION_STATUS_NOT_NULL, facultyId);
    }

    @Override
    public List<Integer> findUsersAmountsAverageMarkAndMarksCertificateByFacultyId(Integer facultyId)
            throws DaoException {
        return executeForFunctionResults(FUNCTION_AMOUNT,
                FIND_USERS_AMOUNTS_AVERAGE_MARK_AND_CERTIFICATE_BY_FACULTY_ID, facultyId);
    }

}
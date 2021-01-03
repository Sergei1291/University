package com.epam.university.dao.impl;

import com.epam.university.dao.AbstractDao;
import com.epam.university.dao.DaoException;
import com.epam.university.dao.UserDtoDao;
import com.epam.university.mapper.impl.UserDtoRowMapper;
import com.epam.university.model.user.ApplicationStatus;
import com.epam.university.model.user.UserDto;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserDtoDaoImpl extends AbstractDao<UserDto> implements UserDtoDao {

    private static final String TABLE_NAME = "user";

    private static final String FIND_NUMBER_USERS_APPLICATION_STATUS =
            "select COUNT(*) as count from user where application_status= ?;";
    private static final String FIND_BY_LOGIN_AND_PASSWORD =
            "select * from user where login = ? and password = sha1(?);";
    private static final String UPDATE_USER_APPLICATION =
            "update user set faculty = ? , average_mark = ? , application_status = ? where id = ? ;";
    private static final String CHANGE_ALL_APPLICATION_STATUS =
            "update user set application_status = ? where application_status = ?;";
    private static final String FIND_ALL_BY_APPLICATION_STATUS_AND_FACULTY =
            "select * from user where application_status = ? and faculty = ?;";

    public UserDtoDaoImpl(Connection connection) {

        super(connection, new UserDtoRowMapper(), TABLE_NAME);

    }

    @Override
    public int findNumberUsersApplicationStatus(ApplicationStatus status) throws DaoException {

        return executeQueryCounter(FIND_NUMBER_USERS_APPLICATION_STATUS, status.name());
    }

    @Override
    public Optional<UserDto> findByLoginAndPassword(String login, String password) throws DaoException {

        return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD,
                login, password);
    }

    @Override
    public void updateApplication(int userId, Integer facultyId, Integer averageMark,
                                  ApplicationStatus status) throws DaoException {
        if (status != null) {
            executeUpdate(UPDATE_USER_APPLICATION, facultyId, averageMark, status.name(), userId);
            return;
        }

        executeUpdate(UPDATE_USER_APPLICATION, facultyId, averageMark, null, userId);

    }

    @Override
    public int changeAllApplicationStatus(ApplicationStatus dataStatus,
                                          ApplicationStatus replaceStatus) throws DaoException {

        return executeUpdate(CHANGE_ALL_APPLICATION_STATUS, replaceStatus.name(), dataStatus.name());
    }

    @Override
    public List<UserDto> findAllByApplicationStatusAndFaculty(ApplicationStatus status,
                                                              Integer faculty) throws DaoException {

        return executeQuery(FIND_ALL_BY_APPLICATION_STATUS_AND_FACULTY, status.name(), faculty);
    }

}
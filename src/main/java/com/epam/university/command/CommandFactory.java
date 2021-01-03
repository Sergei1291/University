package com.epam.university.command;

import com.epam.university.command.impl.*;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.service.impl.FacultyServiceImpl;
import com.epam.university.service.impl.ApplicationServiceImpl;
import com.epam.university.service.impl.UserDtoServiceImpl;

public class CommandFactory {

    private final static String ACCOUNT = "account";
    private final static String APPLICATION = "application";
    private final static String APPLY = "apply";
    private final static String AUTHORIZATION = "authorization";
    private final static String CANCEL_APPLICATION = "cancelApplication";
    private final static String ENROLLED_APPLICANTS = "enrolledApplicants";
    private final static String LOCAL = "local";
    private final static String LOGIN = "login";
    private final static String LOGOUT = "logout";
    private final static String MAIN = "main";
    private final static String RECRUITMENT = "recruitment";
    private final static String REGISTER = "register";
    private final static String SELECT_FACULTY = "selectFaculty";

    public static Command create(String command) {

        switch (command) {
            case ACCOUNT:
                return new AccountCommand();
            case APPLICATION:
                return new ApplicationCommand(new FacultyServiceImpl(new DaoHelperCreator()));
            case APPLY:
                return new ApplyCommand(new ApplicationServiceImpl(new DaoHelperCreator()));
            case AUTHORIZATION:
                return new AuthorizationCommand();
            case CANCEL_APPLICATION:
                return new CancelApplicationCommand(new ApplicationServiceImpl(new DaoHelperCreator()));
            case ENROLLED_APPLICANTS:
                return new EnrolledApplicantsCommand(new ApplicationServiceImpl(new DaoHelperCreator()));
            case LOCAL:
                return new LocalCommand();
            case LOGIN:
                return new LoginCommand(new UserDtoServiceImpl(new DaoHelperCreator()));
            case LOGOUT:
                return new LogoutCommand();
            case MAIN:
                return new MainCommand(new UserDtoServiceImpl(new DaoHelperCreator()));
            case RECRUITMENT:
                return new RecruitmentCommand(new FacultyServiceImpl(new DaoHelperCreator()));
            case REGISTER:
                return new RegisterCommand(new ApplicationServiceImpl(new DaoHelperCreator()));
            case SELECT_FACULTY:
                return new SelectFacultyCommand(new FacultyServiceImpl(new DaoHelperCreator()));
            default:
                throw new IllegalArgumentException("illegal command " + command);
        }

    }

}
package com.epam.university.command;

import com.epam.university.command.impl.*;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.service.impl.*;

public class CommandFactory {

    private final static String ACCOUNT = "account";
    private final static String APPLICATION_FORM = "applicationForm";
    private final static String APPLICATIONS_INFO = "applicationsInfo";
    private final static String APPLY = "apply";
    private final static String AUTHORIZATION = "authorization";
    private final static String CANCEL_APPLICATION = "cancelApplication";
    private final static String CLOSE_REGISTRATION = "closeRegistration";
    private final static String ENTERED_APPLICANTS = "enteredApplicants";
    private final static String ENTRANCE_COMPANY = "entranceCompany";
    private final static String LOCAL = "local";
    private final static String LOGIN = "login";
    private final static String LOGOUT = "logout";
    private final static String MAIN = "main";
    private final static String PERSONAL_APPLICATION = "personalApplication";
    private final static String REGISTRATION_SUCCESS = "registrationSuccess";
    private final static String SELECTION_FACULTY = "selectionFaculty";
    private final static String STATISTIC_APPLICANTS = "statisticApplicants";

    public static Command create(String command) {
        switch (command) {
            case ACCOUNT:
                return new AccountCommand(new RegistrationServiceImpl(new DaoHelperCreator()));
            case APPLICATION_FORM:
                return new ApplicationFormCommand(new FacultyServiceImpl(new DaoHelperCreator()));
            case APPLICATIONS_INFO:
                return new ApplicationsInfoCommand(new FacultyApplicantServiceImpl(new DaoHelperCreator()));
            case APPLY:
                return new ApplyCommand(new EnrolleeServiceImpl(new DaoHelperCreator()));
            case AUTHORIZATION:
                return new AuthorizationCommand();
            case CANCEL_APPLICATION:
                return new CancelApplicationCommand(new EnrolleeServiceImpl(new DaoHelperCreator()));
            case CLOSE_REGISTRATION:
                return new CloseRegistrationCommand(new CommitteeServiceImpl(new DaoHelperCreator()));
            case ENTERED_APPLICANTS:
                return new EnteredApplicantsCommand(new EnteredApplicantServiceImpl(new DaoHelperCreator()));
            case ENTRANCE_COMPANY:
                return new EntranceCompanyCommand(new EntranceCompanyServiceImpl(new DaoHelperCreator()));
            case LOCAL:
                return new LocalCommand();
            case LOGIN:
                return new LoginCommand(new LoginServiceImpl(new DaoHelperCreator()));
            case LOGOUT:
                return new LogoutCommand();
            case MAIN:
                return new MainCommand(new RegistrationServiceImpl(new DaoHelperCreator()));
            case PERSONAL_APPLICATION:
                return new PersonalApplicationCommand(new PersonalApplicationServiceImpl(new DaoHelperCreator()));
            case REGISTRATION_SUCCESS:
                return new RegistrationSuccessCommand(new RegistrationServiceImpl(new DaoHelperCreator()));
            case SELECTION_FACULTY:
                return new SelectionFacultyCommand(new FacultyDtoServiceImpl(new DaoHelperCreator()));
            case STATISTIC_APPLICANTS:
                return new StatisticApplicantsCommand(new StatisticApplicantServiceImpl(new DaoHelperCreator()));
            default:
                throw new IllegalArgumentException("illegal command " + command);
        }
    }

}
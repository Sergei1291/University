package com.epam.university.command;

import com.epam.university.command.impl.ApplicationsInfoCommand;
import com.epam.university.context.RequestContext;
import com.epam.university.model.FullApplication;
import com.epam.university.model.identifiable.*;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.FacultyApplicantService;
import com.epam.university.service.impl.FacultyApplicantServiceImpl;
import com.epam.university.validator.NumberValidator;
import com.epam.university.validator.Validator;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class ApplicationsInfoCommandTest {

    private final static String PAGE_ERROR = "WEB-INF/view/error.jsp";
    private final static String PAGE_APPLICATIONS_INFO = "WEB-INF/view/applicationsInfo.jsp";

    private final static String FACULTY_ID_PARAMETER = "facultyId";
    private final static String[] FACULTY_ID_VALUE = new String[]{"1"};
    private final static String APPLICATIONS_INFO_ATTRIBUTE = "applicationsInfo";
    private final static String MESSAGE_ATTRIBUTE = "message";

    @Test
    public void testExecuteShouldForwardErrorPageWhenFacultyIdNotValid() throws ServiceException {
        //given
        FacultyApplicantService facultyApplicantService = Mockito.mock(FacultyApplicantServiceImpl.class);
        Validator<String> validator = Mockito.mock(NumberValidator.class);
        when(validator.isValid(anyString())).thenReturn(false);
        ApplicationsInfoCommand applicationsInfoCommand =
                new ApplicationsInfoCommand(facultyApplicantService, validator);
        RequestContext actualRequestContext = new RequestContext();
        actualRequestContext.setRequestParameter(FACULTY_ID_PARAMETER, FACULTY_ID_VALUE);
        //when
        CommandResult actual = applicationsInfoCommand.execute(actualRequestContext);
        //then
        CommandResult expected = CommandResult.forward(PAGE_ERROR);
        Assert.assertEquals(expected, actual);

        RequestContext expectedRequestContext = new RequestContext();
        expectedRequestContext.setRequestParameter(FACULTY_ID_PARAMETER, FACULTY_ID_VALUE);
        expectedRequestContext.setRequestAttribute(MESSAGE_ATTRIBUTE, "PARAMETER IS NOT VALID: 1");
        Assert.assertEquals(expectedRequestContext, actualRequestContext);
    }

    @Test
    public void testExecuteShouldForwardApplicationsInfoPageWhenFacultyIdValid() throws ServiceException {
        //given
        FacultyApplicantService facultyApplicantService = Mockito.mock(FacultyApplicantServiceImpl.class);
        FullApplication fullApplication = new FullApplication(
                new Application(1, 1, 1, 1, ApplicationStatus.APPLIED),
                new UserDto(1, UserRole.ENROLLEE, "", ""),
                Arrays.asList(new Certificate(1, 1, 1, 1)));
        List<FullApplication> fullApplications = Arrays.asList(fullApplication);
        when(facultyApplicantService.findFacultyApplicationsInfo(anyInt())).thenReturn(fullApplications);
        Validator<String> validator = Mockito.mock(NumberValidator.class);
        when(validator.isValid(anyString())).thenReturn(true);
        ApplicationsInfoCommand applicationsInfoCommand =
                new ApplicationsInfoCommand(facultyApplicantService, validator);
        RequestContext actualRequestContext = new RequestContext();
        actualRequestContext.setRequestParameter(FACULTY_ID_PARAMETER, FACULTY_ID_VALUE);
        //when
        CommandResult actual = applicationsInfoCommand.execute(actualRequestContext);
        //then
        CommandResult expected = CommandResult.forward(PAGE_APPLICATIONS_INFO);
        Assert.assertEquals(expected, actual);

        RequestContext expectedRequestContext = new RequestContext();
        expectedRequestContext.setRequestParameter(FACULTY_ID_PARAMETER, FACULTY_ID_VALUE);
        expectedRequestContext.setRequestAttribute(APPLICATIONS_INFO_ATTRIBUTE, fullApplications);
        Assert.assertEquals(expectedRequestContext, actualRequestContext);
    }

}
package com.epam.university.command;

import com.epam.university.command.impl.ApplicationFormCommand;
import com.epam.university.context.RequestContext;
import com.epam.university.model.identifiable.Subject;
import com.epam.university.model.identifiable.SubjectName;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.FacultySubjectService;
import com.epam.university.service.impl.FacultySubjectServiceImpl;
import com.epam.university.validator.NumberValidator;
import com.epam.university.validator.Validator;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class ApplicationFormCommandTest {

    private final static String PAGE_ERROR = "WEB-INF/view/error.jsp";
    private final static String PAGE_APPLICATION_FORM = "WEB-INF/view/applicationForm.jsp";

    private final static String FACULTY_ID_PARAMETER = "facultyId";
    private final static String[] FACULTY_ID_VALUE = new String[]{"1"};
    private final static String SUBJECTS_ATTRIBUTE = "subjects";
    private final static String MESSAGE_ATTRIBUTE = "message";

    @Test
    public void testExecuteShouldForwardErrorPageWhenFacultyIdNotValid() throws ServiceException {
        //given
        FacultySubjectService facultySubjectService = Mockito.mock(FacultySubjectServiceImpl.class);
        Validator<String> validator = Mockito.mock(NumberValidator.class);
        when(validator.isValid(anyString())).thenReturn(false);
        ApplicationFormCommand applicationFormCommand =
                new ApplicationFormCommand(facultySubjectService, validator);
        RequestContext actualRequestContext = new RequestContext();
        actualRequestContext.setRequestParameter(FACULTY_ID_PARAMETER, FACULTY_ID_VALUE);
        //when
        CommandResult actual = applicationFormCommand.execute(actualRequestContext);
        //then
        CommandResult expected = CommandResult.forward(PAGE_ERROR);
        Assert.assertEquals(expected, actual);

        RequestContext expectedRequestContext = new RequestContext();
        expectedRequestContext.setRequestParameter(FACULTY_ID_PARAMETER, FACULTY_ID_VALUE);
        expectedRequestContext.setRequestAttribute(MESSAGE_ATTRIBUTE, "PARAMETER IS NOT VALID: 1");
        Assert.assertEquals(expectedRequestContext, actualRequestContext);
    }

    @Test
    public void testExecuteShouldForwardErrorPageWhenSubjectListEmpty() throws ServiceException {
        //given
        FacultySubjectService facultySubjectService = Mockito.mock(FacultySubjectServiceImpl.class);
        when(facultySubjectService.findFacultySubjects(anyInt())).thenReturn(new ArrayList<>());
        Validator<String> validator = Mockito.mock(NumberValidator.class);
        when(validator.isValid(anyString())).thenReturn(true);
        ApplicationFormCommand applicationFormCommand =
                new ApplicationFormCommand(facultySubjectService, validator);
        RequestContext actualRequestContext = new RequestContext();
        actualRequestContext.setRequestParameter(FACULTY_ID_PARAMETER, FACULTY_ID_VALUE);
        //when
        CommandResult actual = applicationFormCommand.execute(actualRequestContext);
        //then
        CommandResult expected = CommandResult.forward(PAGE_ERROR);
        Assert.assertEquals(expected, actual);

        RequestContext expectedRequestContext = new RequestContext();
        expectedRequestContext.setRequestParameter(FACULTY_ID_PARAMETER, FACULTY_ID_VALUE);
        expectedRequestContext.setRequestAttribute(MESSAGE_ATTRIBUTE, "PARAMETER IS NOT VALID: 1");
        Assert.assertEquals(expectedRequestContext, actualRequestContext);
    }

    @Test
    public void testExecuteShouldForwardApplicationFormPageWhenSubjectListNotEmpty() throws ServiceException {
        //given
        FacultySubjectService facultySubjectService = Mockito.mock(FacultySubjectServiceImpl.class);
        List<Subject> subjectList = Arrays.asList(new Subject(1, SubjectName.BIOLOGY),
                (new Subject(2, SubjectName.GEOGRAPHY)));
        when(facultySubjectService.findFacultySubjects(anyInt())).thenReturn(subjectList);
        Validator<String> validator = Mockito.mock(NumberValidator.class);
        when(validator.isValid(anyString())).thenReturn(true);
        ApplicationFormCommand applicationFormCommand =
                new ApplicationFormCommand(facultySubjectService, validator);
        RequestContext actualRequestContext = new RequestContext();
        actualRequestContext.setRequestParameter(FACULTY_ID_PARAMETER, FACULTY_ID_VALUE);
        //when
        CommandResult actual = applicationFormCommand.execute(actualRequestContext);
        //then
        CommandResult expected = CommandResult.forward(PAGE_APPLICATION_FORM);
        Assert.assertEquals(expected, actual);

        RequestContext expectedRequestContext = new RequestContext();
        expectedRequestContext.setRequestParameter(FACULTY_ID_PARAMETER, FACULTY_ID_VALUE);
        expectedRequestContext.setRequestAttribute(SUBJECTS_ATTRIBUTE, subjectList);
        Assert.assertEquals(expectedRequestContext, actualRequestContext);
    }

}
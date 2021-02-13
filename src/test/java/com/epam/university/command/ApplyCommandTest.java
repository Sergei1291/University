package com.epam.university.command;

import com.epam.university.command.impl.ApplyCommand;
import com.epam.university.context.RequestContext;
import com.epam.university.model.identifiable.UserDto;
import com.epam.university.model.identifiable.UserRole;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.ApplicationService;
import com.epam.university.service.impl.ApplicationServiceImpl;
import com.epam.university.validator.NumberValidator;
import com.epam.university.validator.Validator;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class ApplyCommandTest {

    private final static String PAGE_ERROR = "WEB-INF/view/error.jsp";
    private final static String LOCALIZE_MESSAGE_ATTRIBUTE = "localizeMessage";

    private final static UserDto USER = new UserDto(1, UserRole.ENROLLEE, "", "");

    private final static String COMMAND_PERSONAL_APPLICATION =
            "/University/controller?command=personalApplication";

    @Test
    public void testExecuteShouldForwardErrorPageWhenRegistrationFinished() throws ServiceException {
        //given
        ApplicationService applicationService = Mockito.mock(ApplicationServiceImpl.class);
        Validator<String> validator = Mockito.mock(NumberValidator.class);
        when(applicationService.isRegistrationFinished()).thenReturn(true);
        ApplyCommand applyCommand = new ApplyCommand(applicationService, validator);
        RequestContext actualRequestContext = new RequestContext();
        //when
        CommandResult actual = applyCommand.execute(actualRequestContext);
        //then
        CommandResult expected = CommandResult.forward(PAGE_ERROR);
        Assert.assertEquals(expected, actual);

        RequestContext expectedRequestContext = new RequestContext();
        expectedRequestContext.setRequestAttribute(LOCALIZE_MESSAGE_ATTRIBUTE, "registration.closed");
        Assert.assertEquals(expectedRequestContext, actualRequestContext);
    }

    @Test
    public void testExecuteShouldForwardPersonalApplicationPageWhenApplicationApplied() throws ServiceException {
        //given
        Map<Integer, Integer> subjectsMarks = new HashMap<Integer, Integer>() {
            {
                put(2, 10);
                put(4, 20);
                put(5, 30);
            }
        };
        ApplicationService applicationService = Mockito.mock(ApplicationServiceImpl.class);
        when(applicationService.apply(USER, 1, 50, subjectsMarks)).thenReturn(true);
        Validator<String> validator = Mockito.mock(NumberValidator.class);
        when(validator.isValid(anyString())).thenReturn(true);
        ApplyCommand applyCommand = new ApplyCommand(applicationService, validator);
        RequestContext actualRequestContext = new RequestContext();
        initializeRequestContext(actualRequestContext);
        //when
        CommandResult actual = applyCommand.execute(actualRequestContext);
        //then
        CommandResult expected = CommandResult.redirect(COMMAND_PERSONAL_APPLICATION);
        Assert.assertEquals(expected, actual);
    }

    private void initializeRequestContext(RequestContext requestContext) {
        requestContext.setSessionAttribute("userDto", USER);
        requestContext.setRequestParameter("facultyId", new String[]{"1"});
        requestContext.setRequestParameter("averageMark", new String[]{"50"});

        requestContext.setRequestParameter("firstSubjectId", new String[]{"2"});
        requestContext.setRequestParameter("secondSubjectId", new String[]{"4"});
        requestContext.setRequestParameter("thirdSubjectId", new String[]{"5"});

        requestContext.setRequestParameter("firstMark", new String[]{"10"});
        requestContext.setRequestParameter("secondMark", new String[]{"20"});
        requestContext.setRequestParameter("thirdMark", new String[]{"30"});
    }

}
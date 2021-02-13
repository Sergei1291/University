package com.epam.university.command;

import com.epam.university.command.impl.CancelApplicationCommand;
import com.epam.university.context.RequestContext;
import com.epam.university.model.identifiable.UserDto;
import com.epam.university.model.identifiable.UserRole;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.CancelApplicationService;
import com.epam.university.service.impl.CancelApplicationServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class CancelApplicationCommandTest {

    private final static String PAGE_ERROR = "WEB-INF/view/error.jsp";
    private final static String COMMAND_PERSONAL_APPLICATION =
            "/University/controller?command=personalApplication";

    private final static String USER_DTO_ATTRIBUTE = "userDto";
    private final static UserDto USER_DTO_ATTRIBUTE_VALUE = new UserDto(1, UserRole.ENROLLEE, "", "");
    private final static String LOCALIZE_MESSAGE_ATTRIBUTE = "localizeMessage";

    @Test
    public void testExecuteShouldForwardErrorPageWhenRegistrationIsClosed() throws ServiceException {
        //given
        CancelApplicationService cancelApplicationService = Mockito.mock(CancelApplicationServiceImpl.class);
        when(cancelApplicationService.isRegistrationFinished()).thenReturn(true);
        CancelApplicationCommand cancelApplicationCommand =
                new CancelApplicationCommand(cancelApplicationService);
        RequestContext actualRequestContext = new RequestContext();
        actualRequestContext.setSessionAttribute(USER_DTO_ATTRIBUTE, USER_DTO_ATTRIBUTE_VALUE);
        //when
        CommandResult actual = cancelApplicationCommand.execute(actualRequestContext);
        //then
        CommandResult expected = CommandResult.forward(PAGE_ERROR);
        Assert.assertEquals(expected, actual);

        RequestContext expectedRequestContext = new RequestContext();
        expectedRequestContext.setSessionAttribute(USER_DTO_ATTRIBUTE, USER_DTO_ATTRIBUTE_VALUE);
        expectedRequestContext.setRequestAttribute(LOCALIZE_MESSAGE_ATTRIBUTE, "registration.closed");
        Assert.assertEquals(expectedRequestContext, actualRequestContext);
    }

    @Test
    public void testExecuteShouldForwardErrorPageWhenApplicationIsCancelled() throws ServiceException {
        //given
        CancelApplicationService cancelApplicationService = Mockito.mock(CancelApplicationServiceImpl.class);
        when(cancelApplicationService.isRegistrationFinished()).thenReturn(false);
        when(cancelApplicationService.cancelApplication(USER_DTO_ATTRIBUTE_VALUE)).thenReturn(false);
        CancelApplicationCommand cancelApplicationCommand =
                new CancelApplicationCommand(cancelApplicationService);
        RequestContext actualRequestContext = new RequestContext();
        actualRequestContext.setSessionAttribute(USER_DTO_ATTRIBUTE, USER_DTO_ATTRIBUTE_VALUE);
        //when
        CommandResult actual = cancelApplicationCommand.execute(actualRequestContext);
        //then
        CommandResult expected = CommandResult.forward(PAGE_ERROR);
        Assert.assertEquals(expected, actual);

        RequestContext expectedRequestContext = new RequestContext();
        expectedRequestContext.setSessionAttribute(USER_DTO_ATTRIBUTE, USER_DTO_ATTRIBUTE_VALUE);
        expectedRequestContext.setRequestAttribute(LOCALIZE_MESSAGE_ATTRIBUTE, "application.not.found");
        Assert.assertEquals(expectedRequestContext, actualRequestContext);
    }

    @Test
    public void testExecuteShouldRedirectCommandPersonalApplicationWhenApplicationIsApplied() throws ServiceException {
        //given
        CancelApplicationService cancelApplicationService = Mockito.mock(CancelApplicationServiceImpl.class);
        when(cancelApplicationService.isRegistrationFinished()).thenReturn(false);
        when(cancelApplicationService.cancelApplication(USER_DTO_ATTRIBUTE_VALUE)).thenReturn(true);
        CancelApplicationCommand cancelApplicationCommand =
                new CancelApplicationCommand(cancelApplicationService);
        RequestContext actualRequestContext = new RequestContext();
        actualRequestContext.setSessionAttribute(USER_DTO_ATTRIBUTE, USER_DTO_ATTRIBUTE_VALUE);
        //when
        CommandResult actual = cancelApplicationCommand.execute(actualRequestContext);
        //then
        CommandResult expected = CommandResult.redirect(COMMAND_PERSONAL_APPLICATION);
        Assert.assertEquals(expected, actual);

        RequestContext expectedRequestContext = new RequestContext();
        expectedRequestContext.setSessionAttribute(USER_DTO_ATTRIBUTE, USER_DTO_ATTRIBUTE_VALUE);
        Assert.assertEquals(expectedRequestContext, actualRequestContext);
    }

    @Test(expected = ServiceException.class)//then
    public void testExecuteShouldThrowExceptionWhenServiceThrowException() throws ServiceException {
        //given
        CancelApplicationService cancelApplicationService = Mockito.mock(CancelApplicationServiceImpl.class);
        when(cancelApplicationService.isRegistrationFinished()).thenThrow(new ServiceException());
        CancelApplicationCommand cancelApplicationCommand =
                new CancelApplicationCommand(cancelApplicationService);
        RequestContext actualRequestContext = new RequestContext();
        actualRequestContext.setSessionAttribute(USER_DTO_ATTRIBUTE, USER_DTO_ATTRIBUTE_VALUE);
        //when
        cancelApplicationCommand.execute(actualRequestContext);
    }

}
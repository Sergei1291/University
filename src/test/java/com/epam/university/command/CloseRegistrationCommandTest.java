package com.epam.university.command;

import com.epam.university.command.impl.CloseRegistrationCommand;
import com.epam.university.context.RequestContext;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.CommitteeService;
import com.epam.university.service.impl.CommitteeServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class CloseRegistrationCommandTest {

    private final static String PAGE_ERROR = "WEB-INF/view/error.jsp";
    private final static String COMMAND_SUCCESS_REGISTRATION =
            "/University/controller?command=successRegistration";
    private final static String LOCALIZE_MESSAGE_ATTRIBUTE = "localizeMessage";

    @Test
    public void testExecuteShouldForwardErrorPageWhenRegistrationIsFinished() throws ServiceException {
        //given
        CommitteeService committeeService = Mockito.mock(CommitteeServiceImpl.class);
        when(committeeService.isRegistrationFinished()).thenReturn(true);
        CloseRegistrationCommand closeRegistrationCommand =
                new CloseRegistrationCommand(committeeService);
        RequestContext actualRequestContext = new RequestContext();
        //when
        CommandResult actual = closeRegistrationCommand.execute(actualRequestContext);
        //then
        CommandResult expected = CommandResult.forward(PAGE_ERROR);
        Assert.assertEquals(expected, actual);

        RequestContext expectedRequestContext = new RequestContext();
        expectedRequestContext.setRequestAttribute(LOCALIZE_MESSAGE_ATTRIBUTE, "registration.closed");
        Assert.assertEquals(expectedRequestContext, actualRequestContext);
    }

    @Test
    public void testExecuteShouldRedirectSuccessRegistrationWhenRegistrationSuccess() throws ServiceException {
        CommitteeService committeeService = Mockito.mock(CommitteeServiceImpl.class);
        when(committeeService.isRegistrationFinished()).thenReturn(false);
        CloseRegistrationCommand closeRegistrationCommand =
                new CloseRegistrationCommand(committeeService);
        RequestContext actualRequestContext = new RequestContext();
        //when
        CommandResult actual = closeRegistrationCommand.execute(actualRequestContext);
        //then
        CommandResult expected = CommandResult.redirect(COMMAND_SUCCESS_REGISTRATION);
        Assert.assertEquals(expected, actual);
        verify(committeeService, times(1)).closeRegistration();
    }

}
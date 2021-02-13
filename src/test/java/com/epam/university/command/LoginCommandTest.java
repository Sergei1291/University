package com.epam.university.command;

import com.epam.university.command.impl.LoginCommand;
import com.epam.university.context.RequestContext;
import com.epam.university.model.identifiable.UserDto;
import com.epam.university.model.identifiable.UserRole;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.LoginService;
import com.epam.university.service.impl.LoginServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class LoginCommandTest {

    private final static String LOGIN_PARAMETER_NAME = "login";
    private final static String[] LOGIN_PARAMETER = new String[]{"loginTest"};
    private final static String PASSWORD_PARAMETER_NAME = "password";
    private final static String[] PASSWORD_PARAMETER = new String[]{"passwordTest"};
    private final static String IS_VISIBLE_ERROR_MESSAGE_ATTRIBUTE = "isVisibleErrorMessage";
    private final static String USER_DTO_ATTRIBUTE = "userDto";

    private final static String PAGE_AUTHORIZATION = "WEB-INF/view/authorization.jsp";
    private final static String COMMAND_ACCOUNT = "/University/controller?command=account";

    @Test
    public void testExecuteShouldPageAuthorizationAndAttributeTrueWhenOptionalEmpty() throws ServiceException {
        //given
        LoginService service = Mockito.mock(LoginServiceImpl.class);
        when(service.login(anyString(), anyString())).thenReturn(Optional.empty());
        LoginCommand loginCommand = new LoginCommand(service);
        RequestContext actualRequestContext = new RequestContext();
        actualRequestContext.setRequestParameter(LOGIN_PARAMETER_NAME, LOGIN_PARAMETER);
        actualRequestContext.setRequestParameter(PASSWORD_PARAMETER_NAME, PASSWORD_PARAMETER);
        //when
        CommandResult actualCommandResult = loginCommand.execute(actualRequestContext);
        //then
        CommandResult expectedCommandResult = CommandResult.forward(PAGE_AUTHORIZATION);
        Assert.assertEquals(expectedCommandResult, actualCommandResult);

        RequestContext expectedRequestContext = new RequestContext();
        expectedRequestContext.setRequestParameter(LOGIN_PARAMETER_NAME, LOGIN_PARAMETER);
        expectedRequestContext.setRequestParameter(PASSWORD_PARAMETER_NAME, PASSWORD_PARAMETER);
        expectedRequestContext.setRequestAttribute(IS_VISIBLE_ERROR_MESSAGE_ATTRIBUTE, true);
        Assert.assertEquals(expectedRequestContext, actualRequestContext);
    }

    @Test
    public void testExecuteShouldCommandAccountWhenOptionalPresent() throws ServiceException {
        //given
        UserDto userDto = new UserDto(1, UserRole.ENROLLEE, "", "");
        LoginService service = Mockito.mock(LoginServiceImpl.class);
        when(service.login(LOGIN_PARAMETER[0], PASSWORD_PARAMETER[0])).thenReturn(Optional.of(userDto));
        LoginCommand loginCommand = new LoginCommand(service);
        RequestContext actualRequestContext = new RequestContext();
        actualRequestContext.setRequestParameter(LOGIN_PARAMETER_NAME, LOGIN_PARAMETER);
        actualRequestContext.setRequestParameter(PASSWORD_PARAMETER_NAME, PASSWORD_PARAMETER);
        //when
        CommandResult actualCommandResult = loginCommand.execute(actualRequestContext);
        //then
        CommandResult expectedCommandResult = CommandResult.redirect(COMMAND_ACCOUNT);
        Assert.assertEquals(expectedCommandResult, actualCommandResult);

        RequestContext expectedRequestContext = new RequestContext();
        expectedRequestContext.setRequestParameter(LOGIN_PARAMETER_NAME, LOGIN_PARAMETER);
        expectedRequestContext.setRequestParameter(PASSWORD_PARAMETER_NAME, PASSWORD_PARAMETER);
        expectedRequestContext.setSessionAttribute(USER_DTO_ATTRIBUTE, userDto);
        Assert.assertEquals(expectedRequestContext, actualRequestContext);
    }

}
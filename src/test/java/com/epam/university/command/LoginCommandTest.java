package com.epam.university.command;

public class LoginCommandTest {
/*
    private final static String LOGIN_PARAMETER_NAME = "login";
    private final static String PASSWORD_PARAMETER_NAME = "password";
    private final static String IS_VISIBLE_ERROR_MESSAGE_ATTRIBUTE_NAME = "isVisibleErrorMessage";
    private final static String USER_DTO_ATTRIBUTE_NAME = "userDto";

    private final static String LOGIN_PARAMETER = "loginTest";
    private final static String PASSWORD_PARAMETER = "passwordTest";

    private final static String PAGE_AUTHORIZATION = "WEB-INF/view/authorization.jsp";
    private final static String PAGE_MAIN = "/University/controller?command=account";

    private final static UserDto USER_DTO = new UserDto(0, "loginTest", null, null, null);

    @Test
    public void testExecuteShouldOptionalEmptyAndAttributeTrueWhenRequestContent() throws ServiceException {
        //given
        UserDtoService service = Mockito.mock(UserDtoService.class);
        when(service.login(anyString(), anyString())).thenReturn(Optional.empty());
        LoginCommand loginCommand = new LoginCommand(service);

        RequestContext actualRequestContext = new RequestContext();
        actualRequestContext.setRequestParameter(LOGIN_PARAMETER_NAME, LOGIN_PARAMETER);
        actualRequestContext.setRequestParameter(PASSWORD_PARAMETER_NAME, PASSWORD_PARAMETER);

        //when
        CommandResult actualCommandResult = loginCommand.execute(actualRequestContext);
        //then
        RequestContext expectedRequestContext = new RequestContext();
        expectedRequestContext.setRequestParameter(LOGIN_PARAMETER_NAME, LOGIN_PARAMETER);
        expectedRequestContext.setRequestParameter(PASSWORD_PARAMETER_NAME, PASSWORD_PARAMETER);
        expectedRequestContext.setRequestAttribute(IS_VISIBLE_ERROR_MESSAGE_ATTRIBUTE_NAME, true);

        CommandResult expectedCommandResult = CommandResult.forward(PAGE_AUTHORIZATION);

        // Assert.assertEquals(expectedRequestContent, actualRequestContent);
        Assert.assertEquals(expectedCommandResult, actualCommandResult);
    }

    @Test
    public void testExecuteShouldOptionalPresentAndAttributeUserDtoWhenRequestContent() throws ServiceException {
        //given
        UserDtoService service = Mockito.mock(UserDtoService.class);
        when(service.login(anyString(), anyString())).thenReturn(Optional.of(USER_DTO));
        LoginCommand loginCommand = new LoginCommand(service);

        RequestContext actualRequestContext = new RequestContext();
        actualRequestContext.setRequestParameter(LOGIN_PARAMETER_NAME, LOGIN_PARAMETER);
        actualRequestContext.setRequestParameter(PASSWORD_PARAMETER_NAME, PASSWORD_PARAMETER);

        //when
        CommandResult actualCommandResult = loginCommand.execute(actualRequestContext);
        //then
        RequestContext expectedRequestContext = new RequestContext();
        expectedRequestContext.setRequestParameter(LOGIN_PARAMETER_NAME, LOGIN_PARAMETER);
        expectedRequestContext.setRequestParameter(PASSWORD_PARAMETER_NAME, PASSWORD_PARAMETER);
        expectedRequestContext.setSessionAttribute(USER_DTO_ATTRIBUTE_NAME, USER_DTO);

        CommandResult expectedCommandResult = CommandResult.redirect(PAGE_MAIN);

        //   Assert.assertEquals(expectedRequestContent, actualRequestContent);
        Assert.assertEquals(expectedCommandResult, actualCommandResult);
    }
*/
}
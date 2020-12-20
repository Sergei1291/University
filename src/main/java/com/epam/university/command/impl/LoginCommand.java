package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.UserDto;
import com.epam.university.service.ServiceException;
import com.epam.university.service.UserDtoService;

import java.util.Optional;

public class LoginCommand implements Command {

    private final static int INDEX_FIRST_VALUE = 0;

    private final static String LOGIN_PARAMETER = "login";
    private final static String PASSWORD_PARAMETER = "password";
    private final static String IS_VISIBLE_ERROR_MESSAGE_ATTRIBUTE = "isVisibleErrorMessage";
    private final static String USER_DTO_ATTRIBUTE = "userDto";

    private final static String PAGE_AUTHORIZATION = "WEB-INF/view/authorization.jsp";
    private final static String COMMAND_MAIN = "/University/controller?command=main";

    private UserDtoService userDtoService;

    public LoginCommand(UserDtoService userDtoService) {

        this.userDtoService = userDtoService;

    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {

        String login = requestContext.getRequestParameter(LOGIN_PARAMETER)[INDEX_FIRST_VALUE];
        String password = requestContext.getRequestParameter(PASSWORD_PARAMETER)[INDEX_FIRST_VALUE];

        Optional<UserDto> optionalUserDto = userDtoService.login(login, password);

        if (!optionalUserDto.isPresent()) {

            requestContext.setRequestAttribute(IS_VISIBLE_ERROR_MESSAGE_ATTRIBUTE, true);

            return CommandResult.forward(PAGE_AUTHORIZATION);
        }

        UserDto userDto = optionalUserDto.get();
        requestContext.setSessionAttribute(USER_DTO_ATTRIBUTE, userDto);

        return CommandResult.redirect(COMMAND_MAIN);
    }

}
package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.service.ServiceException;

public class LogoutCommand implements Command {

    private final static String USER_DTO_ATTRIBUTE = "userDto";
    private final static String COMMAND_MAIN = "/University/controller?command=main";

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        requestContext.setSessionAttribute(USER_DTO_ATTRIBUTE, null);
        return CommandResult.redirect(COMMAND_MAIN);
    }

}
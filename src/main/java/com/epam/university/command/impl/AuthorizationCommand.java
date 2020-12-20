package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.service.ServiceException;

public class AuthorizationCommand implements Command {

    private final static String PAGE_AUTHORIZATION = "WEB-INF/view/authorization.jsp";

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {

        return CommandResult.forward(PAGE_AUTHORIZATION);
    }

}
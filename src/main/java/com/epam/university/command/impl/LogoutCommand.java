package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.service.ServiceException;

public class LogoutCommand implements Command {

    private final static String COMMAND_MAIN = "/University/controller?command=main";

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {

        requestContext.invalidateSession();
        //todo not invalidate: session keep userDto and local
        //todo remove attribute userDto

        return CommandResult.redirect(COMMAND_MAIN);
    }

}
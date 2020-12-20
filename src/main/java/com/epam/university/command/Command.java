package com.epam.university.command;

import com.epam.university.context.RequestContext;
import com.epam.university.service.ServiceException;

public interface Command {

    CommandResult execute(RequestContext requestContext) throws ServiceException;

}
package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.service.ServiceException;

public class LocalCommand implements Command {

    private final static int INDEX_FIRST_VALUE = 0;

    private final static String LOCAL_PARAMETER = "local";
    private final static String QUERY_PARAMETER = "query";
    private final static String LOCAL_ATTRIBUTE = "local";

    private final static String URL = "/University/controller?";

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {

        String localParameter = requestContext.getRequestParameter(LOCAL_PARAMETER)[INDEX_FIRST_VALUE];

        requestContext.setSessionAttribute(LOCAL_ATTRIBUTE, localParameter);

        String query = requestContext.getRequestParameter(QUERY_PARAMETER)[INDEX_FIRST_VALUE];

        return CommandResult.redirect(URL + query);
    }

}
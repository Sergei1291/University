package com.epam.university.command;

import com.epam.university.context.RequestContext;
import com.epam.university.service.ServiceException;

public interface Command {

    /**
     * This method is used to performs the actions of the business logic
     * of the application. Param requestContext contains data for actions
     * as parameters and attributes. The results of actions also are recorded
     * as attributes and parameters in the param requestContext. The method
     * also determines the address of the page for displaying the result
     * of actions of the services of the application, or address of the
     * command for executing next action. returned CommandResult object
     * contains this address.
     *
     * @param requestContext Contains parameters and attributes.
     * @return CommandResult
     * @throws ServiceException
     */
    CommandResult execute(RequestContext requestContext) throws ServiceException;

}
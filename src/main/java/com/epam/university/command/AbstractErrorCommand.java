package com.epam.university.command;

import com.epam.university.context.RequestContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractErrorCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    private final static String LOCALIZE_MESSAGE_ATTRIBUTE = "localizeMessage";
    private final static String MESSAGE_ATTRIBUTE = "message";
    private final static String PAGE_ERROR = "WEB-INF/view/error.jsp";

    protected CommandResult forwardErrorPage(RequestContext requestContext,
                                             String errorMessage, boolean isLocalizedMassage) {
        LOGGER.warn(errorMessage);
        if (isLocalizedMassage) {
            requestContext.setRequestAttribute(LOCALIZE_MESSAGE_ATTRIBUTE, errorMessage);
        } else {
            requestContext.setRequestAttribute(MESSAGE_ATTRIBUTE, errorMessage);
        }
        return CommandResult.forward(PAGE_ERROR);
    }

}
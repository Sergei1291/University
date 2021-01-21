package com.epam.university.command;

import com.epam.university.context.RequestContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractErrorCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    private final static String IS_LOCALIZE_MESSAGE_ATTRIBUTE = "isLocalizeMessage";
    private final static String LOCALIZE_MESSAGE_ATTRIBUTE = "localizeMessage";
    private final static String PAGE_ERROR = "WEB-INF/view/error.jsp";

    protected CommandResult forwardErrorPage(RequestContext requestContext, String errorMessage) {
        LOGGER.warn(errorMessage);
        requestContext.setRequestAttribute(IS_LOCALIZE_MESSAGE_ATTRIBUTE, true);
        requestContext.setRequestAttribute(LOCALIZE_MESSAGE_ATTRIBUTE, errorMessage);
        return CommandResult.forward(PAGE_ERROR);
    }

}
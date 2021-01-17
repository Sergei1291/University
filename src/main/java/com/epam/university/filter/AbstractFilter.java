package com.epam.university.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public abstract class AbstractFilter {

    private final static Logger LOGGER = LogManager.getLogger();

    private final static String MESSAGE_ATTRIBUTE = "message";
    private final static String PAGE_ERROR = "WEB-INF/view/error.jsp";

    protected void dispatchToErrorPage(ServletRequest servletRequest, ServletResponse servletResponse,
                                       String errorMessage) throws ServletException, IOException {
        LOGGER.warn(errorMessage);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_ERROR);
        request.setAttribute(MESSAGE_ATTRIBUTE, errorMessage);
        requestDispatcher.forward(servletRequest, servletResponse);
    }

}
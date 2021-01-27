package com.epam.university.controller;

import com.epam.university.command.Command;
import com.epam.university.command.CommandFactory;
import com.epam.university.command.CommandResult;
import com.epam.university.connection.ConnectionPool;
import com.epam.university.context.RequestContext;
import com.epam.university.context.RequestContextHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private final static Logger LOGGER = LogManager.getLogger();

    private final static String COMMAND_PARAMETER = "command";
    private final static String MESSAGE_ATTRIBUTE = "message";
    private final static String PAGE_ERROR = "WEB-INF/view/error.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestContextHelper requestContextHelper = new RequestContextHelper();
            RequestContext requestContext = requestContextHelper.create(request);
            String commandParameter = request.getParameter(COMMAND_PARAMETER);
            Command command = CommandFactory.create(commandParameter);
            CommandResult commandResult = command.execute(requestContext);
            requestContextHelper.updateRequest(request, requestContext);
            dispatch(commandResult, request, response);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            request.setAttribute(MESSAGE_ATTRIBUTE, e.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_ERROR);
            try {
                requestDispatcher.forward(request, response);
            } catch (Exception exceptionForward) {
                LOGGER.error(exceptionForward.getMessage(), exceptionForward);
            }
        }
    }

    private void dispatch(CommandResult commandResult, HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {
        String page = commandResult.getPage();
        if (commandResult.isRedirect()) {
            response.sendRedirect(page);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        requestDispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.shutdown();
    }

}
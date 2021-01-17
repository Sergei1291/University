package com.epam.university.filter;

import com.epam.university.model.identifiable.user.ApplicationStatus;
import com.epam.university.model.identifiable.user.UserDto;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class CommandEnrolleeFilter extends AbstractFilter implements Filter {

    private final static String COMMAND_PARAMETER = "command";
    private final static String USER_DTO_ATTRIBUTE = "userDto";
    private final static String CANCEL_APPLICATION_COMMAND = "cancelApplication";
    private final static String APPLY_COMMAND = "apply";
    private final static String APPLIED_ERROR_MESSAGE = "You have already applied.";
    private final static String NOT_HAVE_APPLICATION_ERROR_MESSAGE = "You don't have application.";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute(USER_DTO_ATTRIBUTE);
        if (userDto != null) {
            Optional<String> optionalErrorMessage = findErrorMessage(request, userDto);
            if (optionalErrorMessage.isPresent()) {
                String errorMessage = optionalErrorMessage.get();
                dispatchToErrorPage(servletRequest, servletResponse, errorMessage);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private Optional<String> findErrorMessage(HttpServletRequest request, UserDto userDto) {
        String command = request.getParameter(COMMAND_PARAMETER);
        ApplicationStatus status = userDto.getApplicationStatus();
        if (status == null && CANCEL_APPLICATION_COMMAND.equals(command)) {
            return Optional.of(NOT_HAVE_APPLICATION_ERROR_MESSAGE);
        }
        if (status != null && APPLY_COMMAND.equals(command)) {
            return Optional.of(APPLIED_ERROR_MESSAGE);
        }
        return Optional.empty();
    }

    @Override
    public void destroy() {
    }

}
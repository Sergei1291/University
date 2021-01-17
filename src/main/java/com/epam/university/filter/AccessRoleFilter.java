package com.epam.university.filter;

import com.epam.university.model.identifiable.user.Role;
import com.epam.university.model.identifiable.user.UserDto;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AccessRoleFilter extends AbstractFilter implements Filter {

    private final static String COMMAND_PARAMETER = "command";
    private final static String USER_DTO_ATTRIBUTE = "userDto";
    private final static String ERROR_MESSAGE = "You don't have rights for target page.";
    private final static List<String> GUEST_COMMANDS = Arrays.asList("local",
            "authorization",
            "detailsFaculty",
            "enteredApplicants",
            "entranceCompany",
            "login",
            "main",
            "selectionFaculty",
            "statisticApplicants");
    private final static List<String> ENROLLEE_COMMANDS = Arrays.asList("local",
            "account",
            "applicationForm",
            "apply",
            "cancelApplication",
            "enteredApplicants",
            "entranceCompany",
            "logout",
            "personalApplication",
            "selectionFaculty",
            "statisticApplicants");
    private final static List<String> COMMITTEE_COMMANDS = Arrays.asList("local",
            "account",
            "applicationsInfo",
            "closeRegistration",
            "detailsFaculty",
            "enteredApplicants",
            "entranceCompany",
            "logout",
            "registrationSuccess",
            "selectionFaculty",
            "statisticApplicants");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute(USER_DTO_ATTRIBUTE);
        String command = request.getParameter(COMMAND_PARAMETER);
        boolean accessFlag = findAccessFlag(userDto, command);
        if (!accessFlag) {
            dispatchToErrorPage(servletRequest, servletResponse, ERROR_MESSAGE);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean findAccessFlag(UserDto userDto, String command) {
        if (userDto != null) {
            Role role = userDto.getRole();
            switch (role) {
                case ENROLLEE:
                    return ENROLLEE_COMMANDS.contains(command);
                case COMMITTEE:
                    return COMMITTEE_COMMANDS.contains(command);
                default:
                    throw new IllegalArgumentException("unknown role " + role);
            }
        } else {
            return GUEST_COMMANDS.contains(command);
        }
    }

    @Override
    public void destroy() {
    }

}
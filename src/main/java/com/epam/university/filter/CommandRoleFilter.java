package com.epam.university.filter;

import com.epam.university.model.user.Role;
import com.epam.university.model.user.UserDto;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CommandRoleFilter implements Filter {

    private final static String COMMAND_PARAMETER = "command";
    private final static String USER_DTO_ATTRIBUTE = "userDto";
    private final static String MESSAGE_ATTRIBUTE = "message";
    private final static String MESSAGE_ATTRIBUTE_VALUE = "you don't have rights for target page!!!";

    private final static String PAGE_ERROR = "WEB-INF/view/error.jsp";

    private final static String ENROLLEE_COMMAND_APPLY = "apply";

    private final static List<String> GUEST_COMMANDS = Arrays.asList("local",
            "main",
            "recruitment",
            "authorization",
            "login",
            "selectFaculty",
            "enrolledApplicants");
    private final static List<String> ENROLLEE_COMMANDS = Arrays.asList("local",
            "main",
            "recruitment",
            "account",
            "logout",
            "selectFaculty",
            "application",
            "apply",
            "cancelApplication");
    private final static List<String> COMMITTEE_COMMANDS = Arrays.asList("local",
            "main",
            "recruitment",
            "account",
            "logout",
            "register");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        boolean accessFlag = false;

        String command = request.getParameter(COMMAND_PARAMETER);
        UserDto userDto = (UserDto) session.getAttribute(USER_DTO_ATTRIBUTE);

        if (userDto != null) {
            Role role = userDto.getRole();

            switch (role) {
                case ENROLLEE:
                    accessFlag = ENROLLEE_COMMANDS.contains(command);
                    accessFlag = accessFlag && isAccessEnrolleApply(userDto, command);
                    break;
                case COMMITTEE:
                    accessFlag = COMMITTEE_COMMANDS.contains(command);
                    break;
            }

        } else {
            accessFlag = GUEST_COMMANDS.contains(command);
        }

        if (!accessFlag) {

            dispatchToErrorPage(servletRequest, servletResponse);

            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    private boolean isAccessEnrolleApply(UserDto userDto, String command) {

        Integer idFaculty = userDto.getFaculty();

        if (idFaculty == null || idFaculty == 0) {

            return true;
        }

        return !ENROLLEE_COMMAND_APPLY.equals(command);
    }

    private void dispatchToErrorPage(ServletRequest servletRequest,
                                     ServletResponse servletResponse) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_ERROR);
        request.setAttribute(MESSAGE_ATTRIBUTE, MESSAGE_ATTRIBUTE_VALUE);
        requestDispatcher.forward(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }

}
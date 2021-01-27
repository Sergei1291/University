package com.epam.university.filter;

import com.epam.university.model.identifiable.UserDto;
import com.epam.university.model.identifiable.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AccessRoleFilter implements Filter {

    private final static Logger LOGGER = LogManager.getLogger();

    private final static String HAVE_NOT_RIGHTS_BUNDLE_ERROR_MESSAGE = "have.not.rights";
    private final static String LOCALIZE_MESSAGE_ATTRIBUTE = "localizeMessage";
    private final static String PAGE_ERROR = "WEB-INF/view/error.jsp";

    private final static String COMMAND_PARAMETER = "command";
    private final static String USER_DTO_ATTRIBUTE = "userDto";

    private final static List<String> GUEST_COMMANDS = Arrays.asList("local",
            "authorization",
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
            "enteredApplicants",
            "entranceCompany",
            "formLists",
            "logout",
            "selectionFaculty",
            "statisticApplicants",
            "successFormLists",
            "successRegistration");

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
            dispatchToErrorPage(servletRequest, servletResponse);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean findAccessFlag(UserDto userDto, String command) {
        if (userDto != null) {
            UserRole role = userDto.getRole();
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

    private void dispatchToErrorPage(ServletRequest servletRequest, ServletResponse servletResponse)
            throws ServletException, IOException {
        LOGGER.warn(HAVE_NOT_RIGHTS_BUNDLE_ERROR_MESSAGE);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_ERROR);
        request.setAttribute(LOCALIZE_MESSAGE_ATTRIBUTE, HAVE_NOT_RIGHTS_BUNDLE_ERROR_MESSAGE);
        requestDispatcher.forward(servletRequest, servletResponse);
    }

}
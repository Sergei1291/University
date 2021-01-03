package com.epam.university.filter;

import com.epam.university.model.user.Role;
import com.epam.university.model.user.UserDto;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RoleFilter implements Filter {

    private final static String PAGE_ERROR = "WEB-INF/view/error.jsp";

    private final static List<String> ENROLLEE_COMMANDS = Arrays.asList("local",
            "main",
            "recruitment",
            "logout",
            "selectionApply",
            "application",
            "apply",
            "cancel");
    private final static List<String> COMMITTEE_COMMANDS = Arrays.asList("local",
            "main",
            "recruitment",
            "logout",
            "register");
    private final static List<String> GUEST_COMMANDS = Arrays.asList("local",
            "main",
            "recruitment",
            "authorization",
            "login",
            "enrolled");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
/*
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        boolean flag = false;
        String command = request.getParameter("command");

        UserDto userDto = (UserDto) session.getAttribute("userDto");

        if (userDto != null) {
            Role role = userDto.getRole();

            switch (role) {
                case ENROLLEE:
                    flag = ENROLLEE_COMMANDS.contains(command);
                    break;
                case COMMITTEE:
                    flag = COMMITTEE_COMMANDS.contains(command);
                    break;
            }

        } else {
            flag = GUEST_COMMANDS.contains(command);
        }

        if (!flag) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_ERROR);
            request.setAttribute("message", "you don't have rights for target page :" + command + ".");
            requestDispatcher.forward(servletRequest, servletResponse);
            return;
        }
*/
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }

}
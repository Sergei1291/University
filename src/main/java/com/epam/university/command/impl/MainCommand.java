package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.Role;
import com.epam.university.model.UserDto;
import com.epam.university.service.ServiceException;

public class MainCommand implements Command {

    private final static String PAGE_MAIN = "WEB-INF/view/main.jsp";
    private final static String PAGE_ACCOUNT = "WEB-INF/view/account.jsp";
    private final static String PAGE_ACCOUNT_COMMITTEE = "WEB-INF/view/accountCommittee.jsp";

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {

        UserDto userDto = (UserDto) requestContext.getSessionAttribute("userDto");

        if (userDto == null) {
            return CommandResult.forward(PAGE_MAIN);
        }

        Role role = userDto.getRole();

        switch (role) {

            case COMMITTEE:
                return CommandResult.forward(PAGE_ACCOUNT_COMMITTEE);
            case ENROLLEE:
                return CommandResult.forward(PAGE_ACCOUNT);
            default:
                throw new IllegalArgumentException("Unknown user role " + role);
        }

    }

}
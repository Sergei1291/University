package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.FacultyDto;
import com.epam.university.model.user.Role;
import com.epam.university.model.user.UserDto;
import com.epam.university.service.FacultyService;
import com.epam.university.service.ServiceException;

public class AccountCommand implements Command {

    private final static String FACULTY_DTO_ATTRIBUTE = "facultyDto";

    private final static String PAGE_ACCOUNT = "WEB-INF/view/account.jsp";
    private final static String PAGE_ACCOUNT_COMMITTEE = "WEB-INF/view/accountCommittee.jsp";

    private final FacultyService facultyService;

    public AccountCommand(FacultyService facultyService) {

        this.facultyService = facultyService;

    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {

        UserDto userDto = (UserDto) requestContext.getSessionAttribute("userDto");

        Role role = userDto.getRole();

        switch (role) {

            case ENROLLEE:
                addAttribute(requestContext, userDto);
                return CommandResult.forward(PAGE_ACCOUNT);
            case COMMITTEE:
                return CommandResult.forward(PAGE_ACCOUNT_COMMITTEE);
            default:
                throw new IllegalArgumentException("Unknown user role " + role.name());
        }

    }

    private void addAttribute(RequestContext requestContext, UserDto userDto) throws ServiceException {

        int idFaculty = userDto.getFaculty();

        if (idFaculty == 0) {

            return;
        }

        FacultyDto facultyDto = facultyService.findFaculty(idFaculty);

        requestContext.setRequestAttribute(FACULTY_DTO_ATTRIBUTE, facultyDto);

    }

}
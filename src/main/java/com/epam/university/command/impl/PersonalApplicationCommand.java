package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.identifiable.Certificate;
import com.epam.university.model.identifiable.user.UserDto;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.PersonalApplicationService;

import java.util.List;

public class PersonalApplicationCommand implements Command {

    private final static String IS_REGISTRATION_FINISHED_ATTRIBUTE = "isRegistrationFinished";
    private final static String USER_DTO_ATTRIBUTE = "userDto";
    private final static String CERTIFICATES_ATTRIBUTE = "certificates";
    private final static String PAGE_PERSONAL_APPLICATION = "WEB-INF/view/personalApplication.jsp";

    private final PersonalApplicationService personalApplicationService;

    public PersonalApplicationCommand(PersonalApplicationService personalApplicationService) {
        this.personalApplicationService = personalApplicationService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        boolean isRegistrationFinished = personalApplicationService.isRegistrationFinished();
        requestContext.setRequestAttribute(IS_REGISTRATION_FINISHED_ATTRIBUTE, isRegistrationFinished);

        UserDto userDto = (UserDto) requestContext.getSessionAttribute(USER_DTO_ATTRIBUTE);
        int userId = userDto.getId();
        List<Certificate> certificates = personalApplicationService.findUserCertificates(userId);
        requestContext.setRequestAttribute(CERTIFICATES_ATTRIBUTE, certificates);

        return CommandResult.forward(PAGE_PERSONAL_APPLICATION);
    }

}
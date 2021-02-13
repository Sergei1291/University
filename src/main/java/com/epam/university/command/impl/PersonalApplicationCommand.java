package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.FullApplication;
import com.epam.university.model.identifiable.UserDto;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.PersonalApplicationService;

import java.util.Optional;

public class PersonalApplicationCommand implements Command {

    private final static String IS_REGISTRATION_FINISHED_ATTRIBUTE = "isRegistrationFinished";
    private final static String IS_APPLICANT_LIST_READY_ATTRIBUTE = "isApplicantListReady";
    private final static String USER_DTO_ATTRIBUTE = "userDto";
    private final static String OPTIONAL_FULL_APPLICATION_ATTRIBUTE = "optionalFullApplication";
    private final static String PAGE_PERSONAL_APPLICATION = "WEB-INF/view/personalApplication.jsp";

    private final PersonalApplicationService personalApplicationService;

    public PersonalApplicationCommand(PersonalApplicationService personalApplicationService) {
        this.personalApplicationService = personalApplicationService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        boolean isRegistrationFinished = personalApplicationService.isRegistrationFinished();
        requestContext.setRequestAttribute(IS_REGISTRATION_FINISHED_ATTRIBUTE, isRegistrationFinished);

        boolean isApplicantListReady = personalApplicationService.isApplicantListReady();
        requestContext.setRequestAttribute(IS_APPLICANT_LIST_READY_ATTRIBUTE, isApplicantListReady);

        UserDto userDto = (UserDto) requestContext.getSessionAttribute(USER_DTO_ATTRIBUTE);
        Optional<FullApplication> optionalFullApplication =
                personalApplicationService.findUserApplication(userDto);
        requestContext.setRequestAttribute(OPTIONAL_FULL_APPLICATION_ATTRIBUTE,
                optionalFullApplication);

        return CommandResult.forward(PAGE_PERSONAL_APPLICATION);
    }

}
package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.UserDto;
import com.epam.university.service.ApplicationService;
import com.epam.university.service.ServiceException;

import java.util.Arrays;
import java.util.List;

public class ApplyCommand implements Command {

    private final static int INDEX_FIRST_VALUE = 0;
    private final static int INDEX_SECOND_VALUE = 1;
    private final static int INDEX_THIRD_VALUE = 2;
    private final static int INDEX_FOURTH_VALUE = 3;

    private final static String USER_DTO_ATTRIBUTE = "userDto";
    private final static String ID_PARAMETER = "id";
    private final static String AVERAGE_MARK_PARAMETER = "averageMark";
    private final static String FIRST_MARK_PARAMETER = "firstMark";
    private final static String SECOND_MARK_PARAMETER = "secondMark";
    private final static String THIRD_MARK_PARAMETER = "thirdMark";

    private final static String COMMAND_MAIN = "/University/controller?command=main";

    private ApplicationService applicationService;

    public ApplyCommand(ApplicationService applicationService) {

        this.applicationService = applicationService;

    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {

        UserDto userDto = (UserDto) requestContext.getSessionAttribute(USER_DTO_ATTRIBUTE);
        int userId = userDto.getId();

        int facultyId = Integer.parseInt(requestContext.getRequestParameter(ID_PARAMETER)[INDEX_FOURTH_VALUE]);

        int idSubjectFirst = Integer.parseInt(requestContext.getRequestParameter(ID_PARAMETER)[INDEX_FIRST_VALUE]);
        int idSubjectSecond = Integer.parseInt(requestContext.getRequestParameter(ID_PARAMETER)[INDEX_SECOND_VALUE]);
        int idSubjectThird = Integer.parseInt(requestContext.getRequestParameter(ID_PARAMETER)[INDEX_THIRD_VALUE]);

        String averageMark = requestContext.getRequestParameter(AVERAGE_MARK_PARAMETER)[INDEX_FIRST_VALUE];

        List<Integer> idSubjects = Arrays.asList(idSubjectFirst, idSubjectSecond, idSubjectThird);

        String firstMark = requestContext.getRequestParameter(FIRST_MARK_PARAMETER)[INDEX_FIRST_VALUE];
        String secondMark = requestContext.getRequestParameter(SECOND_MARK_PARAMETER)[INDEX_FIRST_VALUE];
        String thirdMark = requestContext.getRequestParameter(THIRD_MARK_PARAMETER)[INDEX_FIRST_VALUE];

        List<String> marks = Arrays.asList(firstMark, secondMark, thirdMark);

        UserDto userDtoCorrect = applicationService.apply(userId, facultyId, averageMark, idSubjects, marks);

        requestContext.setSessionAttribute(USER_DTO_ATTRIBUTE, userDtoCorrect);

        return CommandResult.redirect(COMMAND_MAIN);
    }

}
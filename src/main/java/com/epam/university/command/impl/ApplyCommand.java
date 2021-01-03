package com.epam.university.command.impl;

import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.user.UserDto;
import com.epam.university.service.ApplicationService;
import com.epam.university.service.ServiceException;

import java.util.*;

public class ApplyCommand implements Command {

    private final static int INDEX_FIRST_VALUE = 0;

    private final static String USER_DTO_ATTRIBUTE = "userDto";
    private final static String FACULTY_ID_PARAMETER = "facultyId";
    private final static String AVERAGE_MARK_PARAMETER = "averageMark";

    private final static String FIRST_SUBJECT_ID_PARAMETER = "firstSubjectId";
    private final static String SECOND_SUBJECT_ID_PARAMETER = "secondSubjectId";
    private final static String THIRD_SUBJECT_ID_PARAMETER = "thirdSubjectId";

    private final static String FIRST_MARK_PARAMETER = "firstMark";
    private final static String SECOND_MARK_PARAMETER = "secondMark";
    private final static String THIRD_MARK_PARAMETER = "thirdMark";

    private final static String COMMAND_ACCOUNT = "/University/controller?command=account";

    private ApplicationService applicationService;

    public ApplyCommand(ApplicationService applicationService) {

        this.applicationService = applicationService;

    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {

        UserDto userDto = (UserDto) requestContext.getSessionAttribute(USER_DTO_ATTRIBUTE);
        int userId = userDto.getId();

        Integer facultyId = getParameterByName(requestContext, FACULTY_ID_PARAMETER);
        Integer averageMark = getParameterByName(requestContext, AVERAGE_MARK_PARAMETER);

        Integer firstSubjectId = getParameterByName(requestContext, FIRST_SUBJECT_ID_PARAMETER);
        Integer secondSubjectId = getParameterByName(requestContext, SECOND_SUBJECT_ID_PARAMETER);
        Integer thirdSubjectId = getParameterByName(requestContext, THIRD_SUBJECT_ID_PARAMETER);
        List<Integer> idSubjects = Arrays.asList(firstSubjectId, secondSubjectId, thirdSubjectId);

        Integer firstMark = getParameterByName(requestContext, FIRST_MARK_PARAMETER);
        Integer secondMark = getParameterByName(requestContext, SECOND_MARK_PARAMETER);
        Integer thirdMark = getParameterByName(requestContext, THIRD_MARK_PARAMETER);
        List<Integer> marks = Arrays.asList(firstMark, secondMark, thirdMark);

        Map<Integer, Integer> subjectsMarks = createMap(idSubjects, marks);

        UserDto userDtoCorrected = applicationService.apply(userId, facultyId, averageMark, subjectsMarks);

        requestContext.setSessionAttribute(USER_DTO_ATTRIBUTE, userDtoCorrected);

        return CommandResult.redirect(COMMAND_ACCOUNT);
    }

    private Integer getParameterByName(RequestContext requestContext, String parameterName) {

        String parameter = requestContext.getRequestParameter(parameterName)[INDEX_FIRST_VALUE];

        return Integer.parseInt(parameter);
    }

    private Map<Integer, Integer> createMap(List<Integer> keys, List<Integer> values) {

        Iterator<Integer> iteratorKey = keys.iterator();
        Iterator<Integer> iteratorValue = values.iterator();
        Map<Integer, Integer> map = new HashMap<>();

        while (iteratorKey.hasNext() && iteratorValue.hasNext()) {
            map.put(iteratorKey.next(), iteratorValue.next());
        }

        return map;
    }

}
package com.epam.university.command.impl;

import com.epam.university.command.AbstractErrorCommand;
import com.epam.university.command.Command;
import com.epam.university.command.CommandResult;
import com.epam.university.context.RequestContext;
import com.epam.university.model.identifiable.UserDto;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.EnrolleeService;

import java.util.*;

public class ApplyCommand extends AbstractErrorCommand implements Command {

    private final static String REGISTRATION_CLOSED_BUNDLE_ERROR_MESSAGE = "registration.closed";
    private final static String APPLICATION_IS_NOT_APPLIED_BUNDLE_ERROR_MESSAGE = "failed.apply";

    private final static String FIRST_SUBJECT_ID_PARAMETER = "firstSubjectId";
    private final static String SECOND_SUBJECT_ID_PARAMETER = "secondSubjectId";
    private final static String THIRD_SUBJECT_ID_PARAMETER = "thirdSubjectId";

    private final static String FIRST_MARK_PARAMETER = "firstMark";
    private final static String SECOND_MARK_PARAMETER = "secondMark";
    private final static String THIRD_MARK_PARAMETER = "thirdMark";

    private final static int INDEX_FIRST_VALUE = 0;
    private final static String USER_DTO_ATTRIBUTE = "userDto";
    private final static String FACULTY_ID_PARAMETER = "facultyId";
    private final static String AVERAGE_MARK_PARAMETER = "averageMark";
    private final static String COMMAND_PERSONAL_APPLICATION =
            "/University/controller?command=personalApplication";

    private final EnrolleeService enrolleeService;

    public ApplyCommand(EnrolleeService enrolleeService) {
        this.enrolleeService = enrolleeService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        if (enrolleeService.isRegistrationFinished()) {
            return forwardErrorPage(requestContext, REGISTRATION_CLOSED_BUNDLE_ERROR_MESSAGE);
        }
        UserDto userDto = (UserDto) requestContext.getSessionAttribute(USER_DTO_ATTRIBUTE);
        Integer facultyId = getParameterByName(requestContext, FACULTY_ID_PARAMETER);
        Integer averageMark = getParameterByName(requestContext, AVERAGE_MARK_PARAMETER);
        Map<Integer, Integer> subjectsMarks = findSubjectsMarks(requestContext);
        boolean isApplicationApplied = enrolleeService.apply(userDto, facultyId, averageMark, subjectsMarks);
        if (!isApplicationApplied) {
            return forwardErrorPage(requestContext, APPLICATION_IS_NOT_APPLIED_BUNDLE_ERROR_MESSAGE);
        }
        return CommandResult.redirect(COMMAND_PERSONAL_APPLICATION);
    }

    private Integer getParameterByName(RequestContext requestContext, String parameterName) {
        String parameter = requestContext.getRequestParameter(parameterName)[INDEX_FIRST_VALUE];
        return Integer.parseInt(parameter);
    }

    private Map<Integer, Integer> findSubjectsMarks(RequestContext requestContext) {
        List<Integer> idSubjects = findIdSubjects(requestContext);
        List<Integer> marks = findMarks(requestContext);
        Iterator<Integer> iteratorKey = idSubjects.iterator();
        Iterator<Integer> iteratorValue = marks.iterator();
        Map<Integer, Integer> map = new HashMap<>();
        while (iteratorKey.hasNext() && iteratorValue.hasNext()) {
            map.put(iteratorKey.next(), iteratorValue.next());
        }
        return map;
    }

    private List<Integer> findIdSubjects(RequestContext requestContext) {
        Integer firstSubjectId = getParameterByName(requestContext, FIRST_SUBJECT_ID_PARAMETER);
        Integer secondSubjectId = getParameterByName(requestContext, SECOND_SUBJECT_ID_PARAMETER);
        Integer thirdSubjectId = getParameterByName(requestContext, THIRD_SUBJECT_ID_PARAMETER);
        return Arrays.asList(firstSubjectId, secondSubjectId, thirdSubjectId);
    }

    private List<Integer> findMarks(RequestContext requestContext) {
        Integer firstMark = getParameterByName(requestContext, FIRST_MARK_PARAMETER);
        Integer secondMark = getParameterByName(requestContext, SECOND_MARK_PARAMETER);
        Integer thirdMark = getParameterByName(requestContext, THIRD_MARK_PARAMETER);
        return Arrays.asList(firstMark, secondMark, thirdMark);
    }

}
package com.epam.university.command;

import com.epam.university.command.impl.StatisticApplicantsCommand;
import com.epam.university.context.RequestContext;
import com.epam.university.model.Counter;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.StatisticApplicationService;
import com.epam.university.service.impl.StatisticApplicationServiceImpl;
import com.epam.university.validator.NumberValidator;
import com.epam.university.validator.Validator;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class StatisticApplicantsCommandTest {

    private final static String PAGE_ERROR = "WEB-INF/view/error.jsp";
    private final static String PAGE_STATISTIC_APPLICANTS = "WEB-INF/view/statisticApplicants.jsp";

    private final static String FACULTY_ID_PARAMETER = "facultyId";
    private final static String[] FACULTY_ID_VALUE = new String[]{"1"};
    private final static String COUNTERS_ATTRIBUTE = "counters";
    private final static String MESSAGE_ATTRIBUTE = "message";

    @Test
    public void testExecuteShouldForwardErrorPageWhenFacultyIdNotValid() throws ServiceException {
        //given
        StatisticApplicationService statisticApplicationService = Mockito.mock(StatisticApplicationServiceImpl.class);
        Validator<String> validator = Mockito.mock(NumberValidator.class);
        when(validator.isValid(anyString())).thenReturn(false);
        StatisticApplicantsCommand statisticApplicantsCommand =
                new StatisticApplicantsCommand(statisticApplicationService, validator);
        RequestContext actualRequestContext = new RequestContext();
        actualRequestContext.setRequestParameter(FACULTY_ID_PARAMETER, FACULTY_ID_VALUE);
        //when
        CommandResult actual = statisticApplicantsCommand.execute(actualRequestContext);
        //then
        CommandResult expected = CommandResult.forward(PAGE_ERROR);
        Assert.assertEquals(expected, actual);

        RequestContext expectedRequestContext = new RequestContext();
        expectedRequestContext.setRequestParameter(FACULTY_ID_PARAMETER, FACULTY_ID_VALUE);
        expectedRequestContext.setRequestAttribute(MESSAGE_ATTRIBUTE, "PARAMETER IS NOT VALID: 1");
        Assert.assertEquals(expectedRequestContext, actualRequestContext);
    }

    @Test
    public void testExecuteShouldForwardStatisticApplicants() throws ServiceException {
        //given
        StatisticApplicationService statisticApplicationService =
                Mockito.mock(StatisticApplicationServiceImpl.class);
        List<Counter> counters = Arrays.asList(new Counter(1, 200), new Counter(201, 400));
        when(statisticApplicationService.findFacultyStatisticApplications(anyInt())).thenReturn(counters);
        Validator<String> validator = Mockito.mock(NumberValidator.class);
        when(validator.isValid(anyString())).thenReturn(true);
        StatisticApplicantsCommand statisticApplicantsCommand =
                new StatisticApplicantsCommand(statisticApplicationService, validator);
        RequestContext actualRequestContext = new RequestContext();
        actualRequestContext.setRequestParameter(FACULTY_ID_PARAMETER, FACULTY_ID_VALUE);
        //when
        CommandResult actual = statisticApplicantsCommand.execute(actualRequestContext);
        //then
        CommandResult expected = CommandResult.forward(PAGE_STATISTIC_APPLICANTS);
        Assert.assertEquals(expected, actual);

        RequestContext expectedRequestContext = new RequestContext();
        expectedRequestContext.setRequestParameter(FACULTY_ID_PARAMETER, FACULTY_ID_VALUE);
        expectedRequestContext.setRequestAttribute(COUNTERS_ATTRIBUTE, counters);
        Assert.assertEquals(expectedRequestContext, actualRequestContext);
    }

}
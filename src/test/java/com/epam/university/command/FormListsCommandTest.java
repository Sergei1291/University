package com.epam.university.command;

import com.epam.university.command.impl.FormListsCommand;
import com.epam.university.context.RequestContext;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.CommitteeService;
import com.epam.university.service.impl.CommitteeServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class FormListsCommandTest {

    private final static String PAGE_ERROR = "WEB-INF/view/error.jsp";
    private final static String COMMAND_SUCCESS_FORM_LISTS =
            "/University/controller?command=successFormLists";
    private final static String LOCALIZE_MESSAGE_ATTRIBUTE = "localizeMessage";

    @Test
    public void testExecuteShouldForwardErrorPageWhenApplicantListReady() throws ServiceException {
        //given
        CommitteeService committeeService = Mockito.mock(CommitteeServiceImpl.class);
        when(committeeService.isApplicantListReady()).thenReturn(true);
        FormListsCommand formListsCommand =
                new FormListsCommand(committeeService);
        RequestContext actualRequestContext = new RequestContext();
        //when
        CommandResult actual = formListsCommand.execute(actualRequestContext);
        //then
        CommandResult expected = CommandResult.forward(PAGE_ERROR);
        Assert.assertEquals(expected, actual);

        RequestContext expectedRequestContext = new RequestContext();
        expectedRequestContext.setRequestAttribute(LOCALIZE_MESSAGE_ATTRIBUTE, "lists.already.formed");
        Assert.assertEquals(expectedRequestContext, actualRequestContext);
    }

    @Test
    public void testExecuteShouldForwardErrorPageWhenRegistrationIsOpened() throws ServiceException {
        //given
        CommitteeService committeeService = Mockito.mock(CommitteeServiceImpl.class);
        when(committeeService.isApplicantListReady()).thenReturn(false);
        when(committeeService.isRegistrationFinished()).thenReturn(false);
        FormListsCommand formListsCommand =
                new FormListsCommand(committeeService);
        RequestContext actualRequestContext = new RequestContext();
        //when
        CommandResult actual = formListsCommand.execute(actualRequestContext);
        //then
        CommandResult expected = CommandResult.forward(PAGE_ERROR);
        Assert.assertEquals(expected, actual);

        RequestContext expectedRequestContext = new RequestContext();
        expectedRequestContext.setRequestAttribute(LOCALIZE_MESSAGE_ATTRIBUTE, "registration.opened");
        Assert.assertEquals(expectedRequestContext, actualRequestContext);
    }

    @Test
    public void testExecuteShouldRedirectSuccessFormListsWhenFormListsSuccess() throws ServiceException {
        CommitteeService committeeService = Mockito.mock(CommitteeServiceImpl.class);
        when(committeeService.isApplicantListReady()).thenReturn(false);
        when(committeeService.isRegistrationFinished()).thenReturn(true);
        FormListsCommand formListsCommand =
                new FormListsCommand(committeeService);
        RequestContext actualRequestContext = new RequestContext();
        //when
        CommandResult actual = formListsCommand.execute(actualRequestContext);
        //then
        CommandResult expected = CommandResult.redirect(COMMAND_SUCCESS_FORM_LISTS);
        Assert.assertEquals(expected, actual);
        verify(committeeService, times(1)).formListApplicants();
    }

}
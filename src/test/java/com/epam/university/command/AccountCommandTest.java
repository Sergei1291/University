package com.epam.university.command;

import com.epam.university.command.impl.AccountCommand;
import com.epam.university.context.RequestContext;
import com.epam.university.model.identifiable.UserDto;
import com.epam.university.model.identifiable.UserRole;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.RegistrationService;
import com.epam.university.service.impl.RegistrationServiceImpl;
import com.epam.university.validator.UserDtoValidator;
import com.epam.university.validator.Validator;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

public class AccountCommandTest {

    private final static String PAGE_ERROR = "WEB-INF/view/error.jsp";
    private final static String PAGE_ACCOUNT_ENROLLEE = "WEB-INF/view/accountEnrollee.jsp";
    private final static String PAGE_ACCOUNT_COMMITTEE = "WEB-INF/view/accountCommittee.jsp";

    private final static String MESSAGE_ATTRIBUTE = "message";
    private final static String USER_DTO_ATTRIBUTE = "userDto";
    private final static String IS_REGISTRATION_FINISHED_ATTRIBUTE = "isRegistrationFinished";
    private final static String IS_APPLICANT_LIST_READY_ATTRIBUTE = "isApplicantListReady";

    private final static UserDto USER_ENROLLEE =
            new UserDto(0, UserRole.ENROLLEE, "", "");
    private final static UserDto USER_COMMITTEE =
            new UserDto(0, UserRole.COMMITTEE, "", "");

    @Test
    public void testExecuteShouldForwardErrorPageWhenUserNotValid() throws ServiceException {
        //given
        RegistrationService registrationService = Mockito.mock(RegistrationServiceImpl.class);
        Validator<UserDto> validator = Mockito.mock(UserDtoValidator.class);
        when(validator.isValid(anyObject())).thenReturn(false);
        AccountCommand accountCommand = new AccountCommand(registrationService, validator);
        RequestContext actualRequestContext = new RequestContext();
        actualRequestContext.setSessionAttribute(USER_DTO_ATTRIBUTE, USER_ENROLLEE);
        //when
        CommandResult actual = accountCommand.execute(actualRequestContext);
        //then
        CommandResult expected = CommandResult.forward(PAGE_ERROR);
        Assert.assertEquals(expected, actual);

        RequestContext expectedRequestContext = new RequestContext();
        expectedRequestContext.setSessionAttribute(USER_DTO_ATTRIBUTE,
                new UserDto(0, UserRole.ENROLLEE, "", ""));
        expectedRequestContext.setRequestAttribute(MESSAGE_ATTRIBUTE, "PARAMETER IS NOT VALID");
        Assert.assertEquals(expectedRequestContext, actualRequestContext);
    }

    @Test
    public void testExecuteShouldForwardAccountCommitteeWhenUserValidAndCommittee() throws ServiceException {
        //given
        RegistrationService registrationService = Mockito.mock(RegistrationServiceImpl.class);
        when(registrationService.isApplicantListReady()).thenReturn(true);
        when(registrationService.isRegistrationFinished()).thenReturn(true);
        Validator<UserDto> validator = Mockito.mock(UserDtoValidator.class);
        when(validator.isValid(anyObject())).thenReturn(true);
        AccountCommand accountCommand = new AccountCommand(registrationService, validator);
        RequestContext actualRequestContext = new RequestContext();
        actualRequestContext.setSessionAttribute(USER_DTO_ATTRIBUTE, USER_COMMITTEE);
        //when
        CommandResult actual = accountCommand.execute(actualRequestContext);
        //then
        CommandResult expected = CommandResult.forward(PAGE_ACCOUNT_COMMITTEE);
        Assert.assertEquals(expected, actual);

        RequestContext expectedRequestContext = new RequestContext();
        expectedRequestContext.setSessionAttribute(USER_DTO_ATTRIBUTE,
                new UserDto(0, UserRole.COMMITTEE, "", ""));
        expectedRequestContext.setRequestAttribute(IS_REGISTRATION_FINISHED_ATTRIBUTE, true);
        expectedRequestContext.setRequestAttribute(IS_APPLICANT_LIST_READY_ATTRIBUTE, true);
        Assert.assertEquals(expectedRequestContext, actualRequestContext);
    }

    @Test
    public void testExecuteShouldForwardAccountEnrolleeWhenUserValidAndEnrollee() throws ServiceException {
        //given
        RegistrationService registrationService = Mockito.mock(RegistrationServiceImpl.class);
        when(registrationService.isApplicantListReady()).thenReturn(false);
        when(registrationService.isRegistrationFinished()).thenReturn(false);
        Validator<UserDto> validator = Mockito.mock(UserDtoValidator.class);
        when(validator.isValid(anyObject())).thenReturn(true);
        AccountCommand accountCommand = new AccountCommand(registrationService, validator);
        RequestContext actualRequestContext = new RequestContext();
        actualRequestContext.setSessionAttribute(USER_DTO_ATTRIBUTE, USER_ENROLLEE);
        //when
        CommandResult actual = accountCommand.execute(actualRequestContext);
        //then
        CommandResult expected = CommandResult.forward(PAGE_ACCOUNT_ENROLLEE);
        Assert.assertEquals(expected, actual);

        RequestContext expectedRequestContext = new RequestContext();
        expectedRequestContext.setSessionAttribute(USER_DTO_ATTRIBUTE,
                new UserDto(0, UserRole.ENROLLEE, "", ""));
        expectedRequestContext.setRequestAttribute(IS_REGISTRATION_FINISHED_ATTRIBUTE, false);
        expectedRequestContext.setRequestAttribute(IS_APPLICANT_LIST_READY_ATTRIBUTE, false);
        Assert.assertEquals(expectedRequestContext, actualRequestContext);
    }

}
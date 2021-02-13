package com.epam.university.command;

import com.epam.university.command.impl.LocalCommand;
import com.epam.university.context.RequestContext;
import com.epam.university.service.ServiceException;
import org.junit.Assert;
import org.junit.Test;

public class LocalCommandTest {

    private final static String LOCAL_PARAMETER = "local";
    private final static String ADDRESS_PARAMETER = "address";
    private final static String ADDRESS = "addressValue";

    @Test
    public void testExecuteShouldForwardAddressWhenLocalParameter() throws ServiceException {
        //given
        LocalCommand localCommand = new LocalCommand();
        RequestContext actualRequestContext = new RequestContext();
        actualRequestContext.setRequestParameter(LOCAL_PARAMETER, new String[]{"ru"});
        actualRequestContext.setRequestParameter(ADDRESS_PARAMETER, new String[]{ADDRESS});
        //when
        CommandResult actual = localCommand.execute(actualRequestContext);
        //then
        CommandResult expected = CommandResult.redirect(ADDRESS);
        Assert.assertEquals(expected, actual);
    }

}
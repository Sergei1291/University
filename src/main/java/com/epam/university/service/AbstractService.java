package com.epam.university.service;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.helper.DaoHelper;

public abstract class AbstractService {

    protected void rollbackTransaction(DaoHelper daoHelper, Exception exception)
            throws ServiceException {

        try {
            daoHelper.rollBackTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage() + " rollbackTransaction\n"
                    + exception.getMessage(), e);
        }

    }

    protected void finishTransaction(DaoHelper daoHelper) throws ServiceException {

        try {
            daoHelper.finishTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage() + " finishTransaction", e);
        }

    }

}
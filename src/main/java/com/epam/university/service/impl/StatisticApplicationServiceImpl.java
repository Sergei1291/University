package com.epam.university.service.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.dao.persistent.api.ApplicationDao;
import com.epam.university.model.Counter;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.StatisticApplicationService;
import com.epam.university.validator.Validator;

import java.util.ArrayList;
import java.util.List;

public class StatisticApplicationServiceImpl implements StatisticApplicationService {

    private final static int INTERVAL = 10;
    private final static int MAX_AMOUNT = 400;

    private final DaoHelperCreator daoHelperCreator;
    private final Validator<Integer> validator;

    public StatisticApplicationServiceImpl(DaoHelperCreator daoHelperCreator, Validator<Integer> validator) {
        this.daoHelperCreator = daoHelperCreator;
        this.validator = validator;
    }

    @Override
    public List<Counter> findFacultyStatisticApplications(int facultyId)
            throws ServiceException {
        if (!validator.isValid(facultyId)) {
            return new ArrayList<>();
        }
        try (DaoHelper daoHelper = daoHelperCreator.create()) {
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            List<Integer> marksAmounts =
                    applicationDao.findAmountsAverageMarkAndCertificatesByFacultyAndStatusNotCancelled(facultyId);
            return findStatisticByIntervals(marksAmounts);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private List<Counter> findStatisticByIntervals(List<Integer> marksAmounts) {
        int numberIntervals = MAX_AMOUNT / INTERVAL;
        List<Counter> counters = new ArrayList<>();
        for (int i = 0; i < numberIntervals; i++) {
            int minBorder = INTERVAL * i + 1;
            int maxBorder = INTERVAL * (i + 1);
            Counter counter = new Counter(minBorder, maxBorder);
            for (Integer currentAmount : marksAmounts) {
                if ((currentAmount >= minBorder) && (currentAmount <= maxBorder)) {
                    counter.plusValue();
                }
            }
            counters.add(counter);
        }
        return counters;
    }

}
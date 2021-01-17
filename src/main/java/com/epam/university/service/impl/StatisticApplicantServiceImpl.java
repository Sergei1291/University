package com.epam.university.service.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.dao.persistent.api.UserDtoDao;
import com.epam.university.model.Counter;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.StatisticApplicantService;

import java.util.ArrayList;
import java.util.List;

public class StatisticApplicantServiceImpl implements StatisticApplicantService {

    private final static int INTERVAL = 10;
    private final static int MAX_AMOUNT = 400;
    
    private DaoHelperCreator daoHelperCreator;

    public StatisticApplicantServiceImpl(DaoHelperCreator daoHelperCreator) {
        this.daoHelperCreator = daoHelperCreator;
    }

    @Override
    public List<Counter> findFacultyStatisticApplicantsMark(int facultyId)
            throws ServiceException {
        try (DaoHelper daoHelper = daoHelperCreator.create()) {
            UserDtoDao userDtoDao = daoHelper.createUserDtoDao();
            List<Integer> marksAmounts =
                    userDtoDao.findUsersAmountsAverageMarkAndMarksCertificateByFacultyId(facultyId);
            return findStatisticByIntervals(marksAmounts);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private List<Counter> findStatisticByIntervals(List<Integer> marksAmounts) {
        int numberIntervals = MAX_AMOUNT / INTERVAL;
        List<Counter> counters = new ArrayList<>();
        for (int i = 0; i < numberIntervals; i++) {
            Integer minBorder = INTERVAL * i + 1;
            Integer maxBorder = INTERVAL * (i + 1);
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
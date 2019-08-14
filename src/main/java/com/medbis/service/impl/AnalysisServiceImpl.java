package com.medbis.service.impl;

import com.medbis.entity.Visit;
import com.medbis.repository.VisitRepository;
import com.medbis.service.interfaces.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.List;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    private VisitRepository visitRepository;

    @Autowired
    public AnalysisServiceImpl(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public int countVisitsMonthly(int month) {
        LocalDate startDate, endDate;
        startDate = LocalDate.of(2019, month, 1);
        endDate = LocalDate.of(2019, month, Month.of(month).length(isLeapYear(2019)));
        return visitRepository.countVisitsByVisitDateBetween(startDate, endDate);
    }

    @Override
    public List<Visit> findByVisitDateAfterAndVisitDateBefore(int month) {
        LocalDate startDate, endDate;
        startDate = LocalDate.of(2019, month, 1);
        endDate = LocalDate.of(2019, month, Month.of(month).length(isLeapYear(2019)));
        return  visitRepository.findByVisitDateAfterAndVisitDateBefore(startDate, endDate);
    }


    private static boolean isLeapYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
    }



}
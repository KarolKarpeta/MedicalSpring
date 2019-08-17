package com.medbis.service.impl;

import com.medbis.entity.Employee;
import com.medbis.entity.User;
import com.medbis.repository.VisitRepository;
import com.medbis.service.interfaces.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    public int countVisitsByEmployeeIdAndVisitStatus(int id, boolean visitStatus) {
        return visitRepository.countVisitsByEmployeeIdAndVisitStatus(id, visitStatus);
    }

    @Override
    public int countVisitsMonthlyByEmployee(int month, int employeeId) {
        LocalDate startDate, endDate;
        startDate = LocalDate.of(2019, month, 1);
        endDate = LocalDate.of(2019, month, Month.of(month).length(isLeapYear(2019)));
        return visitRepository.countVisitsByVisitDateBetweenAndEmployeeId(startDate, endDate, employeeId);
    }


    private static boolean isLeapYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
    }

       public int getSumOfVisits(Map<Integer, Integer> visitsPlannedByEmployees) {
           int sumOfPlannedVisits = 0;
           for (Integer num : visitsPlannedByEmployees.values())  sumOfPlannedVisits += num;
           return sumOfPlannedVisits;

       }

    public Map<Integer, Integer> createEmployeesResultMap(boolean visitsStatus, List<? extends User> employees) {
        Map<Integer, Integer> resultMap = new LinkedHashMap<>();
        int iterMapKey = 0;

        for (User employee : employees) {
            Employee emp = (Employee) employee;
            resultMap.put(iterMapKey, countVisitsByEmployeeIdAndVisitStatus(emp.getId(), visitsStatus));
            iterMapKey++;
        }
        return resultMap;
    }
}
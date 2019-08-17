package com.medbis.service.interfaces;


import com.medbis.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface AnalysisService {


    int countVisitsMonthly(int month);

    int countVisitsByEmployeeIdAndVisitStatus(int id, boolean visitStatus);

    int countVisitsMonthlyByEmployee(int month, int employeeId);

    int getSumOfVisits(Map<Integer, Integer> visitsPlannedByEmployees);

    Map<Integer, Integer> createEmployeesResultMap(boolean visitsStatus, List<? extends User> employees);
}

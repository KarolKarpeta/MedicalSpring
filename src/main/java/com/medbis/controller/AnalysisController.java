package com.medbis.controller;

import com.medbis.entity.User;
import com.medbis.service.interfaces.AnalysisService;
import com.medbis.service.interfaces.TreatmentService;
import com.medbis.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class AnalysisController {

    private UserService userService;
    private AnalysisService analysisService;
    private TreatmentService treatmentService;


    public AnalysisController(@Qualifier("EmployeeServiceImpl") UserService userService, AnalysisService analysisService, TreatmentService treatmentService) {
        this.userService = userService;
        this.analysisService = analysisService;
        this.treatmentService = treatmentService;
    }

    @GetMapping("/analysis-general")
    public String getGeneralStats(Model model) {
        List<? extends User> employees = userService.findAll();
        Map<Integer, Integer> visitsDoneByEmployees = analysisService.createEmployeesResultMap(true, employees);
        Map<Integer, Integer> visitsPlannedByEmployees = analysisService.createEmployeesResultMap(false, employees);

        model.addAttribute("sumOfPlannedVisits", analysisService.getSumOfVisits(visitsPlannedByEmployees));
        model.addAttribute("sumOfDoneVisits", analysisService.getSumOfVisits(visitsDoneByEmployees));
        model.addAttribute("employeesVisitsDoneMap", visitsDoneByEmployees);
        model.addAttribute("employeesVisitsPlannedMap", visitsPlannedByEmployees);
        model.addAttribute("employees", userService.findAll());
        model.addAttribute("treatments", treatmentService.findAll());
        return "analysis/analysis";
    }

}

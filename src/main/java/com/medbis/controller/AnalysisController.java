package com.medbis.controller;

import com.medbis.entity.Visit;
import com.medbis.service.interfaces.AnalysisService;
import com.medbis.service.interfaces.TreatmentService;
import com.medbis.service.interfaces.UserService;
import com.medbis.service.interfaces.VisitService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AnalysisController {

    private UserService userService;
    private AnalysisService analysisService;
    private TreatmentService treatmentService;
    private VisitService visitService;


    public AnalysisController(@Qualifier("EmployeeServiceImpl") UserService userService, AnalysisService analysisService, TreatmentService treatmentService, VisitService visitService) {
        this.userService = userService;
        this.analysisService = analysisService;
        this.treatmentService = treatmentService;
        this.visitService = visitService;
    }

    @GetMapping("/analysis-general")
    public String getGeneralStats(Model model){
        model.addAttribute("counter", 99);
        model.addAttribute("employees", userService.findAll());
        model.addAttribute("treatments", treatmentService.findAll());
        return "analysis/analysis";
    }

    @GetMapping("/analysis")
    public String getMonthlyAnalysis(Model model, @RequestParam("month") Integer month){
        try {
            List<Visit> visitsByMonth =  analysisService.findByVisitDateAfterAndVisitDateBefore(month);
            int counter = analysisService.countVisitsMonthly(month);
            model.addAttribute("visits", visitsByMonth);
            model.addAttribute("counter", counter);
            model.addAttribute("employees", userService.findAll());
            model.addAttribute("treatments", treatmentService.findAll());
        }
        catch (NumberFormatException err){
            err.printStackTrace();
        }
        return "analysis/analysis";
    }

}

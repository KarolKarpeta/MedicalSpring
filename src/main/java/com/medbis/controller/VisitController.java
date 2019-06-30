package com.medbis.controller;

import com.medbis.entity.Treatment;
import com.medbis.service.interfaces.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VisitController {

    private VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }


    //SHOW ALL VISITS
    @GetMapping ("/visits")
    public String findAll(Model theModel){
        theModel.addAttribute("visitsList", visitService.findAllVisits());
        return "visits/visit-list";
    }


    //Show form for ADD NEW PATIENT
    @GetMapping("/visits/showFormForAddVisit")
    public String showFormForAddVisit(@RequestParam("patientId")int thePatientId, Model theModel){
        //Patient newPatient = (Patient) userFactory.getNewUser("patient");
        return "visits/visit-form";
    }

//    @GetMapping ("/patients/showPatientDetails")
//    public String showPatientDetails(@RequestParam("patientIdDetails")int theId, Model theModel){
//        Patient newPatient = (Patient) userService.findById(theId);
//        System.out.println("Patient details: " + newPatient.toString());
//        theModel.addAttribute("patient", newPatient);
//        return "users/patient-details";
//    }



}

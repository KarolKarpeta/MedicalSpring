package com.medbis.controller;

import com.medbis.entity.Treatment;
import com.medbis.entity.Visit;
import com.medbis.service.interfaces.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

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
        Visit newVisit = new Visit();
        theModel.addAttribute("visit", newVisit);
        theModel.addAttribute("patientId", thePatientId);
        return "visits/visit-form";
    }

    //ADDING NEW VISITS
    @PostMapping("/visits/addNewNewVisit")
    public String addNewMedicine(@Valid @ModelAttribute("visit") Visit theVisit,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "medicine/visit-form";
        }else{
            System.out.println("Visit to save:" + theVisit.toString());
            visitService.save(theVisit);
            return "redirect:/visits";
        }
    }

//    @GetMapping ("/patients/showPatientDetails")
//    public String showPatientDetails(@RequestParam("patientIdDetails")int theId, Model theModel){
//        Patient newPatient = (Patient) userService.findById(theId);
//        System.out.println("Patient details: " + newPatient.toString());
//        theModel.addAttribute("patient", newPatient);
//        return "users/patient-details";
//    }



}

package com.medbis.controller;

import com.medbis.entity.Patient;
import com.medbis.entity.Treatment;
import com.medbis.entity.Visit;
import com.medbis.service.interfaces.UserService;
import com.medbis.service.interfaces.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private UserService userService;

    @Autowired
    public VisitController(VisitService visitService, @Qualifier("PatientServiceImpl") UserService userService) {
        this.visitService = visitService;
        this.userService = userService;
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
        Visit newVisit = new Visit();
        Patient thePatient = (Patient) userService.findById(thePatientId);
        theModel.addAttribute("patientId", thePatientId);
        theModel.addAttribute("visit", newVisit);
        return "visits/visit-form";
    }

    //ADDING NEW VISITS
    @PostMapping("/visits/addNewNewVisit")
    public String addNewMedicine(Model theModel, @Valid @ModelAttribute("visit") Visit theVisit, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            theModel.addAttribute("patientId", theVisit.getVisitPatientId() );
            return "visits/visit-form";
        }else{
            visitService.save(theVisit);
            return "redirect:/visits";
        }
    }

    @GetMapping ("/visits/showFormForEditVisit")
    public String showFormForEditMedicine(@RequestParam("visitIdToEdit")int theId, Model theModel){
        Visit visitToEdit = visitService.findById(theId);
        theModel.addAttribute("visit", visitToEdit);
        theModel.addAttribute("patientId", visitToEdit.getVisitPatientId() );
        return "visits/visit-form";
    }


}

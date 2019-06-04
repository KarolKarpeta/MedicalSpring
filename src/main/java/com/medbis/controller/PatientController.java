package com.medbis.controller;

import com.medbis.entity.Patient;
import com.medbis.service.interfaces.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientController {

    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    @GetMapping("/patients")
    public String findAll(Model theModel){

        System.out.println("Pacjent: " + patientService.findAll());
        for(Patient patient: patientService.findAll()){
            System.out.println("pac: " + patient.toString());
        }
        theModel.addAttribute("patientList", patientService.findAll());
        return "patient/patient-list";
    }


}

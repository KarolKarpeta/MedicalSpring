package com.medbis.controller;

import com.medbis.entity.*;
import com.medbis.factory.UserFactory;
import com.medbis.service.impl.DoctorServiceImpl;
import com.medbis.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class PatientController {

    private MedicineService medicineService;
    private DiseaseService diseaseService;
    private UserService userService;
    private UserFactory userFactory;
    private DoctorServiceImpl doctorService;
    private VisitService visitService;

    @Autowired
    public PatientController(@Qualifier(value = "PatientServiceImpl") UserService userService, UserFactory userFactory, DiseaseService diseaseService, MedicineService medicineService, DoctorServiceImpl doctorService, VisitService visitService) {
        this.userService = userService;
        this.userFactory = userFactory;
        this.diseaseService = diseaseService;
        this.medicineService = medicineService;
        this.doctorService = doctorService;
        this.visitService = visitService;
    }


    @RequestMapping(value = {"/patients", "", "/"})
    public String findAll(Model theModel){
        theModel.addAttribute("patientList", userService.findAll());
        return "users/patient-list";
    }

    //Show form for ADD NEW PATIENT
    @GetMapping("/patients/showFormForAddPatient")
    public String showFormForAddPatient(Model theModel){
//        theModel.addAttribute("diseases", diseaseService.findAll());
        Patient newPatient = (Patient) userFactory.getNewUser("patient");
        System.out.println("pacjent:" + newPatient);
        theModel.addAttribute("patient",newPatient);
        theModel.addAttribute("allMedicines", medicineService.findAll());
        theModel.addAttribute("allDiseases", diseaseService.findAll());
        theModel.addAttribute("allDoctors", doctorService.findAll());
        return "users/patient-form2";
    }

    private static final String AJAX_HEADER_NAME = "X-Requested-With";
    private static final String AJAX_HEADER_VALUE = "XMLHttpRequest";

    /* MEDICINES ***************************************/
    //Add NEW ROW FOR MEDICINE, look params!
    @PostMapping(value="/patients/addNewPatient", params={"addRow"})
    public String addMedicineRow(Model theModel, @ModelAttribute("patient") Patient thePatient, HttpServletRequest request) {
        System.out.println("model: " + theModel);
        theModel.addAttribute("allMedicines", medicineService.findAll());
        theModel.addAttribute("allDiseases", diseaseService.findAll());

        if (AJAX_HEADER_VALUE.equals(request.getHeader(AJAX_HEADER_NAME))) {
            // It is an Ajax request, render only #items fragment of the page.
            thePatient.getPatientMedicines().add(new Medicine());
            return "users/patient-form::#medicineTableF";
        } else {
            // It is a standard HTTP request, render whole page.
            return "users/patient-form";
        }


    }
    //DELETE ONE ROW OF MEDICINE, look params!
    @PostMapping(value="/patients/addNewPatient", params={"removeRow"})
    public String delMedicineRow(Model theModel, @ModelAttribute("patient") Patient thePatient, HttpServletRequest request) {
        theModel.addAttribute("allMedicines", medicineService.findAll());
        theModel.addAttribute("allDiseases", diseaseService.findAll());

        final Integer rowId = Integer.valueOf(request.getParameter("removeRow"));
        System.out.println("usun wiersz: " + rowId);
        thePatient.getPatientMedicines().remove(rowId.intValue());

        if (AJAX_HEADER_VALUE.equals(request.getHeader(AJAX_HEADER_NAME))) {
            // It is an Ajax request, render only #items fragment of the page.
            return "users/patient-form::#medicineTableF";
        } else {
            // It is a standard HTTP request, render whole page.
            return "users/patient-form";
        }
    }



    /* DISEASE ***************************************/
    //Add NEW ROW FOR DISEASE, look params!
    @PostMapping(value="/patients/addNewPatient", params={"addDiseaseRow"})
    public String addDiseaseRow(Model theModel, @ModelAttribute("patient") Patient thePatient) {
        thePatient.getPatientDiseases().add(new Disease()); //.getRows().add(new Row());
        theModel.addAttribute("allMedicines", medicineService.findAll());
        theModel.addAttribute("allDiseases", diseaseService.findAll());
        return "users/patient-form";
    }
    //DELETE ONE ROW OF DISEASE, look params!
    @PostMapping(value="/patients/addNewPatient", params={"removeDiseaseRow"})
    public String delDiseaseRow(Model theModel, @ModelAttribute("patient") Patient thePatient, final HttpServletRequest req) {
        theModel.addAttribute("allMedicines", medicineService.findAll());
        theModel.addAttribute("allDiseases", diseaseService.findAll());
        final Integer rowId = Integer.valueOf(req.getParameter("removeDiseaseRow"));
        thePatient.getPatientDiseases().remove(rowId.intValue());
        return "users/patient-form";
    }







    //ADD NEW PATIENT
    @PostMapping("/patients/addNewPatient")
    public String addNewPatient(RedirectAttributes redirectAttributes, Model theModel, @Valid @ModelAttribute("patient") Patient thePatient, BindingResult bindingResult, @RequestParam(name = "backTo", required = false) String backTo){
        if (bindingResult.hasErrors()){
            theModel.addAttribute("allMedicines", medicineService.findAll());
            theModel.addAttribute("allDiseases", diseaseService.findAll());
            theModel.addAttribute("allDoctors", doctorService.findAll());
            theModel.addAttribute("backTo", backTo);
            return "users/patient-form2";
        }else{
            userService.save(thePatient);
            System.out.println("modek " + theModel);
            if("patientDetails".equals(backTo)){
                redirectAttributes.addAttribute("patientIdDetails", thePatient.getPatientId());
                return "redirect:/patients/showPatientDetails";
            }else{
                return "redirect:/patients";
            }
        }
    }

    //EDITING NEW PATIENT
    @GetMapping ("/patients/showFormForEditPatient")
    public String showFormForEditMedicine(@RequestParam("patientIdToEdit")int theId, @RequestParam(name = "backTo", required = false) String backTo, Model theModel){
        Patient newPatient = (Patient) userService.findById(theId);
        theModel.addAttribute("allMedicines", medicineService.findAll());
        theModel.addAttribute("allDiseases", diseaseService.findAll());
        theModel.addAttribute("allDoctors", doctorService.findAll());
        theModel.addAttribute("patient", newPatient);
        theModel.addAttribute("backTo", backTo);
        return "users/patient-form2";
    }

    //DELETING NEW PATIENT
    @GetMapping("/patients/delete")
    public String delete(@RequestParam("patientIdToDelete")int theId){
        userService.deleteById(theId);
        return "redirect:/patients";
    }

    //Show patient details, all
    @GetMapping ("/patients/showPatientDetails")
    public String showPatientDetails(@RequestParam("patientIdDetails")int theId, Model theModel){
        Patient newPatient = (Patient) userService.findById(theId);
        List<Visit> patientVisitList = visitService.findAllByVisitPatientIdOrderByVisitDateDesc(theId);
        Integer doctorId = newPatient.getDoctorId();
        Doctor doctor = doctorService.findById(doctorId);
        theModel.addAttribute("patient", newPatient);
        theModel.addAttribute("doctor", doctor);
        theModel.addAttribute("patientVisitList", patientVisitList);

        return "users/patient_details";
    }





}

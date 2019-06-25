package com.medbis.controller;

import com.medbis.entity.Disease;
import com.medbis.entity.Medicine;
import com.medbis.entity.Patient;
import com.medbis.factory.UserFactory;
import com.medbis.service.interfaces.DiseaseService;
import com.medbis.service.interfaces.MedicineService;
import com.medbis.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class PatientController {

    private MedicineService medicineService;
    private DiseaseService diseaseService;
    private UserService userService;
    private UserFactory userFactory;

    @Autowired
    public PatientController(@Qualifier(value = "PatientServiceImpl") UserService userService, UserFactory userFactory, DiseaseService diseaseService, MedicineService medicineService) {
        this.userService = userService;
        this.userFactory = userFactory;
        this.diseaseService = diseaseService;
        this.medicineService = medicineService;
    }


    @GetMapping("/patients")
    public String findAll(Model theModel){
        theModel.addAttribute("patientList", userService.findAll());
        return "users/patient-list";
    }

    //Show form for ADD NEW PATIENT
    @GetMapping("/patients/showFormForAddPatient")
    public String showFormForAddPatient(Model theModel){
//        theModel.addAttribute("diseases", diseaseService.findAll());
        Patient newPatient = (Patient) userFactory.getNewUser("patient");
        theModel.addAttribute("patient",newPatient);
        theModel.addAttribute("allMedicines", medicineService.findAll());
        return "users/patient-form";
    }
    //Add NEW ROW FOR MEDICINE, look params!
    @PostMapping(value="/patients/addNewPatient", params={"addRow"})
    public String addRow(Model theModel, @ModelAttribute("patient") Patient thePatient) {
        thePatient.getPatientMedicines().add(new Medicine()); //.getRows().add(new Row());
        theModel.addAttribute("allMedicines", medicineService.findAll());
        return "users/patient-form";
    }

    //DELETE ONE ROW OF MEDICINE, look params!
    @PostMapping(value="/patients/addNewPatient", params={"removeRow"})
    public String delRow(Model theModel, @ModelAttribute("patient") Patient thePatient, final HttpServletRequest req) {
        theModel.addAttribute("allMedicines", medicineService.findAll());
        final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
        System.out.println("Row to delete: " + rowId);
        System.out.println("Size: "+ thePatient.getPatientMedicines().size() );
        for(Medicine med : thePatient.getPatientMedicines()){
            System.out.println(med.toString());
        }

        thePatient.getPatientMedicines().remove(rowId.intValue());
        System.out.println("Size: "+ thePatient.getPatientMedicines().size() );
        for(Medicine med : thePatient.getPatientMedicines()){
            System.out.println(med.toString());
        }

        return "users/patient-form";
    }

//    @RequestMapping(value="/seedstartermng", params={"removeRow"})
//    public String removeRow(final SeedStarter seedStarter, final BindingResult bindingResult,final HttpServletRequest req) {
//        final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
//        seedStarter.getRows().remove(rowId.intValue());
//        return "seedstartermng";
//    }

    //ADD NEW PATIENT
    @PostMapping("/patients/addNewPatient")
    public String addNewPatient(Model theModel, @Valid @ModelAttribute("patient") Patient thePatient, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            theModel.addAttribute("allMedicines", medicineService.findAll());
            return "users/patient-form";
        }else{
            userService.save(thePatient);
            return "redirect:/patients";
        }
    }

    //EDITING NEW PATIENT
    @GetMapping ("/patients/showFormForEditPatient")
    public String showFormForEditMedicine(@RequestParam("patientIdToEdit")int theId, Model theModel){
        Patient newPatient = (Patient) userService.findById(theId);
        theModel.addAttribute("allMedicines", medicineService.findAll());
        theModel.addAttribute("patient", newPatient);
        return "users/patient-form";
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
        System.out.println("Patient details: " + newPatient.toString());
        theModel.addAttribute("patient", newPatient);
        return "users/patient-details";
    }





}

package com.medbis.service.impl;

import com.medbis.entity.Patient;
import com.medbis.repository.PatientRepository;
import com.medbis.service.interfaces.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient findById(int theId) {
        Optional<Patient> result = patientRepository.findById(theId);
        Patient thePatient = null;
        if(result.isPresent()){
            thePatient = result.get();
        }else{
            throw new RuntimeException("Dod not find patient id" + theId);
        }
        return thePatient;
    }

    @Override
    public void save(Patient thePatient) {
        patientRepository.save(thePatient);
    }

    @Override
    public void deleteById(int theId) {
        patientRepository.deleteById(theId);
    }
}

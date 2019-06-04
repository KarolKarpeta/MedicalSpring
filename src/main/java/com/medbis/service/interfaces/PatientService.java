package com.medbis.service.interfaces;

import com.medbis.entity.Patient;

import java.util.List;

public interface PatientService {

    public List<Patient> findAll();

    public Patient findById(int theId);

    public void save(Patient theMedicine);

    public void deleteById(int theId);



}

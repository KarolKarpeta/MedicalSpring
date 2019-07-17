package com.medbis.service.interfaces;

import com.medbis.entity.Category;
import com.medbis.entity.Doctor;

import java.util.List;

public interface DoctorService {

    List<Category> findAll();

    Category findById(int id);

    void save(Doctor doctor);

    void deleteById(int id);

}

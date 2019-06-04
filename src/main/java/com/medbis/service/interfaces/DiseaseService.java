package com.medbis.service.interfaces;

import com.medbis.entity.Disease;

import java.util.List;
import java.util.Optional;

public interface DiseaseService {

    List<Disease> findAll();

    Disease findById(int id);


}

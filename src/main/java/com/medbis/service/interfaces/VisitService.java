package com.medbis.service.interfaces;

import com.medbis.entity.Visit;
import java.util.List;

public interface VisitService {

    public List<Visit> findAll();

    public Visit findById(int theId);

    public void save(Visit theMedicine);

    public void deleteById(int theId);

    public List<Visit> findAllVisits();
}



package com.medbis.repository;

import com.medbis.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository  extends JpaRepository<Employee, Integer> {
    String findByName(String username);



}

package com.medbis.service.impl;

import com.medbis.entity.Employee;
import com.medbis.repository.EmployeeRepository;
import com.medbis.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrinicipalDetailsService implements UserDetailsService {

     private EmployeeRepository employeeRepository;


     public UserPrinicipalDetailsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
     }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Employee employee = this.employeeRepository.findByLogin(name);
        return new UserPrincipal(employee);
    }
}

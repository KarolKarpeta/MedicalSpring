package com.medbis.security;

import com.medbis.controller.HomeController;
import com.medbis.entity.Employee;
import com.medbis.repository.EmployeeRepository;
import com.medbis.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrinicipalDetailsService implements UserDetailsService {

     private EmployeeRepository employeeRepository;
     @Lazy
     private HomeController homeController;

     public UserPrinicipalDetailsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
     }

    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            Employee employee = this.employeeRepository.findByLogin(username);
            return new UserPrincipal(employee);
        }
        catch (UsernameNotFoundException | NullPointerException err){
            //todo:add message to login form about an error;
          homeController.showLoginForm();
        }
        return null;
    }
}

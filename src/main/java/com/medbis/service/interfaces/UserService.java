package com.medbis.service.interfaces;

import com.medbis.entity.Employee;
import com.medbis.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {
    List<? extends User> findAll();

    User findById(int id);

    void save(User user);

    void deleteById(int id);

    boolean checkIfPasswordChangeRequired(Employee employee);

    User findByName(String name);

}


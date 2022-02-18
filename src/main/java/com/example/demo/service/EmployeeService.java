package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.model.Role;

import java.util.List;

public interface EmployeeService {

List<Employee> listAll();

Employee create(String name, String surname, String EMBG, String email, float salary, Role position);

Employee delete(Long id);

Employee findById(Long id);

Employee update(Long id,String name, String surname, String EMBG, String email, float salary, Role position);



}

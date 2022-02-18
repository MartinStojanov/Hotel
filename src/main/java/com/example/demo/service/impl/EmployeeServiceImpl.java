package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.model.Role;
import com.example.demo.repository.EmployeeRepo;
import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo employeeRepo;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public List<Employee> listAll() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee create(String name, String surname, String EMBG, String email, float salary, Role position) {
        Employee employee=new Employee(name,surname,EMBG,email,salary,position);
        return employeeRepo.save(employee);
    }

    @Override
    public Employee delete(Long id) {
        Employee employee=this.employeeRepo.findById(id).orElseThrow(RuntimeException::new);
        this.employeeRepo.delete(employee);
        return employee;
    }

    @Override
    public Employee findById(Long id) {
        return this.employeeRepo.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Employee update(Long id, String name, String surname, String EMBG, String email,
                           float salary, Role position) {
        Employee employee = findById(id);
        employee.setName(name);
        employee.setSurname(surname);
        employee.setEmail(email);
        employee.setEMBG(EMBG);
        employee.setSalary(salary);
        employee.setPosition(position);
        return this.employeeRepo.save(employee);
    }
}

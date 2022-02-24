package com.example.demo.controllers;

import com.example.demo.model.Employee;
import com.example.demo.model.Role;
import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeesController {
    private final EmployeeService employeeService;


    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public String listEmployees(Model model){

        List<Employee> employeeList = this.employeeService.listAll();
        model.addAttribute("employees",employeeList);
        return "listEmployees";
    }



    @PostMapping("/employees/add")
    public String addEmployee(@RequestParam String name,
                              @RequestParam String surname,
                              @RequestParam String EMBG,
                              @RequestParam String email,
                              @RequestParam float salary,
                              @RequestParam Role position){
        this.employeeService.create(name,surname,EMBG,email,salary,position);
        return "editEmployee";//da se smeni Strana so nov vraboten
    }

    @GetMapping("/employee/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        Employee emp = this.employeeService.findById(id);
        List<Role> roles = new ArrayList<>();
        Role a = Role.chef;
        Role b = Role.cleaner;
        Role c = Role.director;
        Role d = Role.reception;
        Role e = Role.hostess;
        Role f = Role.manager;
        Role g = Role.waiter;
        roles.add(a);
        roles.add(b);
        roles.add(c);
        roles.add(d);
        roles.add(e);
        roles.add(g);
        roles.add(f);

        model.addAttribute("emp",emp);
        model.addAttribute("roles",roles);

        return "editEmployee";
    }

    @PostMapping("/employee/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam String surname,
                         @RequestParam String EMBG,
                         @RequestParam String email,
                         @RequestParam float salary,
                         @RequestParam Role position) {
        this.employeeService.update(id, name, surname, EMBG, email,salary,position);
        return "redirect:/employees";
    }

    @PostMapping("/employee/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.employeeService.delete(id);
        return "redirect:/employees"; // da se smeni strana so izbrisan rabotnik
    }

}

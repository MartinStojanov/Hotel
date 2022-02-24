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

import java.util.List;


//Test 4 GIT-HUB
@Controller
public class EmployeesController {
    private final EmployeeService employeeService;


    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/listEmployees")
    public String listEmployees(Model model){

        List<Employee> employeeList = this.employeeService.listAll();
        model.addAttribute("employees",employeeList);
        return "listEmployees";
    }

    //eden Get Mapping so input forma za addEmployee
    //TODO:
    //@GetMapping


    @PostMapping("/addEmployee")
    public String addEmployee(@RequestParam String name,
                              @RequestParam String surname,
                              @RequestParam String EMBG,
                              @RequestParam String email,
                              @RequestParam float salary,
                              @RequestParam Role position){
        this.employeeService.create(name,surname,EMBG,email,salary,position);
        return "redirect:/listEmployees";//da se smeni Strana so nov vraboten
    }

    @PostMapping("/employee/{id}")
    public String updateEmployee(@PathVariable Long id,
                                 @RequestParam String name,
                                 @RequestParam String surname,
                                 @RequestParam String EMBG,
                                 @RequestParam String email,
                                 @RequestParam float salary,
                                 @RequestParam Role position){
        this.employeeService.update(id,name,surname,EMBG,email,salary,position);
        return "redirect:/employees";//da se smeni Strana so updated rabotnik

    }
    @PostMapping("/employee/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.employeeService.delete(id);
        return "redirect:/employees"; // da se smeni strana so izbrisan rabotnik
    }

}

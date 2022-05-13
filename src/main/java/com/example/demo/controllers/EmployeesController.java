package com.example.demo.controllers;

import com.example.demo.model.CsvUtils;
import com.example.demo.model.Employee;
import com.example.demo.model.Role;
import com.example.demo.repository.EmployeeRepo;
import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class EmployeesController {
    private final EmployeeService employeeService;
    private final EmployeeRepo employeeRepo;


    public EmployeesController(EmployeeService employeeService, EmployeeRepo employeeRepo) {
        this.employeeService = employeeService;
        this.employeeRepo = employeeRepo;
    }

    @GetMapping("/employees")
    public String listEmployees(Model model){

        List<Employee> employeeList = this.employeeService.listAll();
        model.addAttribute("employees",employeeList);
        return "listEmployees2";
    }

    @PostMapping("/employee")
    public String addEmployee(@RequestParam String name,
                              @RequestParam String surname,
                              @RequestParam String EMBG,
                              @RequestParam String email,
                              @RequestParam float salary,
                              @RequestParam Role position){
        this.employeeService.create(name,surname,EMBG,email,salary,position);
        return "redirect:/employees";
    }

    @GetMapping("/employee/add")
    public String showAdd(Model model) {
        model.addAttribute("roles", Role.values());
        return "addEmployeeProba";
    }


    @GetMapping("/employee/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        Employee emp = this.employeeService.findById(id);

        model.addAttribute("emp",emp);
        model.addAttribute("roles",Role.values());

        return "addEmployeeProba";
    }

    @PostMapping("/employee/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam String surname,
                         @RequestParam String EMBG,
                         @RequestParam String email,
                         @RequestParam String salary,
                         @RequestParam Role position) {
        float sal = Float.parseFloat(salary);
        this.employeeService.update(id, name, surname, EMBG, email,sal,position);
        return "redirect:/employees";
    }

    @PostMapping("/employee/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.employeeService.delete(id);
        return "redirect:/employees"; // da se smeni strana so izbrisan rabotnik
    }

    @GetMapping("/uploadPage")
    public String getUploadPage(){
        return "uploadPage";
    }

    @PostMapping(value = "/upload", consumes = "text/csv")
    public String uploadSimple(@RequestBody InputStream body) throws IOException {
        employeeRepo.saveAll(CsvUtils.read(Employee.class, body));

        return "redirect:/employees";
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public String uploadMultipart(@RequestParam("file") MultipartFile file) throws IOException {
        employeeRepo.saveAll(CsvUtils.read(Employee.class, file.getInputStream()));
        return "redirect:/employees";
    }



}

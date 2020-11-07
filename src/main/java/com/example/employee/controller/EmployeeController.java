package com.example.employee.controller;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService empService;
    @Autowired
    private EmployeeRepository empRepository;

    @GetMapping("/getEmployees")
    public ResponseEntity<List<Employee>> getEmployees() {
        return new ResponseEntity<>(empService.getAllEmployees(), HttpStatus.OK);
    }

    @PostMapping("/saveEmployees")
    public ResponseEntity<Employee> saveEmployees(@RequestBody Employee Emp) {
        return new ResponseEntity<>(empService.saveEmployee(Emp), HttpStatus.CREATED);
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee emp) throws Exception{
        Employee empDet = empRepository.findById(emp.getId()).orElseThrow(() -> new Exception("Employee not found for this id:: " + emp.getId()));
        empDet.setId(emp.getId());
        empDet.setName(emp.getName());
        empDet.setGender(emp.getGender());
        Employee updEmp = empRepository.save(empDet);
        return new ResponseEntity<>(empDet, HttpStatus.OK);
    }


}

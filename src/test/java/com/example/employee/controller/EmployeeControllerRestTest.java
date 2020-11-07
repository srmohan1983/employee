package com.example.employee.controller;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
class EmployeeControllerRestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService empService;

    @MockBean
    private EmployeeRepository empRepository;


    @Test
    public void getEmployeesRest() throws Exception {

        List<Employee> empList = new ArrayList<>();
        Employee emp = new Employee(1, "Anil", "Male");
        empList.add(emp);

        when(empService.getAllEmployees()).thenReturn(empList);
        RequestBuilder request = MockMvcRequestBuilders.get("/getEmployees").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request).andReturn();
        String expected = "[{id:1,name:Anil,gender:Male}]";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);


    }

    @Test
    public void saveEmployeesRest() throws Exception {


        Employee emp = new Employee(2, "Adhvik", "Male");
        ObjectMapper obj = new ObjectMapper();
        String empJson = obj.writeValueAsString(emp);
        //String saveEmp =

        when(empService.saveEmployee(any(Employee.class))).thenReturn(emp);
        RequestBuilder request = MockMvcRequestBuilders.post("/saveEmployees").contentType(MediaType.APPLICATION_JSON).content(empJson);
        MvcResult result = mockMvc.perform(request).andReturn();
        String expected = "{id:2,name:Adhvik,gender:Male}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);


    }

    @Test
    public void updateEmployeesRest() throws Exception {

        Employee emp = new Employee(2, "Adhvik", "Male");
        ObjectMapper obj = new ObjectMapper();
        String empJson = obj.writeValueAsString(emp);
        //String saveEmp =

        //when(empService.saveEmployee(any(Employee.class))).thenReturn(emp);
        RequestBuilder request = MockMvcRequestBuilders.post("/updateEmployee").contentType(MediaType.APPLICATION_JSON).content(empJson);
        MvcResult result = mockMvc.perform(request).andReturn();
        String expected = "{id:2,name:Adhvik,gender:Male}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);


    }

}
package com.example.usermanagement.controllers;

import com.example.usermanagement.dto.Employee;
import com.example.usermanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static com.example.usermanagement.utility.MediaTypeLiterals.APPLICATION_JSON;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/health", method = GET,
            produces = {APPLICATION_JSON})
    public ResponseEntity health(){
        return new ResponseEntity<>("Success", OK);
    }

    @RequestMapping(value = "/employee", method = POST,
            produces = {APPLICATION_JSON})
    public ResponseEntity create(@RequestBody Employee employee){
        employeeService.createEmployee(employee);
        return new ResponseEntity<>(CREATED);
    }

    @RequestMapping(value = "/employee/{employeeId}", method = GET,
            produces = {APPLICATION_JSON})
    public ResponseEntity create(@PathVariable Long employeeId){
        Employee employee = employeeService.getEmployee(employeeId);
        return new ResponseEntity<>(employee, OK);
    }


    @RequestMapping(value = "/employees", method = GET,
            produces = {APPLICATION_JSON})
    public ResponseEntity findEmployeesByFirstName(@RequestParam String firstName){
        List<Employee> employees = employeeService.findEmployeesByFirstName(firstName);
        return new ResponseEntity<>(employees, OK);
    }
}

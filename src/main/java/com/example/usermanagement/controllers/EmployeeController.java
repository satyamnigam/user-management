package com.example.usermanagement.controllers;

import com.example.usermanagement.dto.Employee;
import com.example.usermanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import static com.example.usermanagement.utility.MediaTypeLiterals.APPLICATION_JSON;
import static com.example.usermanagement.utility.MediaTypeLiterals.APPLICATION_XML;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/health", method = GET,
            produces = {APPLICATION_JSON})
    public ResponseEntity health(){
        return new ResponseEntity<>("Success", OK);
    }

    @RequestMapping(value = "/employees", method = POST,
            produces = {APPLICATION_JSON})
    public ResponseEntity create(@Valid @RequestBody Employee employee){
        employeeService.createEmployee(employee);
        return new ResponseEntity<>(CREATED);
    }

    @RequestMapping(value = "/employees/{employeeId}", method = GET,
            produces = {APPLICATION_JSON, APPLICATION_XML})
    public ResponseEntity<Employee> create(@PathVariable Long employeeId){
        Employee employee = employeeService.getEmployee(employeeId);
        return new ResponseEntity<>(employee, OK);
    }


    @RequestMapping(value = "/employees", method = GET,
            produces = {APPLICATION_JSON, APPLICATION_XML})
    public ResponseEntity<List<Employee>> getEmployees(){
        List<Employee> employees = employeeService.getEmployees();
        return new ResponseEntity<>(employees, OK);
    }

    @RequestMapping(value = "/employees/{employeeId}", method = DELETE,
            produces = {APPLICATION_JSON})
    public ResponseEntity delete(@PathVariable Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(NO_CONTENT);
    }
}

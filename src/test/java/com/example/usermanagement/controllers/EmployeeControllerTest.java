package com.example.usermanagement.controllers;

import com.example.usermanagement.dto.Employee;
import com.example.usermanagement.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    private EmployeeController serviceUnderTest;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("test1");
        employee.setLastName("test2");
        ResponseEntity<?> responseEntity = serviceUnderTest.create(employee);
        verify(employeeService).createEmployee(employee);
        assertEquals(new ResponseEntity<>(HttpStatus.CREATED), responseEntity);
    }
}
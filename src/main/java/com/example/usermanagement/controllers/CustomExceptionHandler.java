package com.example.usermanagement.controllers;

import com.example.usermanagement.EmployeeNotFoundException;
import com.example.usermanagement.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Date;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleAllExceptions(Exception e){
        return new ResponseEntity<>(new ErrorResponse(new Date(), e.getMessage(), "Internal server error."), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = EmployeeNotFoundException.class)
    public ResponseEntity handleEmployeeNotFoundException(EmployeeNotFoundException e){
        return new ResponseEntity<>(new ErrorResponse(new Date(), e.getMessage(), "Employee not found."), NOT_FOUND);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity handleEmployeeNotFoundException(MethodArgumentNotValidException e){
        return new ResponseEntity<>(new ErrorResponse(new Date(), "Validation failed", e.getBindingResult().toString()), BAD_REQUEST);
    }
}

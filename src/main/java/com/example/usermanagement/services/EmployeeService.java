package com.example.usermanagement.services;

import com.example.usermanagement.EmployeeNotFoundException;
import com.example.usermanagement.dataaccess.EmployeeJpaRepository;
import com.example.usermanagement.dataaccess.EmployeeRepository;
import com.example.usermanagement.dto.Employee;
import com.example.usermanagement.entities.EmployeeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    EmployeeJpaRepository employeeJpaRepository;

    @Transactional
    public void createEmployee(Employee employee){
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());

        // employeeRepository.persist(employeeEntity);
        employeeJpaRepository.save(employeeEntity);
    }

    @Transactional(readOnly = true)
    public Employee getEmployee(Long id){
        EmployeeEntity employeeEntity = employeeRepository.getEmployee(id);
        if(employeeEntity == null){
            throw new EmployeeNotFoundException("Employee not found");
        }
        return convertToDto(employeeEntity);
    }

    @Transactional(readOnly = true)
    public List<Employee> findEmployeesByFirstName(String firstName){
        List<EmployeeEntity> employeeEntities = employeeRepository.findEmployeesByFirstName(firstName);
        return employeeEntities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Employee> getEmployees(){
        List<EmployeeEntity> employeeEntities = employeeRepository.findAllEmployees();
        return employeeEntities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private Employee convertToDto(EmployeeEntity employeeEntity) {
        if(employeeEntity != null){
            Employee employee = modelMapper.map(employeeEntity, Employee.class);
            return employee;
        }
        return null;
    }

    @Transactional
    public void deleteEmployee(Long employeeId) {
        employeeRepository.delete(employeeId);
    }
}

package com.example.usermanagement.dataaccess;

import com.example.usermanagement.dto.Employee;
import com.example.usermanagement.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeJpaRepository extends JpaRepository<EmployeeEntity, Long> {

}

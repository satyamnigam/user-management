package com.example.usermanagement.dataaccess;

import com.example.usermanagement.entities.EmployeeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeRepositoryTest {

    EmployeeEntity employeeEntity;
    List<EmployeeEntity> employeeEntities;
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";

    @Mock
    TypedQuery<EmployeeEntity> typedQuery;

    @Mock
    EntityManager em;

    @InjectMocks
    EmployeeRepository serviceUnderTest;

    @BeforeEach
    void setUp() {
        employeeEntities = new ArrayList<EmployeeEntity>();
        employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName(FIRST_NAME);
        employeeEntity.setLastName(LAST_NAME);
        employeeEntities.add(employeeEntity);
    }

    @Test
    void testFindEmployeesByFirstName(){
        when(em.createQuery("select employee from EmployeeEntity as employee " +
                "where employee.firstName like :firstName", EmployeeEntity.class)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(employeeEntities);

        List<EmployeeEntity> employeeList = serviceUnderTest.findEmployeesByFirstName("firstName");
        assertEquals(employeeList, employeeEntities);
    }

    @Test
    void testFindEmployeesByFirstNameWhenNoRecordsFound(){
        when(em.createQuery("select employee from EmployeeEntity as employee " +
                "where employee.firstName like :firstName", EmployeeEntity.class)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(null);

        List<EmployeeEntity> employeeList = serviceUnderTest.findEmployeesByFirstName("firstName");
        assertNull(employeeList);
    }

    @Test
    void testFindEmployeesByFirstNameWhenInputIsNull(){
        assertThrows(IllegalArgumentException.class, () -> serviceUnderTest.findEmployeesByFirstName(""));
    }

    @Test
    void testFindAllEmployees(){
        when(em.createQuery("select employee from EmployeeEntity employee ", EmployeeEntity.class)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(employeeEntities);
        List<EmployeeEntity> employeeList = serviceUnderTest.findAllEmployees();
        assertEquals(employeeList, employeeEntities);
    }
}
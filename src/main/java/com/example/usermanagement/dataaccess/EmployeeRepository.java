package com.example.usermanagement.dataaccess;

import com.example.usermanagement.entities.EmployeeEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class EmployeeRepository extends AbstractRepository {

    public EmployeeEntity getEmployee(Long id){
        return getEntityManager().find(EmployeeEntity.class, id);
    }

    public List<EmployeeEntity> findEmployeesByFirstName(String firstName){
        if (firstName == null || firstName.length() == 0) {
            throw new IllegalArgumentException("The name argument is required");
        }

        EntityManager em = getEntityManager();
        TypedQuery<EmployeeEntity> typedQuery = em.createQuery("select employee from EmployeeEntity as employee " +
                "where employee.firstName like :firstName", EmployeeEntity.class);
        typedQuery.setParameter("firstName", firstName);
        List<EmployeeEntity> employees = typedQuery.getResultList();

        return employees;
    }

    public List<EmployeeEntity> findEmployeesByLastName(String lastName){
        if (lastName == null || lastName.length() == 0) {
            throw new IllegalArgumentException("The name argument is required");
        }

        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("EmployeeEntity.findByLastName");
        query.setParameter("lastName", lastName);
        List<EmployeeEntity> employees = query.getResultList();

        return employees;
    }

    public List<EmployeeEntity> findAllEmployees(){
        EntityManager em = getEntityManager();
        TypedQuery<EmployeeEntity> typedQuery = em.createQuery("select employee from EmployeeEntity employee ", EmployeeEntity.class);
        return typedQuery.getResultList();
    }

    public void delete(Long employeeId){
        EntityManager em = getEntityManager();
        EmployeeEntity employeeEntity = em.find(EmployeeEntity.class, employeeId);
        if(employeeEntity == null){
            throw new EntityNotFoundException("Employee not found");
        }
        employeeEntity.setDeleted(true);
    }
}

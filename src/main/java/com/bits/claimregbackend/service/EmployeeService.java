package com.bits.claimregbackend.service;

import com.bits.claimregbackend.entity.Employee;
import com.bits.claimregbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository repository;

    public List<Employee> getEmployees() {
        return repository.findAll();
    }

    public List<Employee> getApprovers() {
        return repository.findByIsApprover(true);
    }

    public Employee getEmployee(Long id) {
        return new Employee();
    }

}

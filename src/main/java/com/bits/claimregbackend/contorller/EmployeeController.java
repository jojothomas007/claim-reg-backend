package com.bits.claimregbackend.contorller;

import com.bits.claimregbackend.entity.Employee;
import com.bits.claimregbackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees/{id}")
    public Employee fetchEmployeeById(@PathVariable("id") Long employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    @GetMapping("/employees")
    public List<Employee> fetchEmployees(@RequestParam Boolean isApprover) {
        List<Employee> employeeList;
        if (isApprover)
            employeeList = employeeService.getApprovers();
        else
            employeeList = employeeService.getEmployees();
        return employeeList;
    }

}
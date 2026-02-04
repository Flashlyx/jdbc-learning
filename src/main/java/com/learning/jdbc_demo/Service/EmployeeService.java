package com.learning.jdbc_demo.Service;

import com.learning.jdbc_demo.Entity.Employee;
import com.learning.jdbc_demo.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public String insertIntoDB(Employee employee){
        employeeRepository.save(employee);
        return "Employee data inserted !";
    }
}

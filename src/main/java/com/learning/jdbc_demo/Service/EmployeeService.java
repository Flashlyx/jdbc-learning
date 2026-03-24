package com.learning.jdbc_demo.Service;

import com.learning.jdbc_demo.DTO.EmployeeDTO;
import com.learning.jdbc_demo.Entity.Employee;
import com.learning.jdbc_demo.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;


    EmployeeService(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    public Employee insertIntoDB(EmployeeDTO employeeDTO) {

        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());

        return employeeRepository.save(employee);
    }

    public String deleteByIdFromDB(EmployeeDTO employeeDTO) {
        employeeRepository.deleteById(employeeDTO.getId());
        return "Employee Deleted by ID";
    }

    public String updateByIdFromDB(EmployeeDTO employeeDTO) {
        Employee existingEmployee = employeeRepository.findById(employeeDTO.getId())
                .orElseThrow();

        existingEmployee.setName(employeeDTO.getName());
        employeeRepository.save(existingEmployee);

        return "Employee detail has been updated !";
    }

    public Employee fetchEmployee(EmployeeDTO employeeDTO) {

        Employee existingEmployee = employeeRepository.findById(employeeDTO.getId())
                .orElseThrow();
        return existingEmployee;
    }
}

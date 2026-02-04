package com.learning.jdbc_demo.Controller;

import com.learning.jdbc_demo.Entity.Employee;
import com.learning.jdbc_demo.Service.EmployeeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping
    @ResponseBody
    public String insertData(@RequestBody Employee employee){
        return employeeService.insertIntoDB(employee);
    }

}

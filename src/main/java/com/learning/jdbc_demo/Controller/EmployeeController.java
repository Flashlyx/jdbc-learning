package com.learning.jdbc_demo.Controller;

import com.learning.jdbc_demo.DTO.EmployeeDTO;
import com.learning.jdbc_demo.Entity.Employee;
import com.learning.jdbc_demo.Service.EmployeeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping
    @ResponseBody
    public Employee insertData(@RequestBody EmployeeDTO employeeDTO){

        return employeeService.insertIntoDB(employeeDTO);
    }

    @DeleteMapping
    @ResponseBody
    public String deleteByIdFromDB(@RequestBody  EmployeeDTO employeeDTO){

        return employeeService.deleteByIdFromDB(employeeDTO);
    }

    @PutMapping
    @ResponseBody
    public String updateByIdFromDB(@RequestBody EmployeeDTO employeeDTO){

        return employeeService.updateByIdFromDB(employeeDTO);
    }

    @GetMapping
    @ResponseBody
    public Employee fetchEmployee(@RequestBody EmployeeDTO employeeDTO){

        return employeeService.fetchEmployee(employeeDTO);
    }

}

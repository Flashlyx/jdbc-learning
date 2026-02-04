package com.learning.jdbc_demo.Controller;

import com.learning.jdbc_demo.Controller.DTO.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @PostMapping
    @ResponseBody
    public EmployeeDTO insertData(@RequestBody EmployeeDTO employeeDTO){
        return employeeDTO;
    }

}

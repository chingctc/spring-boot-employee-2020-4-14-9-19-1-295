package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    List<Employee> employees = new ArrayList<>();

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployees() {
        employees.add(new Employee(0, "Xiaoming", 20, "Male"));
        employees.add(new Employee(1, "Xiaohong", 19, "Female"));
        employees.add(new Employee(2, "Xiaozhi", 15, "Male"));
        employees.add(new Employee(3, "Xiaogang", 16, "Male"));
        employees.add(new Employee(4, "Xiaoxia", 15, "Female"));
        return employees;
    }

    @GetMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getSpecificEmployee(@PathVariable int employeeId) {
        for (Employee employee : this.employees) {
            if (employee.getId() == employeeId) {
                return employees;
            }
            return null;
        }
        return employees;
    }

    //    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<Employee> getEmployeesByPage(@RequestParam int page, @RequestParam int pageSize) {
//        return employees;
//    }
//
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<Employee> getMaleEmployees(@RequestParam String gender) {
//        return employees;
//    }
//
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        employees.add(employee);
        return employee;
    }

    @PutMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> updateEmployee(@PathVariable int employeeId, Employee employee) {
        for(int i = 0; i < employees.size(); i++) {
            if(employees.get(i).getId().equals(employeeId)){
                this.employees.get(i).setId(employee.getId());
                this.employees.get(i).setName(employee.getName());
                this.employees.get(i).setAge(employee.getAge());
                this.employees.get(i).setGender(employee.getGender());
            }
        }
        return employees;
    }

    @DeleteMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> deleteEmployee(@PathVariable int employeeId) {
        for(int i = 0; i < employees.size(); i++) {
            if(employees.get(i).getId().equals(employeeId)){
                this.employees.remove(i);
            }
        }
        return employees;
    }
}

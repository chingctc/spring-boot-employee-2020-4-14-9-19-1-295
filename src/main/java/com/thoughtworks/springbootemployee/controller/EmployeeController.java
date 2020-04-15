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
        employees.add(new Employee(0, "Xiaoming", 20, "Male", 1000));
        employees.add(new Employee(1, "Xiaohong", 19, "Female", 1000));
        employees.add(new Employee(2, "Xiaozhi", 15, "Male", 2000));
        employees.add(new Employee(3, "Xiaogang", 16, "Male", 2000));
        employees.add(new Employee(4, "Xiaoxia", 15, "Female", 3000));
        return employees;
    }

    @GetMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getSpecificEmployee(@PathVariable int employeeId) {
        return getEmployee(employeeId);
    }

    public Employee getEmployee(int id) {
        for (Employee employee : this.employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    @GetMapping("?gender={gender}")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getMaleEmployees(@PathVariable String gender) {
        return getEmployeeWithGender(gender);
    }

    public List<Employee> getEmployeeWithGender(String gender) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : this.employees) {
            if (employee.getGender().equals(gender)) {
                result.add(employee);
            }
        }
        return result;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        employees.add(employee);
        return employee;
    }

    @PutMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployee(@PathVariable int employeeId, @RequestBody Employee employee) {
        Employee updatedEmployee = updateEmployeeList(employeeId, employee);
        return updatedEmployee;
    }

    public Employee updateEmployeeList(int employeeId, Employee employee) {
        for (Employee updateEmployee : this.employees) {
            if (updateEmployee.getId() == employeeId) {
                updateEmployee.setName(employee.getName());
                updateEmployee.setAge(employee.getAge());
                updateEmployee.setGender(employee.getGender());
                updateEmployee.setSalary(employee.getSalary());
                return updateEmployee;
            }
        }
        return null;
    }


    @DeleteMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> deleteEmployee(@PathVariable int employeeId) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equals(employeeId)) {
                this.employees.remove(i);
            }
        }
        return employees;
    }

    @GetMapping("?page={page}&pageSize={pageSize}")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployeesByPage(@PathVariable int page, @PathVariable int pageSize) {
        return getPage(page, pageSize);
    }

    public List<Employee> getPage(int page, int pageSize) {
        List<Employee> result = new ArrayList<>();
        int maxsize = Math.min((page * pageSize), this.employees.size());
        for (int i = (page - 1) * pageSize; i < maxsize; i++) {
            result.add(this.employees.get(i));
        }
        return result;
    }
}

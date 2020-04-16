package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.thoughtworks.springbootemployee.data.EmployeeData.getTestEmployees;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    List<Employee> employees = new ArrayList<>();

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployees() {
        return getTestEmployees();
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

    @GetMapping(value = "", params = {"gender"})
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getMaleEmployees(@RequestParam String gender) {
        return getTestEmployees().stream().filter(employee -> employee.getGender().equals(gender)).collect(Collectors.toList());
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
            if (employees.get(i).getId() == employeeId) {
                this.employees.remove(i);
            }
        }
        return employees;
    }

//    @GetMapping("?page={page}&pageSize={pageSize}")
//    @GetMapping(value = "", params = {"page", "pageSize"})
//    @ResponseStatus(HttpStatus.OK)
//    public List<Employee> getEmployeesByPage(@RequestParam int page, @RequestParam int pageSize) {
//        return getPage(page, pageSize);
//    }

//    public List<Employee> getPage(int page, int pageSize) {
//        List<Employee> result = new ArrayList<>();
//        int maxsize = Math.min((page * pageSize), getTestEmployees.size());
//        for (int i = (page - 1) * pageSize; i < maxsize; i++) {
//            result.add(this.employees.get(i));
//        }
//        return result;
//    }
}

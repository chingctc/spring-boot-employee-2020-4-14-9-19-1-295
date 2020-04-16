package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    private List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        employees.add(new Employee(0, "Xiaoming", 20, "Male", 1000));
        employees.add(new Employee(1, "Xiaohong", 19, "Female", 1000));
        employees.add(new Employee(2, "Xiaozhi", 15, "Male", 2000));
        employees.add(new Employee(3, "Xiaogang", 16, "Male", 2000));
        employees.add(new Employee(4, "Xiaoxia", 15, "Female", 3000));
    }

    public List<Employee> findAll() {
        return employees;
    }

    public Employee findById(int employeeId) {
        return employees.stream()
                .filter(employee -> employee.getId() == (employeeId))
                .findFirst()
                .orElse(null);
    }

    public Employee save(Employee employee) {
        employees.add(employee);
        return employee;
    }

    public List<Employee> findByGender(String gender) {
        return employees.stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    public List<Employee> remove(int employeeId) {
        return employees.stream()
                .filter(employee -> employee.getId() != employeeId)
                .collect(Collectors.toList());
    }
}

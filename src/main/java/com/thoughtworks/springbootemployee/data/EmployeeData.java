package com.thoughtworks.springbootemployee.data;

import com.thoughtworks.springbootemployee.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeData {
    public static List<Employee> getTestEmployees() {
        List<Employee> employees=new ArrayList<>();
        employees.add(new Employee(0, "Xiaoming", 20, "Male", 1000));
        employees.add(new Employee(1, "Xiaohong", 19, "Female", 1000));
        employees.add(new Employee(2, "Xiaozhi", 15, "Male", 2000));
        employees.add(new Employee(3, "Xiaogang", 16, "Male", 2000));
        employees.add(new Employee(4, "Xiaoxia", 15, "Female", 3000));

        return employees;
    }
}

package com.thoughtworks.springbootemployee.data;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompanyData {
    public static List<Company> getTestCompany() {
        List<Employee> employees=new ArrayList<>();

        employees.add(new Employee(0, "Xiaoming", 20, "Male", 1000));
        employees.add(new Employee(1, "Xiaohong", 19, "Female", 1000));

        List<Company> companies=new ArrayList<>();
        companies.add(new Company("alibaba", 200, Collections.singletonList(new Employee(1, "Xiaoming", 20, "Male", 1000)), 1));
        companies.add(new Company("oocl", 400, Collections.singletonList(new Employee(2, "Mavis", 18, "Female", 2000)), 2));
        companies.add(new Company("google", 500, Collections.singletonList(new Employee(3, "Xiaoxia", 22, "Male", 2000)), 3));
        companies.add(new Company("yahoo", 300, Collections.singletonList(new Employee(4, "Xiaohong", 25, "Male", 2000)), 4));
        companies.add(new Company("firefox", 300, Collections.singletonList(new Employee(5, "Xiaozhi", 30, "Female", 2000)), 5));
        companies.add(new Company("opera", 200, Collections.singletonList(new Employee(6, "Xiaogang", 20, "Male", 2000)), 6));

        return companies;
    }
}

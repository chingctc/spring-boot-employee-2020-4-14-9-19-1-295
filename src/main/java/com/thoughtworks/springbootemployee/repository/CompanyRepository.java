package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CompanyRepository {
    private List<Company> companies = new ArrayList<>();

    public CompanyRepository() {
        companies.add(new Company("alibaba", 200, Collections.singletonList(new Employee(1, "Xiaoming", 20, "Male", 1000)), 1));
        companies.add(new Company("oocl", 400, Collections.singletonList(new Employee(2, "Mavis", 18, "Female", 2000)), 2));
        companies.add(new Company("google", 500, Collections.singletonList(new Employee(3, "Xiaoxia", 22, "Male", 2000)), 3));
        companies.add(new Company("yahoo", 300, Collections.singletonList(new Employee(4, "Xiaohong", 25, "Male", 2000)), 4));
        companies.add(new Company("firefox", 300, Collections.singletonList(new Employee(5, "Xiaozhi", 30, "Female", 2000)), 5));
        companies.add(new Company("opera", 200, Collections.singletonList(new Employee(6, "Xiaogang", 20, "Male", 2000)), 6));
    }

    public List<Company> findAll() {
        return companies;
    }

    public Company findById(int companyId) {
        return companies.stream()
                .filter(company -> company.getId() == (companyId))
                .findFirst()
                .orElse(null);
    }

    public Company save(Company company) {
        companies.add(company);
        return company;
    }

    public List<Company> remove(int companyId) {
        return companies.stream()
                .filter(company -> company.getId() != companyId)
                .collect(Collectors.toList());
    }

    public List<Employee> findCompanyEmployees(int companyId) {
        return companies.stream()
                .filter(company -> company.getId() == companyId)
                .findFirst()
                .map(Company::getEmployees)
                .orElse(null);
    }


}

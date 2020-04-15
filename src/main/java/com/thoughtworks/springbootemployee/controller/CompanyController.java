package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    List<Company> companies = new ArrayList<>();

    @GetMapping
    public List<Company> getCompanies(){
        companies.add(new Company("alibaba",200, Collections.singletonList(new Employee(1, "Xiaoming", 20, "Male", 1000)), 1));
        companies.add(new Company("oocl",400, Collections.singletonList(new Employee(2, "Mavis", 18, "Female", 2000)), 2));
        return companies;
    }

    @GetMapping("/{id}")
    public Object getCompany(@PathVariable int id) {
        for (Company company : this.companies) {
            if (company.getId() == id) {
                return company;
            }
        }
        return null;
    }

    public List<Employee> getCompanyEmployeesList(int id) {
        for (Company company : this.companies) {
            if (company.getId() == id) {
                return company.getEmployees();
            }
        }
        return null;
    }

    @PostMapping
    public Company addCompany(@RequestBody Company company) {
        this.companies.add(company);
        return company;
    }

    @DeleteMapping("/{id}")
    public Company deleteCompany(@PathVariable int id) {
        for (Company company : this.companies) {
            if (company.getId() == id) {
                this.companies.remove(company);
                return company;
            }
        }
        return null;
    }

    @PutMapping("/{id}")
    public Company put(@PathVariable int id, @RequestBody Company company) {
        for (Company updateCompany : this.companies) {
            if (updateCompany.getId() == id) {
                updateCompany.setCompanyName(company.getCompanyName());
                updateCompany.setEmployeesNumber(company.getEmployeesNumber());
                updateCompany.setEmployees(company.getEmployees());
                return updateCompany;
            }
        }
        return null;
    }

}

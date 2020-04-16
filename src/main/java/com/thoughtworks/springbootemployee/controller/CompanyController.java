package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    List<Company> companies = new ArrayList<>();

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Company> getCompanies() {
        companies.add(new Company("alibaba", 200, Collections.singletonList(new Employee(1, "Xiaoming", 20, "Male", 1000)), 1));
        companies.add(new Company("oocl", 400, Collections.singletonList(new Employee(2, "Mavis", 18, "Female", 2000)), 2));
        companies.add(new Company("google", 500, Collections.singletonList(new Employee(3, "Xiaoxia", 22, "Male", 2000)), 3));
        companies.add(new Company("yahoo", 300, Collections.singletonList(new Employee(4, "Xiaohong", 25, "Male", 2000)), 4));
        companies.add(new Company("firefox", 300, Collections.singletonList(new Employee(5, "Xiaozhi", 30, "Female", 2000)), 5));
        companies.add(new Company("opera", 200, Collections.singletonList(new Employee(6, "Xiaogang", 20, "Male", 2000)), 6));

        return companies;
    }

    @GetMapping("/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public Object getCompany(@PathVariable int companyId) {
        for (Company company : this.companies) {
            if (company.getId() == companyId) {
                return company;
            }
        }
        return null;
    }

    @GetMapping("/{companyId}/employees")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getCompanyEmployees(@PathVariable int companyId) {
        List<Employee> employees = getCompanyEmployeesList(companyId);
        return employees;
    }

    public List<Employee> getCompanyEmployeesList(int companyId) {
        for (Company company : this.companies) {
            if (company.getId() == companyId) {
                return company.getEmployees();
            }
        }
        return null;
    }

    @GetMapping("?page={page}&pageSize={pageSize}")
    @ResponseStatus(HttpStatus.OK)
    public List<Company> getCompanyListByPage(@PathVariable int page, @PathVariable int pageSize) {
        return getPage(page, pageSize);
    }

    public List<Company> getPage(int page, int pageSize) {
        List<Company> result = new ArrayList<>();
        int maxsize = Math.min((page * pageSize), this.companies.size());
        for (int i = (page - 1) * pageSize; i < maxsize; i++) {
            result.add(this.companies.get(i));
        }
        return result;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company addCompany(@RequestBody Company company) {
        this.companies.add(company);
        return company;
    }

    @DeleteMapping("/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public Company deleteCompany(@PathVariable int companyId) {
        for (Company company : this.companies) {
            if (company.getId() == companyId) {
                this.companies.remove(company);
                return company;
            }
        }
        return null;
    }

    @PutMapping("/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public Company put(@PathVariable int companyId, @RequestBody Company company) {
        for (Company updateCompany : this.companies) {
            if (updateCompany.getId() == companyId) {
                updateCompany.setCompanyName(company.getCompanyName());
                updateCompany.setEmployeesNumber(company.getEmployeesNumber());
                updateCompany.setEmployees(company.getEmployees());
                return updateCompany;
            }
        }
        return null;
    }
}

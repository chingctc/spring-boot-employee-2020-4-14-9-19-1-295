package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.thoughtworks.springbootemployee.data.CompanyData.getTestCompany;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    List<Company> companies = new ArrayList<>();

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Company> getCompanies() {
        return getTestCompany();
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
        System.out.print(page + " " + pageSize);
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

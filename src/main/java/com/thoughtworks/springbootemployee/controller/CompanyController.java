package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    private CompanyRepository companyRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Company> getCompanies() {
        return companyService.getAll();
    }

    @GetMapping("/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public Company getCompany(@PathVariable int companyId) {
        return companyService.getById(companyId);
    }

    @GetMapping("/{companyId}/employees")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getCompanyEmployees(@PathVariable int companyId) {
        return companyService.getCompanyEmployeesById(companyId);
    }

    @GetMapping(value = "", params={"page","pageSize"})
    @ResponseStatus(HttpStatus.OK)
    public List<Company> getCompanyListByPage(@RequestParam int page, @RequestParam int pageSize) {
        return companyService.getCompanyByPage(page, pageSize);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company addCompany(@RequestBody Company company) {
        return companyService.create(company);
    }

    @DeleteMapping("/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Company> deleteCompany(@PathVariable int companyId) {
        return companyService.delete(companyId);
    }

    @PutMapping("/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public Company put(@PathVariable int companyId, @RequestBody Company company) {
        return companyService.update(companyId, company);
    }
}

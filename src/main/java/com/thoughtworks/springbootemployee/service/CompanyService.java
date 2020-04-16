package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company getById(int id) {
        return companyRepository.findById(id);
    }

    public Company create(Company company) {
        return companyRepository.save(company);
    }

    public List<Company> delete(int companyId) {
        return companyRepository.remove(companyId);
    }

    public Company update(int companyId, Company company) {
        Company existingCompany = companyRepository.findById(companyId);
        if(company.getCompanyName() != null){
            existingCompany.setCompanyName(company.getCompanyName());
        }
        if(company.getEmployees() != null){
            existingCompany.setEmployees(company.getEmployees());
        }
        existingCompany.setEmployeesNumber(company.getEmployeesNumber());
        return companyRepository.save(existingCompany);
    }

    public List<Employee> getCompanyEmployeesById(int companyId) {
        return companyRepository.findCompanyEmployees(companyId);
    }

    public List<Company> getCompanyByPage(int page, int pageSize){
        return companyRepository.findAll()
                .stream()
                .skip(page * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
}

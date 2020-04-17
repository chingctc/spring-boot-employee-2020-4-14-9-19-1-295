package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    public List<Company> getAll(Integer page, Integer pageSize) {
        return companyRepository.findAll(PageRequest.of(page, pageSize)).getContent();
    }
    public Company getById(Integer companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

    public Company create(Company company) {
        return companyRepository.save(company);
    }

    public void delete(Integer companyId) {
        companyRepository.deleteById(companyId);
    }

    public Company update(int companyId, Company company) {
        Company existingCompany = companyRepository.findById(companyId).orElse(null);
        if(company.getCompanyName() != null){
            assert existingCompany != null;
            existingCompany.setCompanyName(company.getCompanyName());
        }
        if(company.getEmployees() != null){
            assert existingCompany != null;
            existingCompany.setEmployees(company.getEmployees());
        }
        assert existingCompany != null;
        existingCompany.setEmployeesNumber(company.getEmployeesNumber());
        return companyRepository.save(existingCompany);
    }

    public List<Employee> getCompanyEmployeesById(int companyId) {
        Company existingCompany = companyRepository.findById(companyId).orElse(null);

        if(existingCompany != null){
            return existingCompany.getEmployees();
        }
        return null;
    }

//    public List<Company> getCompanyByPage(int page, int pageSize){
//        return companyRepository.findAll()
//                .stream()
//                .skip(page * pageSize)
//                .limit(pageSize)
//                .collect(Collectors.toList());
//    }
}

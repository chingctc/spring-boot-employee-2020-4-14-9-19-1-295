package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeesById(int employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public List<Employee> getByGender(String gender) {
        return employeeRepository.findByGender(gender);
    }

    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> delete(int employeeId) {
        return employeeRepository.remove(employeeId);
    }
    public Employee update(int employeeId, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(employeeId);
        if(employee.getName() != null){
            existingEmployee.setName(employee.getName());
        }
        if(employee.getGender() != null) {
            existingEmployee.setGender(employee.getGender());
        }
        existingEmployee.setAge(employee.getAge());
        existingEmployee.setSalary(employee.getSalary());
        return employeeRepository.save(existingEmployee);
    }

    public List<Employee> getEmployeesByPage(int page, int pageSize) {
        return employeeRepository.findAll()
                .stream()
                .skip(page * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
}

package com.thoughtworks.springbootemployee.model;

import java.util.List;

public class Company {
    private static int counter = 1;
    private String companyName;
    private int employeesNumber;
    private List<Employee> employees;
    private int id;

    public Company() {
        this.id = counter++;
    }

    public Company(String companyName, int employeesNumber, List<Employee> employees, int id) {
        this.companyName = companyName;
        this.employeesNumber = employeesNumber;
        this.employees = employees;
        this.id = counter++;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(int employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void setCounter(int counter) {
        Company.counter = counter;
    }
}

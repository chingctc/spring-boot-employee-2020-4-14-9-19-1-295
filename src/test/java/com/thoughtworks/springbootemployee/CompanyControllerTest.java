package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.controller.CompanyController;
import com.thoughtworks.springbootemployee.controller.EmployeeController;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import io.restassured.http.ContentType;
import io.restassured.mapper.TypeRef;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

//import org.springframework.cloud.contract.spec.internal.HttpStatus;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyControllerTest {

    @Autowired
    private CompanyController companyController;
    private CompanyService companyService;


    @Before
    public void setUp() throws Exception {
        RestAssuredMockMvc.standaloneSetup(companyController);
    }

    @Test
    public void shouldFindCompanyById() {
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .when()
                .get("/companies/1");

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        Company company = response.getBody().as(Company.class);
        List<Employee> expected = company.getEmployees();
        Assert.assertEquals(1, company.getId());
        Assert.assertEquals("alibaba", company.getCompanyName());
        Assert.assertEquals(200, company.getEmployeesNumber());
    }

    @Test
    public void shouldRemoveCompany() {
        Company company = new Company();
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .body(company)
                .when()
                .delete("/companies/4");

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        List<Company> companies =
                response.getBody().as(new TypeRef<List<Company>>() {
                    @Override
                    public Type getType() {
                        return super.getType();
                    }
                });
        Assert.assertEquals(5, companies.size());
    }

    @Test
    public void shouldDisplayCompanyWithPageSize() {
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .params("page", "1", "pageSize", "3")
                .when()
                .get("/companies");

        List<Employee> jsonResult = response.jsonPath().getList("$");
        Assert.assertEquals(3,jsonResult.size());
    }

    @Test
    public void shouldAddCompany() {
        Company company = new Company("NewCompany", 250, Collections.singletonList(new Employee(7, "NewEmployee", 25, "Male", 2500)), 7);
        MockMvcResponse response = (MockMvcResponse) given().contentType(ContentType.JSON)
                .body(company)
                .when()
                .post("/companies");

        Assert.assertEquals(201, response.getStatusCode());

        String name = response.jsonPath().getString("companyName");
        Assert.assertEquals("NewCompany",name);
    }
}

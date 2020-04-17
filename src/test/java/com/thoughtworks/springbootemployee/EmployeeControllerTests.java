package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.controller.EmployeeController;
import com.thoughtworks.springbootemployee.model.Employee;
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
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTests {

    @Autowired
    private EmployeeController employeeController;
    private EmployeeService employeeService;


    @Before
    public void setUp() throws Exception {
        RestAssuredMockMvc.standaloneSetup(employeeController);
    }

    @Test
    public void shouldFindEmployeeById() {
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .when()
                .get("/employees/1");

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        Employee employee = response.getBody().as(Employee.class);
        Assert.assertEquals(1, employee.getId());
        Assert.assertEquals("Xiaohong", employee.getName());
        Assert.assertEquals(19, employee.getAge());
        Assert.assertEquals("Female", employee.getGender());
        Assert.assertEquals(1000, employee.getSalary());
    }

    @Test
    public void shouldAddEmployee() {
        Employee employee = new Employee(5, "NewName", 20, "Male", 1000);
        MockMvcResponse response = (MockMvcResponse) given().contentType(ContentType.JSON)
                .body(employee)
                .when()
                .post("/employees");

        Assert.assertEquals(201, response.getStatusCode());

        String name = response.jsonPath().getString("name");
        Assert.assertEquals("NewName", name);
    }

    @Test
    public void shouldFindEmployeeByGender() {
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .params("gender", "Male")
                .when()
                .get("/employees");

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        List<Employee> employees = response.getBody().as(new TypeRef<List<Employee>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        Assert.assertEquals(3, employees.size());
        Assert.assertEquals("Xiaoming", employees.get(0).getName());
        Assert.assertEquals("Xiaozhi", employees.get(1).getName());
        Assert.assertEquals("Xiaogang", employees.get(2).getName());
    }

    @Test
    public void shouldDisplayEmployeeWithPageSize() {
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .params("page", "0", "pageSize", "3")
                .when()
                .get("/employees");

        List<Employee> jsonResult = response.jsonPath().getList("$");
        Assert.assertEquals(3, jsonResult.size());
    }

    @Test
    public void shouldUpdateEmployee() {
        Employee employee = new Employee(1, "NewEmployee1", 19, "Female", 1000);
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .body(employee)
                .when()
                .put("/employees/1");

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        String name = response.jsonPath().getString("name");
        Assert.assertEquals("NewEmployee1", name);
    }

    @Test
    public void shouldRemoveEmployee() {
        Employee employee = new Employee();
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .body(employee)
                .when()
                .delete("/employees/4");

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        List<Employee> employees = response.getBody().as(new TypeRef<List<Employee>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        Assert.assertEquals(4, employees.size());
    }
}

package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class EmployeeServiceTest {
    @Test
    public void should_get_employee_by_id() throws Exception {
        EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);
        List<Employee> expected = new ArrayList<>();
        when(repository.findAll()).thenReturn(expected);
    }

    @Test
    public void should_get_employee_by_gender() throws Exception {
        EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);
        List<Employee> expected = new ArrayList<>();
        when(repository.findAllByGender("Male")).thenReturn(expected);
    }

    @Test
    public void should_update_employee() throws Exception {
        EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);
        EmployeeService service = new EmployeeService(repository);
    }
}

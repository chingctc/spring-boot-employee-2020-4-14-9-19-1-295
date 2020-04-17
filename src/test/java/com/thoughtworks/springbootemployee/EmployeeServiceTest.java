package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
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
}

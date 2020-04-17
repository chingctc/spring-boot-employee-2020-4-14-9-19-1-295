package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class CompanyServiceTest {
    @Test
    public void should_get_all_companies() throws Exception {
        CompanyRepository repository = Mockito.mock(CompanyRepository.class);
        List<Company> expected = new ArrayList<>();
        when(repository.findAll()).thenReturn(expected);
    }
}

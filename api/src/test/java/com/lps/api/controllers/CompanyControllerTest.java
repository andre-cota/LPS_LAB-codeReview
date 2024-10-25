package com.lps.api.controllers;

import com.lps.api.models.Company;
import com.lps.api.services.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CompanyControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CompanyService companyService;

    @InjectMocks
    private CompanyController companyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(companyController).build();
    }

    @Test
    void getAllCompanies() throws Exception {
        List<Company> companies = Arrays.asList(new Company(1L, "Company A", null, null), new Company(2L, "Company B", null, null));
        when(companyService.findAll()).thenReturn(companies);

        mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Company A")))
                .andExpect(jsonPath("$[1].name", is("Company B")));
    }

    @Test
    void getCompanyById() throws Exception {
        Company company = new Company(1L, "Company A", null, null);
        when(companyService.findById(anyLong())).thenReturn(company);

        mockMvc.perform(get("/companies/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Company A")));
    }

    @Test
    void getCompanyById_NotFound() throws Exception {
        when(companyService.findById(anyLong())).thenReturn(null);

        mockMvc.perform(get("/companies/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createCompany() throws Exception {
        Company company = new Company(1L, "Company A", null, null);
        when(companyService.save(any(Company.class))).thenReturn(company);

        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Company A\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Company A")));
    }

    @Test
    void updateCompany() throws Exception {
        Company updatedCompany = new Company(1L, "Updated Company", null, null);
        when(companyService.updateCompany(anyLong(), any(Company.class))).thenReturn(updatedCompany);

        mockMvc.perform(put("/companies/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Updated Company\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Updated Company")));
    }

    @Test
    void updateCompany_NotFound() throws Exception {
        when(companyService.updateCompany(anyLong(), any(Company.class))).thenReturn(null);

        mockMvc.perform(put("/companies/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Updated Company\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteCompany() throws Exception {
        doNothing().when(companyService).deleteById(anyLong());

        mockMvc.perform(delete("/companies/1"))
                .andExpect(status().isNoContent());
    }
}
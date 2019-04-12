package com.dsniatecki.yourfleetmanager.controllers;


import com.dsniatecki.yourfleetmanager.dto.company.CompanyBasicDTO;
import com.dsniatecki.yourfleetmanager.dto.company.CompanyListElementDTO;
import com.dsniatecki.yourfleetmanager.dto.department.DepartmentBasicDTO;
import com.dsniatecki.yourfleetmanager.services.company.CompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("CompanyController -  Unit Tests")
class CompanyControllerUnitTest {

    @MockBean
    private CompanyService companyService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test retrieveBasic Success")
    void retriveBasicSuccess() throws Exception {
        CompanyBasicDTO company = new CompanyBasicDTO();
        company.setId(1L);
        company.setName("FunnyCompany");
        company.getAddress().setId(12L);

        doReturn(company).when(companyService).getBasicById(1L);

        mockMvc.perform(get("/v1/companies/{companyId}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect( jsonPath("$.id").value(company.getId()))
                .andExpect( jsonPath("$.name").value(company.getName()))
                .andExpect( jsonPath("$.address.id").value(company.getAddress().getId()));
    }

    @Test
    @DisplayName("Test retrieveBasic Failed - NumberFormatException")
    void retriveBasicFailed() throws Exception{

        mockMvc.perform(get("/v1/companies/{companyId}", "java_the_best"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    @DisplayName("Test retrieveList Success")
    void retriveList() throws Exception{
        CompanyListElementDTO company1 = new CompanyListElementDTO();
        company1.setId(1L);
        company1.setName("FunnyCompany1");
        company1.setCountry("JavaWorld1");
        company1.setCity("JavaLand1");

        CompanyListElementDTO company2 = new CompanyListElementDTO();
        company2.setId(2L);
        company2.setName("FunnyCompany1");
        company2.setCountry("JavaWorld2");
        company2.setCity("JavaLand2");


        doReturn(Arrays.asList(company1, company2)).when(companyService).getAllAsListElements();

        mockMvc.perform(get("/v1/companies/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect( jsonPath("$[0].id").value(company1.getId()))
                .andExpect( jsonPath("$[0].name").value(company1.getName()))
                .andExpect( jsonPath("$[0].country").value(company1.getCountry()))
                .andExpect( jsonPath("$[0].city").value(company1.getCity()))
                .andExpect( jsonPath("$[1].id").value(company2.getId()))
                .andExpect( jsonPath("$[1].name").value(company2.getName()))
                .andExpect( jsonPath("$[1].country").value(company2.getCountry()))
                .andExpect( jsonPath("$[1].city").value(company2.getCity()));
    }

    @Test
    @DisplayName("Test deleteById")
    void deleteByIdTest() throws Exception{

        mockMvc.perform(delete("/v1/companies/{companyId}", 1L))
                .andExpect(status().isOk());

    }
    @Test
    @DisplayName("Test: addNew() - Success")
    void addNew() throws Exception{
        CompanyBasicDTO savedCompany = generateCompanyBasicDTO();
        CompanyBasicDTO postCompany = generateCompanyBasicDTO();

        doReturn(savedCompany).when(companyService).saveBasic(any());

        mockMvc.perform(post("/v1/companies/")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertToJson(postCompany ))
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect( jsonPath("$.id").value(savedCompany.getId()))
                .andExpect( jsonPath("$.name").value(savedCompany .getName()))
                .andExpect( jsonPath("$.address.id").value(savedCompany.getAddress().getId()))
                .andExpect( jsonPath("$.address.country").value( savedCompany .getAddress().getCountry()))
                .andExpect( jsonPath("$.address.city").value(savedCompany.getAddress().getCity()))
                .andExpect( jsonPath("$.contactDetails.id")
                        .value(savedCompany.getContactDetails().getId()))
                .andExpect(jsonPath("$.contactDetails.telephoneNumber")
                        .value(savedCompany .getContactDetails().getTelephoneNumber()))
                .andExpect(jsonPath("$.contactDetails.emailAddress")
                        .value(savedCompany.getContactDetails().getEmailAddress()));
    }



    @Test
    @DisplayName("Test: updatePartialy() - Success")
    void updatePartialy() throws Exception{
        CompanyBasicDTO savedCompany = generateCompanyBasicDTO();
        CompanyBasicDTO postCompany = new CompanyBasicDTO();
        postCompany.setName(savedCompany.getName());

        doReturn(savedCompany).when(companyService).updatePartial(any(), anyLong());

        mockMvc.perform(patch("/v1/companies/{companyId}",  1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertToJson(postCompany))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect( jsonPath("$.id").value(savedCompany.getId()))
                .andExpect( jsonPath("$.name").value(savedCompany .getName()))
                .andExpect( jsonPath("$.address.id").value(savedCompany.getAddress().getId()))
                .andExpect( jsonPath("$.address.country").value( savedCompany .getAddress().getCountry()))
                .andExpect( jsonPath("$.address.city").value(savedCompany.getAddress().getCity()))
                .andExpect( jsonPath("$.contactDetails.id")
                        .value(savedCompany.getContactDetails().getId()))
                .andExpect(jsonPath("$.contactDetails.telephoneNumber")
                        .value(savedCompany .getContactDetails().getTelephoneNumber()))
                .andExpect(jsonPath("$.contactDetails.emailAddress")
                        .value(savedCompany.getContactDetails().getEmailAddress()));
    }

    private String convertToJson(Object object){
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private CompanyBasicDTO generateCompanyBasicDTO(){
        CompanyBasicDTO company = new CompanyBasicDTO();
        company.setId(15L);
        company.setName("FunnyCompany");
        company.getAddress().setId(25L);
        company.getAddress().setCountry("JavaWorld");
        company.getContactDetails().setId(35L);
        company.getContactDetails().setTelephoneNumber("567-344-432");
        return company;
    }

}
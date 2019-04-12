package com.dsniatecki.yourfleetmanager.controllers;


import com.dsniatecki.yourfleetmanager.dto.car.CarBasicDTO;
import com.dsniatecki.yourfleetmanager.dto.department.DepartmentBasicDTO;
import com.dsniatecki.yourfleetmanager.dto.department.DepartmentDTO;
import com.dsniatecki.yourfleetmanager.services.department.DepartmentService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("DepartmentController -  Unit Tests")
class DepartmentControllerUnitTest {

    @MockBean
    private DepartmentService departmentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test: getBasicById() - Success")
    void getBasicById() throws Exception {
        DepartmentBasicDTO department = generateDepartmentBasicDTO();

        doReturn(department).when(departmentService).getBasicById(1L);

        mockMvc.perform(get("/v1/companies/{companyId}/departments/{id}", 1L, 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect( jsonPath("$.name").value(department.getName()))
                .andExpect( jsonPath("$.contactDetails.id")
                        .value(department.getContactDetails().getId()))
                .andExpect(jsonPath("$.contactDetails.telephoneNumber")
                        .value(department.getContactDetails().getTelephoneNumber()))
                .andExpect(jsonPath("$.contactDetails.emailAddress")
                        .value(department.getContactDetails().getEmailAddress()));
    }

    @Test
    @DisplayName("Test: getWithCarsById() - Success")
    void geWithCarsById() throws Exception {
        DepartmentDTO department = generateDepartmentDTO();

        doReturn(department).when(departmentService).getWithCarsById(1L);

        mockMvc.perform(get("/v1/companies/{companyId}/departments/{id}/cars", 1L, 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect( jsonPath("$.id").value(department.getId()))
                .andExpect( jsonPath("$.name").value(department.getName()))
                .andExpect( jsonPath("$.contactDetails.id")
                        .value(department.getContactDetails().getId()))
                .andExpect(jsonPath("$.contactDetails.telephoneNumber")
                        .value(department.getContactDetails().getTelephoneNumber()))
                .andExpect(jsonPath("$.contactDetails.emailAddress")
                        .value(department.getContactDetails().getEmailAddress()))
                .andExpect(jsonPath("$.cars[0].id").value(department.getCars().get(0).getId()))
                .andExpect(jsonPath("$.cars[0].brand").value(department.getCars().get(0).getBrand()))
                .andExpect(jsonPath("$.cars[0].model").value(department.getCars().get(0).getModel()));

    }

    @Test
    @DisplayName("Test: deleteById() - Success")
    void deleteByIdTest() throws Exception{

        mockMvc.perform(delete("/v1/companies/{companyId}/departments/{id}", 1L, 1L))
                .andExpect(status().isOk());

    }


    @Test
    @DisplayName("Test: addNewToCompany() - Success")
    void addNewToCompany() throws Exception{
        DepartmentBasicDTO savedDepartment = generateDepartmentBasicDTO();
        DepartmentBasicDTO postDepartment = new DepartmentBasicDTO();
        postDepartment.setName(savedDepartment.getName());
        postDepartment.getContactDetails().setTelephoneNumber(savedDepartment.getContactDetails().getTelephoneNumber());
        postDepartment.getContactDetails().setEmailAddress(savedDepartment.getContactDetails().getEmailAddress());

        doReturn(savedDepartment).when(departmentService).saveWithCompany(any(), anyLong());

        mockMvc.perform(post("/v1/companies/{companyId}/departments/", 1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertToJson(postDepartment))
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect( jsonPath("$.id").value(savedDepartment.getId()))
                .andExpect( jsonPath("$.name").value(savedDepartment.getName()))
                .andExpect( jsonPath("$.contactDetails.id")
                        .value(savedDepartment.getContactDetails().getId()))
                .andExpect(jsonPath("$.contactDetails.telephoneNumber")
                        .value(savedDepartment.getContactDetails().getTelephoneNumber()))
                .andExpect(jsonPath("$.contactDetails.emailAddress")
                        .value(savedDepartment.getContactDetails().getEmailAddress()));


    }


    @Test
    @DisplayName("Test: updatePartialy() - Success")
    void updatePartialy() throws Exception{
        DepartmentBasicDTO savedDepartment = generateDepartmentBasicDTO();
        DepartmentBasicDTO postDepartment = new DepartmentBasicDTO();
        postDepartment.setName(savedDepartment.getName());

        doReturn(savedDepartment).when(departmentService).updatePartial(any(), anyLong());

        mockMvc.perform(patch("/v1/companies/{companyId}/departments/{id}", 1L, 1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertToJson(postDepartment))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect( jsonPath("$.id").value(savedDepartment.getId()))
               .andExpect( jsonPath("$.name").value(postDepartment.getName()))
               .andExpect( jsonPath("$.contactDetails.id")
                        .value(savedDepartment.getContactDetails().getId()))
                .andExpect(jsonPath("$.contactDetails.telephoneNumber")
                       .value(savedDepartment.getContactDetails().getTelephoneNumber()))
               .andExpect(jsonPath("$.contactDetails.emailAddress")
                        .value(savedDepartment.getContactDetails().getEmailAddress()));




    }

    private String convertToJson(Object object){
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    private DepartmentBasicDTO generateDepartmentBasicDTO(){
        DepartmentBasicDTO department = new DepartmentBasicDTO();
        department.setId(15L);
        department.setName("FunnyDepartment");
        department.getContactDetails().setId(20L);
        department.getContactDetails().setEmailAddress("funnyemail@email.com");
        department.getContactDetails().setTelephoneNumber("563-432-342");
        return department;
    }


    private DepartmentDTO generateDepartmentDTO() {

        DepartmentDTO department = new DepartmentDTO();
        department.setId(15L);
        department.setName("FunnyDepartment");
        department.getContactDetails().setId(20L);
        department.getContactDetails().setEmailAddress("funnyemail@email.com");
        department.getContactDetails().setTelephoneNumber("563-432-342");

        CarBasicDTO car = new CarBasicDTO();
        car.setId(45L);
        car.setBrand("FunnyCar");
        car.setModel("FunnyModel");
        car.setRegistrationNumber("JAVA 12");

        department.getCars().add(car);

        return department;
    }

}

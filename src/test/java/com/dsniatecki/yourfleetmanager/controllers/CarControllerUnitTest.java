package com.dsniatecki.yourfleetmanager.controllers;

import com.dsniatecki.yourfleetmanager.dto.car.CarDTO;
import com.dsniatecki.yourfleetmanager.services.car.CarService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("CarControllerUnitTest - Unit Tests")
class CarControllerUnitTest {

    @MockBean
    private CarService carService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test: getByIdTest() - Success")
    void getByIdTest() throws Exception{
        CarDTO car = generateCarDTO();

        doReturn(car).when(carService).getById(any());

        mockMvc.perform(get("/v1/companies/{companyId}/departments/{depId}/cars/{id}", 1L, 1L,1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect( jsonPath("$.id").value(car.getId()))
                .andExpect( jsonPath("$.model").value(car.getModel()))
                .andExpect( jsonPath("$.productionYear").value(car.getProductionYear()))
                .andExpect( jsonPath("$.vehicleResponder.id").value(car.getVehicleResponder().getId()))
                .andExpect(jsonPath("$.vehicleResponder.firstName")
                        .value(car.getVehicleResponder().getFirstName()))
                .andExpect( jsonPath("$.vehicleResponder.contactDetails.id")
                        .value(car.getVehicleResponder().getContactDetails().getId()));
    }

    @Test
    @DisplayName("Test: deleteById() - Success")
    void deleteByIdTest() throws Exception{
        mockMvc.perform(delete("/v1/companies/{companyId}/departments/{depId}/cars/{id}", 1L, 1L,1L))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test: addNewToDepartment() - Success")
    void addNewToDepartmentTest() throws Exception{

        CarDTO postCarDTO = generateCarDTO();
        CarDTO returnedCarDTO = generateCarDTO();

        doReturn(returnedCarDTO).when(carService).saveWithDepartment(any(), anyLong());

        mockMvc.perform(post("/v1/companies/{companyId}/departments/{depId}/cars/", 1L, 1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertToJson(postCarDTO))
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect( jsonPath("$.id").value(returnedCarDTO .getId()))
                .andExpect( jsonPath("$.brand").value(returnedCarDTO.getBrand()))
                .andExpect( jsonPath("$.model").value(returnedCarDTO.getModel()))
                .andExpect( jsonPath("$.productionYear").value(returnedCarDTO.getProductionYear()))
                .andExpect( jsonPath("$.vehicleResponder.id").value(returnedCarDTO.getVehicleResponder().getId()))
                .andExpect( jsonPath("$.vehicleResponder.firstName")
                        .value(returnedCarDTO.getVehicleResponder().getFirstName()))
                .andExpect( jsonPath("$.vehicleResponder.contactDetails.id")
                        .value(returnedCarDTO.getVehicleResponder().getContactDetails().getId()))
                .andExpect( jsonPath("$.vehicleResponder.contactDetails.telephoneNumber")
                        .value(returnedCarDTO.getVehicleResponder().getContactDetails().getTelephoneNumber()));


    }
    @Test
    @DisplayName("Test: updatePartial() - Success")
    void updatePartial() throws Exception{


        CarDTO postCarDTO = generateCarDTO();
        postCarDTO.setBrand("JAVA");
        postCarDTO.setModel("The Best");
        CarDTO returnedCarDTO = generateCarDTO();

        doReturn(returnedCarDTO).when(carService).updatePartiaL(any(), anyLong());

        mockMvc.perform(patch("/v1/companies/{companyId}/departments/{depId}/cars/{id}", 1L, 1L, 1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertToJson(postCarDTO))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect( jsonPath("$.id").value(returnedCarDTO .getId()))
                .andExpect( jsonPath("$.brand").value(returnedCarDTO.getBrand()))
                .andExpect( jsonPath("$.model").value(returnedCarDTO.getModel()))
                .andExpect( jsonPath("$.productionYear").value(returnedCarDTO.getProductionYear()))
                .andExpect( jsonPath("$.vehicleResponder.id").value(returnedCarDTO.getVehicleResponder().getId()))
                .andExpect( jsonPath("$.vehicleResponder.firstName")
                        .value(returnedCarDTO.getVehicleResponder().getFirstName()))
                .andExpect( jsonPath("$.vehicleResponder.contactDetails.id")
                        .value(returnedCarDTO.getVehicleResponder().getContactDetails().getId()))
                .andExpect( jsonPath("$.vehicleResponder.contactDetails.telephoneNumber")
                        .value(returnedCarDTO.getVehicleResponder().getContactDetails().getTelephoneNumber()));


    }

    private String convertToJson(Object object){
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private CarDTO generateCarDTO(){
        CarDTO car = new CarDTO();
        car.setId(45L);
        car.setBrand("FunnyCar");
        car.setModel("FunnyModel");
        car.setRegistrationNumber("JAVA 12");
        car.getVehicleResponder().setId(20L);
        car.getVehicleResponder().setFirstName("Damian");
        car.getVehicleResponder().getContactDetails().setId(30L);
        car.getVehicleResponder().getContactDetails().setEmailAddress("siemkaTasiemka@gmail.com");
        return car;
    }
}

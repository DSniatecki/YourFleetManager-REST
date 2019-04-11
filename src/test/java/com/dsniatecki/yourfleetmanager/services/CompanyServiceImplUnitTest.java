package com.dsniatecki.yourfleetmanager.services;

import com.dsniatecki.yourfleetmanager.dto.company.CompanyBasicDTO;
import com.dsniatecki.yourfleetmanager.dto.company.CompanyDepartmentsDTO;
import com.dsniatecki.yourfleetmanager.dto.company.CompanyListElementDTO;
import com.dsniatecki.yourfleetmanager.entities.Address;
import com.dsniatecki.yourfleetmanager.entities.Company;
import com.dsniatecki.yourfleetmanager.entities.ContactDetails;
import com.dsniatecki.yourfleetmanager.entities.Department;
import com.dsniatecki.yourfleetmanager.exceptions.ResourceNotFoundException;
import com.dsniatecki.yourfleetmanager.repositories.CompanyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("CompanyServiceImpl - Unit Tests")
class CompanyServiceImplUnitTest {

    @Autowired
    private CompanyService companyService;

    @MockBean
    private CompanyRepository companyRepository;

    @Test
    @DisplayName("Test getBasicById Success")
    void getBasicByIdSuccess() throws Exception{
        Company company = new Company();
        company.setId(1L);
        company.setName("company");
        company.getAddress().setId(12L);
        company.getAddress().setCountry("Poland");
        company.getAddress().setCity("Wrolcaw");
        company.getContactDetails().setId(15L);
        company.getContactDetails().setTelephoneNumber("563 231 321");

        doReturn(Optional.of(company)).when(companyRepository).findById(1L);
        CompanyBasicDTO companyBasicDTO = companyService.getBasicById(1L);

        Assertions.assertNotNull(companyBasicDTO);
        Assertions.assertEquals(companyBasicDTO.getId(), company.getId());
        Assertions.assertEquals(companyBasicDTO.getName(), company.getName());
        Assertions.assertEquals(companyBasicDTO.getAddress().getId(), company.getAddress().getId());
        Assertions.assertEquals(companyBasicDTO.getAddress().getCountry(), company.getAddress().getCountry());
        Assertions.assertEquals(companyBasicDTO.getContactDetails().getId(), company.getContactDetails().getId());
        Assertions.assertEquals(companyBasicDTO.getContactDetails().getTelephoneNumber(),
                company.getContactDetails().getTelephoneNumber());

    }

    @Test()
    @DisplayName("Test getBasicById Failed - ResourceNotFoundException")
    void getBasicByIdFailed() throws Exception{

        doReturn(Optional.empty()).when(companyRepository).findById(2L);
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class,
                () -> companyService.getBasicById(2L) );
        Assertions.assertTrue(thrown.getMessage().contains("Company[id:2] was not found."));


    }


    @Test
    @DisplayName("Test getAllAsListElements")
    void getAllAsListElementsTest() throws Exception{
        Company company1 = new Company();
        company1.setId(1L);
        company1.setName("company");
        company1.getAddress().setCity("Wrolcaw");

        Company company2 = new Company();
        company2.setId(11L);
        company2.setName("company2");
        company2.getAddress().setCity("Wrolcaw2");


        doReturn(Arrays.asList(company1, company2)).when(companyRepository).findAll();

        List<CompanyListElementDTO> returnedList = companyService.getAllAsListElements();

        Assertions.assertNotNull(returnedList.get(0));
        Assertions.assertNotNull(returnedList.get(1));
        Assertions.assertEquals(returnedList.get(0).getId(), company1.getId());
        Assertions.assertEquals(returnedList.get(1).getId(), company2.getId());
        Assertions.assertEquals(returnedList.get(0).getName(), company1.getName());
        Assertions.assertEquals(returnedList.get(0).getCountry(), company1.getAddress().getCountry());
        Assertions.assertEquals(returnedList.get(1).getCountry(), company2.getAddress().getCountry());
        Assertions.assertEquals(returnedList.get(0).getCity(), company1.getAddress().getCity());
        Assertions.assertEquals(returnedList.get(1).getCity(), company2.getAddress().getCity());
    }

    @Test
    @DisplayName("Test getWithDepartments")
    void getWithDepartmentsTest() throws Exception{
        Company company = new Company();
        company.setId(1L);
        company.setName("company");
        company.getAddress().setId(12L);
        company.getAddress().setCountry("Poland");
        company.getAddress().setCity("Wrolcaw");
        company.getContactDetails().setId(15L);
        company.getContactDetails().setTelephoneNumber("563 231 321");
        Department department = new Department();
        department.setId(10L);
        department.setName("FunnyDepartment");
        department.getContactDetails().setId(19L);
        department.getContactDetails().setTelephoneNumber("555 555 555");
        company.addDepartment(department);

        doReturn(Optional.of(company)).when(companyRepository).findById(1L);

        CompanyDepartmentsDTO returnedCompany = companyService.getWithDepartments(1L);

        Assertions.assertNotNull(returnedCompany);
        Assertions.assertEquals(returnedCompany.getId(), company.getId());
        Assertions.assertEquals(returnedCompany.getName(), company.getName());
        Assertions.assertEquals(returnedCompany.getDepartments().get(0).getId(), department.getId());
        Assertions.assertEquals(returnedCompany.getDepartments().get(0).getName(), department.getName());
    }

}
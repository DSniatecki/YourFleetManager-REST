package com.dsniatecki.yourfleetmanager.services.company;

import com.dsniatecki.yourfleetmanager.dto.company.CompanyBasicDTO;
import com.dsniatecki.yourfleetmanager.dto.company.CompanyDepartmentsDTO;
import com.dsniatecki.yourfleetmanager.dto.company.CompanyListElementDTO;
import com.dsniatecki.yourfleetmanager.entities.Company;
import com.dsniatecki.yourfleetmanager.entities.Department;
import com.dsniatecki.yourfleetmanager.exceptions.ResourceNotFoundException;
import com.dsniatecki.yourfleetmanager.repositories.CompanyRepository;
import com.dsniatecki.yourfleetmanager.services.company.CompanyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    @DisplayName("Test: getBasicById - Success")
    void getBasicByIdSuccess() throws Exception{
        Company company = generateCompany();

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
    @DisplayName("Test getBasicById() - Failed - ResourceNotFoundException")
    void getBasicByIdFailed() throws Exception{

        doReturn(Optional.empty()).when(companyRepository).findById(2L);
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class,
                () -> companyService.getBasicById(2L) );
        Assertions.assertTrue(thrown.getMessage().contains("Company[id:2] was not found."));
    }

    @Test
    @DisplayName("Test getAllAsListElements() - Success")
    void getAllAsListElementsTest() throws Exception{
        List<Company> companyList = generateCompanyList();

        doReturn(companyList).when(companyRepository).findAll();

        List<CompanyListElementDTO> returnedList = companyService.getAllAsListElements();

        Assertions.assertNotNull(returnedList.get(0));
        Assertions.assertNotNull(returnedList.get(1));
        Assertions.assertEquals(returnedList.get(0).getId(), companyList.get(0).getId());
        Assertions.assertEquals(returnedList.get(1).getId(), companyList.get(1).getId());
        Assertions.assertEquals(returnedList.get(0).getName(), companyList.get(0).getName());
        Assertions.assertEquals(returnedList.get(0).getCountry(), companyList.get(0).getAddress().getCountry());
        Assertions.assertEquals(returnedList.get(1).getCountry(), companyList.get(1).getAddress().getCountry());
        Assertions.assertEquals(returnedList.get(0).getCity(), companyList.get(0).getAddress().getCity());
        Assertions.assertEquals(returnedList.get(1).getCity(), companyList.get(1).getAddress().getCity());
    }

    @Test
    @DisplayName("Test getWithDepartments() - Success")
    void getWithDepartmentsTest() throws Exception{
        Company company = generateCompany();

        doReturn(Optional.of(company)).when(companyRepository).findById(1L);

        CompanyDepartmentsDTO returnedCompany = companyService.getWithDepartments(1L);

        Assertions.assertNotNull(returnedCompany);
        Assertions.assertEquals(returnedCompany.getId(), company.getId());
        Assertions.assertEquals(returnedCompany.getName(), company.getName());
        Assertions.assertEquals(returnedCompany.getDepartments().get(0).getId(),
                company.getDepartments().get(0).getId());
        Assertions.assertEquals(returnedCompany.getDepartments().get(0).getName(),
                company.getDepartments().get(0).getName());
    }

    @Test
    @DisplayName("Test getPageOfListElements")
    void getPageOfListElementsTest() throws Exception {
        Page<Company> companyPage = new PageImpl<>(generateCompanyList());
        doReturn(companyPage).when(companyRepository).findAll(Pageable.unpaged());
        Page<CompanyListElementDTO> returnedPage = companyService.getPageOfListElements(Pageable.unpaged());

        Assertions.assertNotNull(returnedPage.getContent().get(0));
        Assertions.assertNotNull(returnedPage.getContent().get(1));

        Assertions.assertEquals(returnedPage.getTotalElements(), companyPage.getTotalElements());
        Assertions.assertEquals(returnedPage.getContent().get(0).getId(),  companyPage.getContent().get(0).getId());
        Assertions.assertEquals(returnedPage.getContent().get(1).getId(),  companyPage.getContent().get(1).getId());
        Assertions.assertEquals(returnedPage.getContent().get(0).getCountry(),  companyPage.getContent().get(0).getAddress().getCountry());
        Assertions.assertEquals(returnedPage.getContent().get(1).getName(),  companyPage.getContent().get(1).getName());
    }


    private Company generateCompany(){

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

        return company;
    }

    private List<Company> generateCompanyList(){

        Company company1 = new Company();
        company1.setId(1L);
        company1.setName("company");
        company1.getAddress().setCity("Wrolcaw");

        Company company2 = new Company();
        company2.setId(11L);
        company2.setName("company2");
        company2.getAddress().setCity("Wrolcaw2");

        return Arrays.asList(company1,company2);
    }


}
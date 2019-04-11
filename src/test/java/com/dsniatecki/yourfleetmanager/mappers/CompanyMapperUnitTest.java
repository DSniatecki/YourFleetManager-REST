package com.dsniatecki.yourfleetmanager.mappers;

import com.dsniatecki.yourfleetmanager.dto.company.CompanyBasicDTO;
import com.dsniatecki.yourfleetmanager.dto.company.CompanyListElementDTO;
import com.dsniatecki.yourfleetmanager.entities.Company;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("CompanyMapper - Unit Tests")
class CompanyMapperUnitTest {

    @Test
    @DisplayName("mapping Company to CompanyBasicDTO")
    void CompanyToCompanyBasicDTOMapperTest() {
        Company company = new Company();
        company.setId(1L);
        company.setName("company");
        company.getAddress().setId(12L);
        company.getAddress().setCountry("Poland");
        company.getAddress().setCity("Wrolcaw");
        company.getContactDetails().setId(15L);
        company.getContactDetails().setTelephoneNumber("563 231 321");

        CompanyBasicDTO companyBasicDTO = CompanyMapper.INSTANCE.companyToCompanyBasicDTO(company);
        Assertions.assertEquals(companyBasicDTO.getId(), company.getId());
        Assertions.assertEquals(companyBasicDTO.getName(), company.getName());
        Assertions.assertEquals(companyBasicDTO.getAddress().getId(), company.getAddress().getId());
        Assertions.assertEquals(companyBasicDTO.getAddress().getCountry(), company.getAddress().getCountry());
        Assertions.assertEquals(companyBasicDTO.getContactDetails().getId(), company.getContactDetails().getId());
    }


    @Test
    @DisplayName("mapping Company to CompanyListElementDTO")
    void CompanyToCompanyListElementDTO() {
        Company company = new Company();
        company.setId(1L);
        company.setName("company");
        company.getAddress().setId(12L);
        company.getAddress().setCountry("Poland");
        company.getAddress().setCity("Wrolcaw");
        company.getContactDetails().setId(15L);
        company.getContactDetails().setTelephoneNumber("563 231 321");

        CompanyListElementDTO companyListElementDTO = CompanyMapper.INSTANCE.companyToCompanyListElementDTO(company);
        Assertions.assertEquals(companyListElementDTO.getId(), company.getId());
        Assertions.assertEquals(companyListElementDTO.getName(), company.getName());
        Assertions.assertEquals(companyListElementDTO.getCountry(), company.getAddress().getCountry());
        Assertions.assertEquals(companyListElementDTO.getCity(), company.getAddress().getCity());
    }

    @Test
    @DisplayName("mapping CompanyBasicDTO to Company")
    void CompanyBasicDTOToCopmanyTest() {
        CompanyBasicDTO companyBasicDTO = new CompanyBasicDTO();
        companyBasicDTO.setId(20L);
        companyBasicDTO.setName("FunnyCompany");
        companyBasicDTO.getAddress().setId(30L);
        companyBasicDTO.getAddress().setCountry("JavaLand");
        companyBasicDTO.getContactDetails().setId(40L);
        companyBasicDTO.getContactDetails().setTelephoneNumber("132 321 232");

        Company company = CompanyMapper.INSTANCE.companyBasicDTOToCompany(companyBasicDTO);

        Assertions.assertEquals(companyBasicDTO.getId(), company.getId());
        Assertions.assertEquals(companyBasicDTO.getName(), company.getName());
        Assertions.assertEquals(companyBasicDTO.getAddress().getId(), company.getAddress().getId());
        Assertions.assertEquals(companyBasicDTO.getAddress().getCountry(), company.getAddress().getCountry());
        Assertions.assertEquals(companyBasicDTO.getContactDetails().getId(), company.getContactDetails().getId());
        Assertions.assertEquals(companyBasicDTO.getContactDetails().getEmailAddress(), company.getContactDetails().getEmailAddress());
    }
}
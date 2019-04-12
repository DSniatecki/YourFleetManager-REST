package com.dsniatecki.yourfleetmanager.mappers.company;

import com.dsniatecki.yourfleetmanager.dto.company.CompanyBasicDTO;
import com.dsniatecki.yourfleetmanager.entities.Company;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("CompanyPartialMapper - Unit Tests")
public class CompanyPartialMapperUnitTest {

    @Test
    @DisplayName("test: updateCompanyWithCompanyBasicDTO() - ")
    void updateCompanyWithCompanyBasicDTOTest() {
        CompanyBasicDTO companyBasicDTO = new CompanyBasicDTO();
        companyBasicDTO.setId(20L);
        companyBasicDTO.setName("FunnyCompany");
        companyBasicDTO.getAddress().setCountry("JavaLand");

        Company company = new Company();
        company.setId(1L);
        company.setName("company");
        company.getAddress().setId(12L);
        company.getAddress().setCountry("Poland");
        company.getAddress().setCity("Wroclaw");
        company.getContactDetails().setId(15L);
        company.getContactDetails().setTelephoneNumber("563 231 321");

        CompanyPartialMapper.companyBasicDTOToCopmany(companyBasicDTO, company);

        Assertions.assertSame(company.getId(), companyBasicDTO.getId());
        Assertions.assertEquals(company.getName(), companyBasicDTO.getName());
        Assertions.assertEquals(company.getAddress().getCountry(), companyBasicDTO.getAddress().getCountry());
        Assertions.assertEquals(company.getAddress().getCity(), "Wroclaw");
        Assertions.assertSame(company.getContactDetails().getId(), 15L);
        Assertions.assertEquals(company.getContactDetails().getTelephoneNumber(), "563 231 321");
    }
}

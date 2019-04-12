package com.dsniatecki.yourfleetmanager.mappers.company;

import com.dsniatecki.yourfleetmanager.dto.company.CompanyBasicDTO;
import com.dsniatecki.yourfleetmanager.dto.company.CompanyDepartmentsDTO;
import com.dsniatecki.yourfleetmanager.dto.company.CompanyListElementDTO;
import com.dsniatecki.yourfleetmanager.entities.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompanyMapper {
     CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

     CompanyBasicDTO companyToCompanyBasicDTO(Company company);
     Company companyBasicDTOToCompany(CompanyBasicDTO companyBasicDTO);

     @Mappings({
             @Mapping(target="country", source="company.address.country"),
             @Mapping(target="city", source = "company.address.city")})
     CompanyListElementDTO companyToCompanyListElementDTO(Company company);
     CompanyDepartmentsDTO companyToCompanyDepartmentsDTO(Company company);
}

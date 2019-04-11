package com.dsniatecki.yourfleetmanager.services;

import com.dsniatecki.yourfleetmanager.dto.company.CompanyBasicDTO;
import com.dsniatecki.yourfleetmanager.dto.company.CompanyDepartmentsDTO;
import com.dsniatecki.yourfleetmanager.dto.company.CompanyListElementDTO;

import java.util.List;

public interface CompanyService {
    CompanyBasicDTO getBasicById(Long id);
    List<CompanyListElementDTO> getAllAsListElements();
    void deleteById(Long valueOf);
    CompanyBasicDTO saveBasic(CompanyBasicDTO companyBasicDTO);
    CompanyBasicDTO updatePartial(CompanyBasicDTO companyBasicDTO, Long id);
    CompanyDepartmentsDTO getWithDepartments(Long id);
}

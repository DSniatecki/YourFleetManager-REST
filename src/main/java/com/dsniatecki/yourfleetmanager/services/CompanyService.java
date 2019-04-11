package com.dsniatecki.yourfleetmanager.services;

import com.dsniatecki.yourfleetmanager.dto.company.CompanyBasicDTO;
import com.dsniatecki.yourfleetmanager.dto.company.CompanyDepartmentsDTO;
import com.dsniatecki.yourfleetmanager.dto.company.CompanyListElementDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyService {
    CompanyBasicDTO getBasicById(Long id);
    List<CompanyListElementDTO> getAllAsListElements();
    Page<CompanyListElementDTO> getPageOfListElements(Pageable pageable);
    void deleteById(Long valueOf);
    CompanyBasicDTO saveBasic(CompanyBasicDTO companyBasicDTO);
    CompanyBasicDTO updatePartial(CompanyBasicDTO companyBasicDTO, Long id);
    CompanyDepartmentsDTO getWithDepartments(Long id);
}

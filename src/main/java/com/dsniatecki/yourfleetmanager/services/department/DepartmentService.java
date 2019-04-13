package com.dsniatecki.yourfleetmanager.services.department;

import com.dsniatecki.yourfleetmanager.dto.department.DepartmentBasicDTO;
import com.dsniatecki.yourfleetmanager.dto.department.DepartmentDTO;

public interface DepartmentService {
    DepartmentDTO getWithCarsById(Long id);
    DepartmentBasicDTO getBasicById(Long id);
    DepartmentBasicDTO saveWithCompany(DepartmentBasicDTO departmentBasicDTO, Long companyId);
    DepartmentBasicDTO updatePartial(DepartmentBasicDTO departmentBasicDTO, Long id);
    void deleteById(Long id);
}

package com.dsniatecki.yourfleetmanager.mappers.department;

import com.dsniatecki.yourfleetmanager.dto.department.DepartmentBasicDTO;
import com.dsniatecki.yourfleetmanager.dto.department.DepartmentDTO;
import com.dsniatecki.yourfleetmanager.entities.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    DepartmentDTO departmentToDepartmentDTO(Department department);
    Department departmentBasicDTOToDepartment(DepartmentBasicDTO departmentBasicDTO);
    DepartmentBasicDTO departmentToDepartmentBasicDTO(Department department);
}

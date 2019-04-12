package com.dsniatecki.yourfleetmanager.mappers.department;

import com.dsniatecki.yourfleetmanager.dto.department.DepartmentBasicDTO;
import com.dsniatecki.yourfleetmanager.entities.Department;
import com.dsniatecki.yourfleetmanager.mappers.contactdetails.ContactDetailsPartialMapper;

public class DepartmentPartialMapper {
    public static void departmentBasicDTOToDepartment(DepartmentBasicDTO departmentBasicDTO, Department department){

        if(departmentBasicDTO.getName()!=null){
            department.setName(departmentBasicDTO.getName());
        }
        if(departmentBasicDTO.getId()!=null){
            department.setId(departmentBasicDTO.getId());
        }

        ContactDetailsPartialMapper.ContactDetailsDTOToContactDetails(
                departmentBasicDTO.getContactDetails(), department.getContactDetails());
    }
}

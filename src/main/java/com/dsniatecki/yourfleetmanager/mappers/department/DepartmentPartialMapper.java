package com.dsniatecki.yourfleetmanager.mappers.department;

import com.dsniatecki.yourfleetmanager.dto.department.DepartmentBasicDTO;
import com.dsniatecki.yourfleetmanager.entities.Department;

public class DepartmentPartialMapper {
    public static void departmentBasicDTOToDepartment(DepartmentBasicDTO departmentBasicDTO, Department department){
        if(departmentBasicDTO.getName()!=null){

            department.setName(departmentBasicDTO.getName());
        }
        if(departmentBasicDTO.getId()!=null){

            department.setId(departmentBasicDTO.getId());
        }

        if(departmentBasicDTO.getContactDetails()!=null){

            if(departmentBasicDTO.getContactDetails().getId()!=null){

                department.getContactDetails().setId(departmentBasicDTO.getContactDetails().getId());
            }
            if(departmentBasicDTO.getContactDetails().getEmailAddress()!=null){
                department.getContactDetails().setEmailAddress(departmentBasicDTO.getContactDetails().getEmailAddress());
            }
            if(departmentBasicDTO.getContactDetails().getTelephoneNumber()!=null){
                department.getContactDetails().setTelephoneNumber(departmentBasicDTO.getContactDetails().getTelephoneNumber());
            }
        }
    }
}

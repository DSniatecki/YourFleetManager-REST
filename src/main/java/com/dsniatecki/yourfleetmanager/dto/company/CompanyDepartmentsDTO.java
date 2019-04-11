package com.dsniatecki.yourfleetmanager.dto.company;


import com.dsniatecki.yourfleetmanager.dto.department.DepartmentBasicDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompanyDepartmentsDTO {

    private Long id;
    private String name;
    private List<DepartmentBasicDTO> departments;

    public CompanyDepartmentsDTO(){}

}

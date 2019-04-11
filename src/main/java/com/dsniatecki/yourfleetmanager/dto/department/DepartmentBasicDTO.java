package com.dsniatecki.yourfleetmanager.dto.department;

import com.dsniatecki.yourfleetmanager.dto.contactdetails.ContactDetailsDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentBasicDTO {
    private Long id;
    private String name;
    private ContactDetailsDTO contactDetails;
}

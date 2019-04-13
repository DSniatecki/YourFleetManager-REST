package com.dsniatecki.yourfleetmanager.dto.department;

import com.dsniatecki.yourfleetmanager.dto.car.CarBasicDTO;
import com.dsniatecki.yourfleetmanager.dto.car.CarDTO;
import com.dsniatecki.yourfleetmanager.dto.contactdetails.ContactDetailsDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DepartmentDTO {

    private Long id;
    private String name;
    private ContactDetailsDTO contactDetails;
    private List<CarBasicDTO> cars;

    public DepartmentDTO(){
        this.contactDetails = new ContactDetailsDTO();
        this.cars = new ArrayList<>();
    }
}

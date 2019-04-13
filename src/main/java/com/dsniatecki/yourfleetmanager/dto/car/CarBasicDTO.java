package com.dsniatecki.yourfleetmanager.dto.car;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarBasicDTO {

    private Long id;
    private String brand;
    private String model;
    private Integer productionYear;
    private String registrationNumber;

}

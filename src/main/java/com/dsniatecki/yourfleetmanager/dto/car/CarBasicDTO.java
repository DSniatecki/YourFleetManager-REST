package com.dsniatecki.yourfleetmanager.dto.car;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class CarBasicDTO {
    private Long id;
    private String brand;
    private String model;
    private int productionYear;
    private String registrationNumber;
}

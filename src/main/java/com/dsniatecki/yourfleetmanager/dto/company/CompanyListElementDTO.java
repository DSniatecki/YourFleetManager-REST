package com.dsniatecki.yourfleetmanager.dto.company;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyListElementDTO {

    private Long id;
    private String name;
    private String country;
    private String city;

}

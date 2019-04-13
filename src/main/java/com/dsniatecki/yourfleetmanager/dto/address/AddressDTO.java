package com.dsniatecki.yourfleetmanager.dto.address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {

    private Long id;
    private String street;
    private String buildingNumber;
    private String city;
    private String zipCode;
    private String country;

}

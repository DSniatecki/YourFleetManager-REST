package com.dsniatecki.yourfleetmanager.dto.contactdetails;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDetailsDTO {

    private Long id;
    private String telephoneNumber;
    private String emailAddress;

}

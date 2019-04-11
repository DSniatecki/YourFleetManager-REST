package com.dsniatecki.yourfleetmanager.dto.company;

import com.dsniatecki.yourfleetmanager.dto.address.AddressDTO;
import com.dsniatecki.yourfleetmanager.dto.contactdetails.ContactDetailsDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyBasicDTO {

    private Long id;
    private String name;
    private AddressDTO address;
    private ContactDetailsDTO contactDetails;

    public CompanyBasicDTO(){
        this.address = new AddressDTO();
        this.contactDetails = new ContactDetailsDTO();
    }

}

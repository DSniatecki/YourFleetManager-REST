package com.dsniatecki.yourfleetmanager.dto.vehicleresponder;

import com.dsniatecki.yourfleetmanager.dto.contactdetails.ContactDetailsDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleResponderDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private ContactDetailsDTO contactDetails;

    public VehicleResponderDTO(){
        this.contactDetails = new ContactDetailsDTO();
    }
}

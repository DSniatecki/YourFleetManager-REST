package com.dsniatecki.yourfleetmanager.mappers.contactdetails;

import com.dsniatecki.yourfleetmanager.dto.contactdetails.ContactDetailsDTO;
import com.dsniatecki.yourfleetmanager.entities.ContactDetails;

public class ContactDetailsPartialMapper {

    public static void ContactDetailsDTOToContactDetails(
            ContactDetailsDTO contactDetailsDTO, ContactDetails contactDetails){

        if(contactDetailsDTO!=null) {

            if (contactDetailsDTO.getId() != null) {
                contactDetails.setId(contactDetailsDTO.getId());
            }
            if (contactDetailsDTO.getTelephoneNumber() != null) {
                contactDetails.setTelephoneNumber(contactDetailsDTO.getTelephoneNumber());
            }
            if (contactDetailsDTO.getEmailAddress() != null) {
                contactDetails.setEmailAddress(contactDetailsDTO.getEmailAddress());
            }
        }

    }

}

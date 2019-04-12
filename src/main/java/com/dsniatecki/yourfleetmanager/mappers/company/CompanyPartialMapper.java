package com.dsniatecki.yourfleetmanager.mappers.company;

import com.dsniatecki.yourfleetmanager.dto.company.CompanyBasicDTO;
import com.dsniatecki.yourfleetmanager.entities.Company;

public class CompanyPartialMapper {

    public static void companyBasicDTOToCopmany(CompanyBasicDTO companyBasicDTO, Company company){
        if(companyBasicDTO.getId()!=null){
            company.setId(companyBasicDTO.getId());
        }
        if(companyBasicDTO.getName()!=null){
            company.setName(companyBasicDTO.getName());
        }
        if(companyBasicDTO.getAddress()!=null){
            if(companyBasicDTO.getAddress().getId()!=null){
                company.getAddress().setId(companyBasicDTO.getAddress().getId());
            }
            if(companyBasicDTO.getAddress().getStreet()!=null){
                company.getAddress().setStreet(companyBasicDTO.getAddress().getStreet());
            }
            if(companyBasicDTO.getAddress().getBuildingNumber()!=null){
                company.getAddress().setBuildingNumber(companyBasicDTO.getAddress().getBuildingNumber());
            }
            if(companyBasicDTO.getAddress().getZipCode()!=null){
                company.getAddress().setZipCode(companyBasicDTO.getAddress().getZipCode());
            }
            if(companyBasicDTO.getAddress().getCity()!=null){
                company.getAddress().setCity(companyBasicDTO.getAddress().getCity());

            }if(companyBasicDTO.getAddress().getCountry()!=null){
                company.getAddress().setCountry(companyBasicDTO.getAddress().getCountry());
            }
        }
        if(companyBasicDTO.getContactDetails()!=null){

            if(companyBasicDTO.getContactDetails().getId()!=null){
                company.getContactDetails().setId(companyBasicDTO.getContactDetails().getId());
            }
            if(companyBasicDTO.getContactDetails().getTelephoneNumber()!=null){
                company.getContactDetails().setTelephoneNumber(companyBasicDTO.getContactDetails().getTelephoneNumber());
            }
            if(companyBasicDTO.getContactDetails().getEmailAddress()!=null){
                company.getContactDetails().setEmailAddress(companyBasicDTO.getContactDetails().getEmailAddress());
            }
        }
    }

}

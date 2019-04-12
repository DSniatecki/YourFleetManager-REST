package com.dsniatecki.yourfleetmanager.mappers.car;

import com.dsniatecki.yourfleetmanager.dto.car.CarDTO;
import com.dsniatecki.yourfleetmanager.entities.Car;
import com.dsniatecki.yourfleetmanager.mappers.contactdetails.ContactDetailsPartialMapper;

public class CarPartialMapper {

    public static void carDTOToCar(CarDTO carDTO, Car car) {

        if (carDTO.getId() != null) {
            car.setId(carDTO.getId());
        }
        if (carDTO.getBrand() != null) {
            car.setBrand(carDTO.getBrand());
        }
        if (carDTO.getModel() != null) {
            car.setModel(carDTO.getModel());
        }
        if (carDTO.getProductionYear() != null) {
            car.setProductionYear(carDTO.getProductionYear());
        }
        if (carDTO.getRegistrationNumber() != null) {
            car.setRegistrationNumber(carDTO.getRegistrationNumber());
        }
        if (carDTO.getVehicleResponder() != null) {

            if (carDTO.getVehicleResponder().getId() != null) {
                car.getVehicleResponder().setId(carDTO.getVehicleResponder().getId());
            }
            if (carDTO.getVehicleResponder().getFirstName() != null) {
                car.getVehicleResponder().setFirstName(carDTO.getVehicleResponder().getFirstName());
            }
            if (carDTO.getVehicleResponder().getLastName() != null) {
                car.getVehicleResponder().setLastName(carDTO.getVehicleResponder().getLastName());
            }
            ContactDetailsPartialMapper.ContactDetailsDTOToContactDetails(
                    carDTO.getVehicleResponder().getContactDetails(), car.getVehicleResponder().getContactDetails());
        }
    }

}

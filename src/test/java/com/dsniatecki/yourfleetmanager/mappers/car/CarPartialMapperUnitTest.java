package com.dsniatecki.yourfleetmanager.mappers.car;

import com.dsniatecki.yourfleetmanager.dto.car.CarDTO;
import com.dsniatecki.yourfleetmanager.entities.Car;
import org.dbunit.Assertion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("CarPartialMapper - Unit Test")
class CarPartialMapperUnitTest {

    @Test
    void carDTOToCarTest() throws Exception{

        CarDTO carDTO = new CarDTO();
        carDTO.setBrand("SuperCar");
        carDTO.setProductionYear(2008);

        Car car = new Car();
        car.setId(45L);
        car.setBrand("FunnyCar");
        car.setModel("FunnyModel");
        car.setRegistrationNumber("JAVA 12");
        car.getVehicleResponder().setId(20L);
        car.getVehicleResponder().setFirstName("Damian");
        car.getVehicleResponder().getContactDetails().setId(30L);
        car.getVehicleResponder().getContactDetails().setEmailAddress("siemkaTasiemka@gmail.com");

        CarPartialMapper.carDTOToCar(carDTO, car);

        Assertions.assertNotNull(car);
        Assertions.assertSame(car.getId(), 45L);
        Assertions.assertEquals(car.getBrand(), carDTO.getBrand());
        Assertions.assertEquals(car.getModel(), "FunnyModel");
        Assertions.assertSame(car.getProductionYear(), carDTO.getProductionYear());
        Assertions.assertSame(car.getVehicleResponder().getId(), 20L);
        Assertions.assertEquals(car.getVehicleResponder().getFirstName(), "Damian");
    }

}

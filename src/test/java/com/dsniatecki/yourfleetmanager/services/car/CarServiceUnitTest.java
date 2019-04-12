package com.dsniatecki.yourfleetmanager.services.car;


import com.dsniatecki.yourfleetmanager.dto.car.CarDTO;
import com.dsniatecki.yourfleetmanager.entities.Car;
import com.dsniatecki.yourfleetmanager.entities.Department;
import com.dsniatecki.yourfleetmanager.mappers.car.CarMapper;
import com.dsniatecki.yourfleetmanager.repositories.CarRepository;
import com.dsniatecki.yourfleetmanager.repositories.DepartmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("CarServiceImpl - Unit Tests")
class CarServiceUnitTest {

    @Autowired
    private CarService carService;

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private DepartmentRepository departmentRepository;

    @Test
    @DisplayName("Test: getById() - Success")
    void getByIdTest() throws Exception{
        Car car = generateCar();

        doReturn(Optional.of(car)).when(carRepository).findById(1L);

        CarDTO carDTO = carService.getById(1L);

        Assertions.assertNotNull(carDTO);

        Assertions.assertEquals(carDTO.getId(), car.getId());
        Assertions.assertEquals(carDTO.getVehicleResponder().getId(), car.getVehicleResponder().getId());
        Assertions.assertEquals(carDTO.getVehicleResponder().getContactDetails().getId(),
                car.getVehicleResponder().getContactDetails().getId());


    }

    @Test
    @DisplayName("Test: saveWithDepartmentTest() - Success")
    void saveWithDepartmentTest() throws Exception{
        CarDTO carDTO = CarMapper.INSTANCE.carToCarDTO(generateCar());
        Car car = generateCar();

        doReturn(Optional.of(generateDepartment())).when(departmentRepository).findById(any());
        doReturn(car).when(carRepository).save(any());

        CarDTO saveCar = carService.saveWithDepartment(carDTO, 10L);

        Assertions.assertNotNull(saveCar);

        Assertions.assertEquals(saveCar.getId(), car.getId());
        Assertions.assertEquals(carDTO.getBrand(), car.getBrand());
        Assertions.assertEquals(carDTO.getModel(), car.getModel());
        Assertions.assertEquals(saveCar.getVehicleResponder().getId(), car.getVehicleResponder().getId());
        Assertions.assertEquals(saveCar.getVehicleResponder().getContactDetails().getId(),
                car.getVehicleResponder().getContactDetails().getId());


    }


    @Test
    @DisplayName("Test: updatePartialTest() - Success")
    void updatePartialTest() throws Exception{
        CarDTO carDTO = CarMapper.INSTANCE.carToCarDTO(generateCar());

        Car car = generateCar();

        doReturn(Optional.of(car)).when(carRepository).findById(1L);
        doReturn(car).when(carRepository).save(any());

        CarDTO updatedCar = carService.updatePartiaL(carDTO, 1L);

        Assertions.assertNotNull(updatedCar);

        Assertions.assertEquals(updatedCar.getId(), car.getId());
        Assertions.assertEquals(updatedCar.getBrand(), car.getBrand());
        Assertions.assertEquals(updatedCar.getModel(), car.getModel());
        Assertions.assertEquals(updatedCar.getVehicleResponder().getId(), car.getVehicleResponder().getId());
        Assertions.assertEquals(updatedCar.getVehicleResponder().getContactDetails().getId(),
                car.getVehicleResponder().getContactDetails().getId());


    }



    private Car generateCar(){
        Car car = new Car();
        car.setId(45L);
        car.setBrand("FunnyCar");
        car.setModel("FunnyModel");
        car.setRegistrationNumber("JAVA 12");
        car.getVehicleResponder().setId(20L);
        car.getVehicleResponder().setFirstName("Damian");
        car.getVehicleResponder().getContactDetails().setId(30L);
        car.getVehicleResponder().getContactDetails().setEmailAddress("siemkaTasiemka@gmail.com");
        return car;
    }


    private Department generateDepartment(){
        Department department = new Department();
        department.setId(15L);
        department.setName("FunnyDepartment");
        department.getContactDetails().setId(20L);
        department.getContactDetails().setEmailAddress("funnyemail@email.com");
        department.getContactDetails().setTelephoneNumber("563-432-342");
        Car car = new Car();
        car.setId(45L);
        car.setBrand("FunnyCar");
        car.setModel("FunnyModel");
        car.setRegistrationNumber("JAVA 12");

        department.addCar(car);

        return department;
    }

}

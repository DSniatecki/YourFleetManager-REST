package com.dsniatecki.yourfleetmanager.mappers.department;

import com.dsniatecki.yourfleetmanager.dto.department.DepartmentBasicDTO;
import com.dsniatecki.yourfleetmanager.entities.Car;
import com.dsniatecki.yourfleetmanager.entities.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("DepartmentServiceImpl - Unit Tests")
 class DepartmentPartialMapperUnitTest {



    @Test
    @DisplayName("test: departmentBasicDTOToDepartment() - Success")
    void departmentBasicDTOToDepartment(){

        Department department = new Department();
        department.setId(15L);
        department.setName("FunnyDepartment");
        department.getContactDetails().setId(20L);
        department.getContactDetails().setEmailAddress("funnyemail@email.com");
        department.getContactDetails().setTelephoneNumber("563-432-342");
        Car car = new Car();
        car.setId(45L);
        car.setBrand("FunnyCar");
        department.addCar(car);

        DepartmentBasicDTO departmentBasicDTO = new DepartmentBasicDTO();
        departmentBasicDTO.setName("FunnyDepartment");
        departmentBasicDTO.getContactDetails().setEmailAddress("funnyemail@email.com");
        departmentBasicDTO.getContactDetails().setTelephoneNumber("563-432-342");

        DepartmentPartialMapper.departmentBasicDTOToDepartment(departmentBasicDTO, department);

        Assertions.assertSame(department.getId(), 15L);
        Assertions.assertSame(department.getCars().get(0).getId(), 45L);
        Assertions.assertEquals(department.getCars().get(0).getBrand(), "FunnyCar");
        Assertions.assertEquals(department.getName(), departmentBasicDTO.getName());
        Assertions.assertEquals(department.getContactDetails().getEmailAddress(),
                                    departmentBasicDTO.getContactDetails().getEmailAddress());
        Assertions.assertEquals(department.getContactDetails().getTelephoneNumber(),
                                    departmentBasicDTO.getContactDetails().getTelephoneNumber()
        );
    }

}

package com.dsniatecki.yourfleetmanager.services.department;

import com.dsniatecki.yourfleetmanager.dto.department.DepartmentBasicDTO;
import com.dsniatecki.yourfleetmanager.dto.department.DepartmentDTO;
import com.dsniatecki.yourfleetmanager.entities.Car;
import com.dsniatecki.yourfleetmanager.entities.Company;
import com.dsniatecki.yourfleetmanager.entities.Department;
import com.dsniatecki.yourfleetmanager.mappers.department.DepartmentMapper;
import com.dsniatecki.yourfleetmanager.repositories.CompanyRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("DepartmentServiceImpl - Unit Tests")
class DepartmentServiceImplUnitTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @MockBean
    private CompanyRepository companyRepository;


    @Test
    @DisplayName("Test: getWithCarsById() -Success")
    void getWithCarsById() {

        Department department = generateDepartment();

        doReturn(Optional.of(department)).when(departmentRepository).findById(15L);
        DepartmentDTO returnedDepartment = departmentService.getWithCarsById(15L);

        Assertions.assertNotNull(returnedDepartment);
        Assertions.assertEquals(returnedDepartment.getId(), department.getId());
        Assertions.assertEquals(returnedDepartment.getName(), department.getName());
        Assertions.assertEquals(returnedDepartment.getContactDetails().getId(),
                                    department.getContactDetails().getId());
        Assertions.assertEquals(returnedDepartment.getContactDetails().getTelephoneNumber(),
                                    department.getContactDetails().getTelephoneNumber());
        Assertions.assertEquals(returnedDepartment.getCars().get(0).getId(),
                                    department.getCars().get(0).getId());
        Assertions.assertEquals(returnedDepartment.getCars().get(0).getBrand(),
                                    department.getCars().get(0).getBrand());
        Assertions.assertEquals(returnedDepartment.getCars().get(0).getModel(),
                                     department.getCars().get(0).getModel());
        Assertions.assertEquals(returnedDepartment.getCars().get(0).getRegistrationNumber(),
                                     department.getCars().get(0).getRegistrationNumber());
    }

    @Test
    @DisplayName("Test: getBasicById() -Success")
    void getBasicById() {

        Department department = generateDepartment();

        doReturn(Optional.of(department)).when(departmentRepository).findById(15L);
        DepartmentBasicDTO returnedDepartment = departmentService.getBasicById(15L);

        Assertions.assertNotNull(returnedDepartment);
        Assertions.assertEquals(returnedDepartment.getId(), department.getId());
        Assertions.assertEquals(returnedDepartment.getName(), department.getName());
        Assertions.assertEquals(returnedDepartment.getContactDetails().getId(), department.getContactDetails().getId());
        Assertions.assertEquals(returnedDepartment.getContactDetails().getTelephoneNumber(),
                                     department.getContactDetails().getTelephoneNumber());
    }

    @Test
    @DisplayName("Test: saveWithCompany() -Success")
    void saveWithCompany() {

        DepartmentBasicDTO departmentBasicDTO =
                DepartmentMapper.INSTANCE.departmentToDepartmentBasicDTO(generateDepartment());
        Department department = generateDepartment();

        doReturn(department).when(departmentRepository).save(any());
        doReturn(Optional.of(generateCompany())).when(companyRepository).findById(15L);
        DepartmentBasicDTO savedDepartment = departmentService.saveWithCompany(departmentBasicDTO, 15L);

        Assertions.assertNotNull(savedDepartment);
        Assertions.assertEquals(savedDepartment.getId(), department.getId());
        Assertions.assertEquals(savedDepartment.getName(), department.getName());
        Assertions.assertEquals(savedDepartment.getContactDetails().getId(), department.getContactDetails().getId());
        Assertions.assertEquals(savedDepartment.getContactDetails().getTelephoneNumber(),
                                    department.getContactDetails().getTelephoneNumber());
    }

    @Test
    @DisplayName("Test: updatePartial() -Success")
    void updatePartial() {

        DepartmentBasicDTO departmentBasicDTO =
                DepartmentMapper.INSTANCE.departmentToDepartmentBasicDTO(generateDepartment());
        Department department = generateDepartment();

        doReturn(Optional.of(department)).when(departmentRepository).findById(15L);
        doReturn(department).when(departmentRepository).save(any());
        DepartmentBasicDTO updatedDepartment = departmentService.updatePartial(departmentBasicDTO, 15L);

        Assertions.assertNotNull(updatedDepartment);
        Assertions.assertEquals(updatedDepartment.getId(), department.getId());
        Assertions.assertEquals(updatedDepartment.getName(), department.getName());
        Assertions.assertEquals(updatedDepartment.getContactDetails().getId(),
                                    department.getContactDetails().getId());
        Assertions.assertEquals(updatedDepartment.getContactDetails().getTelephoneNumber(),
                                    department.getContactDetails().getTelephoneNumber());
        Assertions.assertSame(department.getCars().get(0).getId(), 45L);
        Assertions.assertEquals(department.getCars().get(0).getBrand(), "FunnyCar");
        Assertions.assertEquals(department.getCars().get(0).getModel(), "FunnyModel");
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

    private Company generateCompany(){
        Company company = new Company();
        company.setId(1L);
        company.setName("company");
        company.getAddress().setId(12L);
        company.getAddress().setCountry("Poland");
        company.getAddress().setCity("Wrolcaw");
        company.getContactDetails().setId(15L);
        company.getContactDetails().setTelephoneNumber("563 231 321");
        Department department = generateDepartment();
        company.addDepartment(department);
        return company;
    }

}
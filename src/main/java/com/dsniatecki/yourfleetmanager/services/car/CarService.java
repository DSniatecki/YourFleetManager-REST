package com.dsniatecki.yourfleetmanager.services.car;

import com.dsniatecki.yourfleetmanager.dto.car.CarDTO;

public interface CarService {
    CarDTO getById(Long id);
    void deleteById(Long id);
    CarDTO saveWithDepartment(CarDTO carDTO, Long departmentId);
    CarDTO updatePartiaL(CarDTO carDTO, Long id);
}

package com.dsniatecki.yourfleetmanager.mappers.car;

import com.dsniatecki.yourfleetmanager.dto.car.CarDTO;
import com.dsniatecki.yourfleetmanager.entities.Car;
import com.dsniatecki.yourfleetmanager.mappers.department.DepartmentMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {
    CarMapper INSTANCE =  Mappers.getMapper(CarMapper.class);

    CarDTO carToCarDTO(Car car);
    Car CarDTOToCar(CarDTO carDTO);
}

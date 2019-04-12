package com.dsniatecki.yourfleetmanager.services.car;

import com.dsniatecki.yourfleetmanager.dto.car.CarDTO;
import com.dsniatecki.yourfleetmanager.entities.Car;
import com.dsniatecki.yourfleetmanager.entities.Department;
import com.dsniatecki.yourfleetmanager.exceptions.ResourceNotFoundException;
import com.dsniatecki.yourfleetmanager.mappers.car.CarMapper;
import com.dsniatecki.yourfleetmanager.mappers.car.CarPartialMapper;
import com.dsniatecki.yourfleetmanager.repositories.CarRepository;
import com.dsniatecki.yourfleetmanager.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class CarServiceImpl implements CarService{

    private CarRepository carRepository;
    private DepartmentRepository departmentRepository;

    public CarServiceImpl(CarRepository carRepository, DepartmentRepository departmentRepository){
        this.carRepository = carRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public CarDTO getById(Long id) {
        Optional<Car> carOptional = carRepository.findById(id);
        checkIsNotPresent(carOptional, id);
        return CarMapper.INSTANCE.carToCarDTO(carOptional.get());
    }

    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public CarDTO saveWithDepartment(CarDTO carDTO, Long departmentId) {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        if(!departmentOptional.isPresent())
             throw new ResourceNotFoundException("Department[id:" + departmentId +"] was not found.");
        Car car = CarMapper.INSTANCE.CarDTOToCar(carDTO);
        car.setDepartment(departmentOptional.get());
        return CarMapper.INSTANCE.carToCarDTO(carRepository.save(car));
    }

    @Override
    public CarDTO updatePartiaL(CarDTO carDTO, Long id) {
        Optional<Car> carOptional = carRepository.findById(id);
        checkIsNotPresent(carOptional, id);
        Car car = carOptional.get();
        CarPartialMapper.carDTOToCar(carDTO, car);
        return CarMapper.INSTANCE.carToCarDTO(carRepository.save(car));
    }

    private void checkIsNotPresent(Optional<Car> optional, Long id){
        if(!optional.isPresent()){
            throw new ResourceNotFoundException("Car[id:" + id +"] was not found.");
        }
    }

}

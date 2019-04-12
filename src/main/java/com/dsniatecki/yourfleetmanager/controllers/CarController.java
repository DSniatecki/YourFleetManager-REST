package com.dsniatecki.yourfleetmanager.controllers;


import com.dsniatecki.yourfleetmanager.dto.car.CarDTO;
import com.dsniatecki.yourfleetmanager.services.car.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/companies/{companyId}/departments/{departmentId}/cars")
@Api(description="controller responsible for operations on the car object")
class CarController {

    private CarService carService;

    public CarController(CarService carService){
        this.carService = carService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarDTO getById(@PathVariable String id){
        return carService.getById(Long.valueOf(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable String id){
        carService.deleteById(Long.valueOf(id));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO addNewToDepartment(@RequestBody CarDTO carDTO, @PathVariable String companyId ){
        return carService.saveWithDepartment(carDTO, Long.valueOf(companyId));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarDTO updatePartialy(@RequestBody CarDTO carDTO, @PathVariable String id ){
        return carService.updatePartiaL(carDTO, Long.valueOf(id));
    }

}

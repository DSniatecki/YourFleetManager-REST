package com.dsniatecki.yourfleetmanager.controllers;


import com.dsniatecki.yourfleetmanager.dto.department.DepartmentBasicDTO;
import com.dsniatecki.yourfleetmanager.dto.department.DepartmentDTO;
import com.dsniatecki.yourfleetmanager.services.department.DepartmentService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/companies/{companyId}/departments")
@Api(description="controller responsible for operations on the department object")
class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DepartmentBasicDTO getBasicById(@PathVariable String id){
        return departmentService.getBasicById(Long.valueOf(id));
    }

    @GetMapping("/{id}/cars")
    @ResponseStatus(HttpStatus.OK)
    public DepartmentDTO getWithCarsById(@PathVariable String id){ return departmentService.getWithCarsById(Long.valueOf(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable String id){
        departmentService.deleteById(Long.valueOf(id));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentBasicDTO addNewToCompany(@RequestBody DepartmentBasicDTO departmentBasicDTO,
                                              @PathVariable String companyId ){
        return departmentService.saveWithCompany( departmentBasicDTO, Long.valueOf(companyId));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DepartmentBasicDTO updatePartially(@RequestBody DepartmentBasicDTO departmentBasicDTO,
                                              @PathVariable String id){
        return departmentService.updatePartial(departmentBasicDTO, Long.valueOf(id));
    }
}

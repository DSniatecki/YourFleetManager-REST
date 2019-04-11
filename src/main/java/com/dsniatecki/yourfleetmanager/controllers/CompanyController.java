package com.dsniatecki.yourfleetmanager.controllers;

import com.dsniatecki.yourfleetmanager.dto.company.CompanyBasicDTO;
import com.dsniatecki.yourfleetmanager.dto.company.CompanyDepartmentsDTO;
import com.dsniatecki.yourfleetmanager.dto.company.CompanyListElementDTO;
import com.dsniatecki.yourfleetmanager.services.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/companies")
class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }


    @GetMapping("/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyBasicDTO retriveBasic(@PathVariable String companyId){
        return companyService.getBasicById(Long.valueOf(companyId));
    }

    @GetMapping("/{companyId}/departments")
    @ResponseStatus(HttpStatus.OK)
    public CompanyDepartmentsDTO retriveWithDepartments(@PathVariable String companyId){
        return companyService.getWithDepartments(Long.valueOf(companyId));
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<CompanyListElementDTO> retriveList(){
        return companyService.getAllAsListElements();
    }

    @DeleteMapping("/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable String companyId){
        companyService.deleteById(Long.valueOf(companyId));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyBasicDTO addNew(@RequestBody CompanyBasicDTO companyBasicDTO){
        return companyService.saveBasic(companyBasicDTO);
    }

    @PutMapping("/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyBasicDTO updateWhole(@RequestBody CompanyBasicDTO companyBasicDTO, @PathVariable String companyId){
        return companyService.saveBasic(companyBasicDTO);
    }


    @PatchMapping("/{companyId}")
    @ResponseStatus(value = HttpStatus.OK)
    public CompanyBasicDTO updatePartialy(@RequestBody CompanyBasicDTO companyBasicDTO, @PathVariable String companyId){
        return companyService.updatePartial(companyBasicDTO, Long.valueOf(companyId) );
    }



}

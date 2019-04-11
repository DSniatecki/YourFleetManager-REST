package com.dsniatecki.yourfleetmanager.controllers;

import com.dsniatecki.yourfleetmanager.dto.company.CompanyBasicDTO;
import com.dsniatecki.yourfleetmanager.dto.company.CompanyDepartmentsDTO;
import com.dsniatecki.yourfleetmanager.dto.company.CompanyListElementDTO;
import com.dsniatecki.yourfleetmanager.services.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction.fromOptionalString;

@RestController
@RequestMapping("/v1/companies")
class CompanyController {

    private static final int DEFAULT_PAGE_SIZE=20;

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

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Page<CompanyListElementDTO> retrivePage(@RequestParam("page") Optional<Integer> page,
                                                   @RequestParam("size") Optional<Integer> size,
                                                   @RequestParam("order") Optional<String> order,
                                                   @RequestParam("direction") Optional<String> direction){
        PageRequest pageRequest = PageRequest.of(
                page.orElse(1) -1,
                size.orElse(DEFAULT_PAGE_SIZE),
                Sort.Direction.fromString(direction.orElse("ASC")),
                order.orElse("name")
        );
        return companyService.getPageOfListElements(pageRequest);
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

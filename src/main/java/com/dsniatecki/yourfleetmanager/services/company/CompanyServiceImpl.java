package com.dsniatecki.yourfleetmanager.services.company;

import com.dsniatecki.yourfleetmanager.dto.company.CompanyBasicDTO;
import com.dsniatecki.yourfleetmanager.dto.company.CompanyDepartmentsDTO;
import com.dsniatecki.yourfleetmanager.dto.company.CompanyListElementDTO;
import com.dsniatecki.yourfleetmanager.entities.Company;
import com.dsniatecki.yourfleetmanager.exceptions.ResourceNotFoundException;
import com.dsniatecki.yourfleetmanager.mappers.company.CompanyMapper;
import com.dsniatecki.yourfleetmanager.mappers.company.CompanyPartialMapper;
import com.dsniatecki.yourfleetmanager.repositories.CompanyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }


    @Override
    public CompanyBasicDTO getBasicById(Long id){
        Optional<Company> companyOptional =  companyRepository.findById(id);
        checkIsNotPresent(companyOptional, id);
        return CompanyMapper.INSTANCE.companyToCompanyBasicDTO(companyOptional.get());
    }

    @Override
    @Transactional
    public CompanyDepartmentsDTO getWithDepartments(Long id) {
        Optional<Company> companyOptional =  companyRepository.findById(id);
        checkIsNotPresent(companyOptional, id);
        return CompanyMapper.INSTANCE.companyToCompanyDepartmentsDTO(companyOptional.get());
    }

    @Override
    public List<CompanyListElementDTO> getAllAsListElements() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyListElementDTO> listCompanies = new ArrayList<>();
        companies.forEach(company ->
                listCompanies.add(CompanyMapper.INSTANCE.companyToCompanyListElementDTO(company)));

        return listCompanies;
    }

    @Override
    public Page<CompanyListElementDTO> getPageOfListElements(Pageable pageable) {
        Page<Company> companiesPage = companyRepository.findAll(pageable);
        return companiesPage.map((CompanyMapper.INSTANCE::companyToCompanyListElementDTO));
    }

    @Override
    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public CompanyBasicDTO saveBasic(CompanyBasicDTO companyBasicDTO) {
        Company savedCompany = companyRepository.save(
                CompanyMapper.INSTANCE.companyBasicDTOToCompany(companyBasicDTO));
        return CompanyMapper.INSTANCE.companyToCompanyBasicDTO(savedCompany);
    }

    @Override
    public CompanyBasicDTO updatePartial(CompanyBasicDTO companyBasicDTO, Long id) {
        Optional<Company> companyOptional =  companyRepository.findById(id);
        checkIsNotPresent(companyOptional, id);
        Company company = companyOptional.get();
        CompanyPartialMapper.companyBasicDTOToCopmany(companyBasicDTO, company);
        return CompanyMapper.INSTANCE.companyToCompanyBasicDTO(companyRepository.save(company));
    }


     private void checkIsNotPresent(Optional<Company> optional, Long id){
        if(!optional.isPresent()){
            throw new ResourceNotFoundException("Company[id:" + id +"] was not found.");
        }
     }

}

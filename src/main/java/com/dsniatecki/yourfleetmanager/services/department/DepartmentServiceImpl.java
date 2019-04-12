package com.dsniatecki.yourfleetmanager.services.department;

import com.dsniatecki.yourfleetmanager.dto.department.DepartmentBasicDTO;
import com.dsniatecki.yourfleetmanager.dto.department.DepartmentDTO;
import com.dsniatecki.yourfleetmanager.entities.Company;
import com.dsniatecki.yourfleetmanager.entities.Department;
import com.dsniatecki.yourfleetmanager.exceptions.ResourceNotFoundException;
import com.dsniatecki.yourfleetmanager.mappers.department.DepartmentMapper;
import com.dsniatecki.yourfleetmanager.mappers.department.DepartmentPartialMapper;
import com.dsniatecki.yourfleetmanager.repositories.CompanyRepository;
import com.dsniatecki.yourfleetmanager.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    private CompanyRepository companyRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, CompanyRepository companyRepository){
        this.departmentRepository = departmentRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public DepartmentDTO getWithCarsById(Long id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        checkIsNotPresent(departmentOptional, id);
        return DepartmentMapper.INSTANCE.departmentToDepartmentDTO(departmentOptional.get());
    }

    @Override
    public DepartmentBasicDTO getBasicById(Long id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        checkIsNotPresent(departmentOptional, id);
        return DepartmentMapper.INSTANCE.departmentToDepartmentBasicDTO(departmentOptional.get());
    }

    @Override
    public DepartmentBasicDTO saveWithCompany(DepartmentBasicDTO departmentBasicDTO, Long companyId) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if(!companyOptional.isPresent()) throw new ResourceNotFoundException("Company[id:" + companyId +"] was not found.");
        Department department = DepartmentMapper.INSTANCE.departmentBasicDTOToDepartment(departmentBasicDTO);
        department.setCompany(companyOptional.get());
        return DepartmentMapper.INSTANCE.departmentToDepartmentBasicDTO(departmentRepository.save(department));
    }

    @Override
    public DepartmentBasicDTO updatePartial(DepartmentBasicDTO departmentBasicDTO, Long id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        checkIsNotPresent(departmentOptional, id);
        Department department = departmentOptional.get();
        DepartmentPartialMapper.departmentBasicDTOToDepartment(departmentBasicDTO, department);
        return DepartmentMapper.INSTANCE.departmentToDepartmentBasicDTO(departmentRepository.save(department));
    }

    @Override
    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }

    private void checkIsNotPresent(Optional<Department> optional, Long id){
        if(!optional.isPresent()){
            throw new ResourceNotFoundException("Department[id:" + id +"] was not found.");
        }
    }
}

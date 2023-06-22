package com.example.departmentservice.service.impl;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.repository.DepartmentRepository;
import com.example.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = modelMapper.map(departmentDto, Department.class);
        Department savedDepartment = departmentRepository.save(department);
        DepartmentDto savedDepartmentDto = modelMapper.map(savedDepartment, DepartmentDto.class);
        return savedDepartmentDto;
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map((department) -> modelMapper.map(department,DepartmentDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public DepartmentDto getDepartmentById(long id) {
        Department department = departmentRepository.findById(id).get();
        return modelMapper.map(department, DepartmentDto.class);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department department = departmentRepository.findByDepartmentCode(code);
        return modelMapper.map(department, DepartmentDto.class);
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
        Department existingDepartment = departmentRepository.findById(departmentDto.getId()).get();
        existingDepartment.setDepartmentName(departmentDto.getDepartmentName());
        existingDepartment.setDepartmentDescription(departmentDto.getDepartmentDescription());
        existingDepartment.setDepartmentCode(departmentDto.getDepartmentCode());
        Department updatedDepartment = departmentRepository.save(existingDepartment);
        return modelMapper.map(updatedDepartment, DepartmentDto.class);
    }

    @Override
    public void deleteDepartmentById(long id) {
        departmentRepository.deleteById(id);
    }
}

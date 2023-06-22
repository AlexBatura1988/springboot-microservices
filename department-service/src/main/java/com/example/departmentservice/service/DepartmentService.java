package com.example.departmentservice.service;

import com.example.departmentservice.dto.DepartmentDto;

import java.util.List;


public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto departmentDto);
    List<DepartmentDto> getAllDepartments();
    DepartmentDto getDepartmentById(long id);
    DepartmentDto getDepartmentByCode(String code);
    DepartmentDto updateDepartment(DepartmentDto departmentDto);
    void deleteDepartmentById(long id);

}

package com.example.employeeservice.service;

import com.example.employeeservice.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(long id);
    List<EmployeeDto> getAllEmployees();
}

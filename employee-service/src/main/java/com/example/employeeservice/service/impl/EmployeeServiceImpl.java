package com.example.employeeservice.service.impl;

import com.example.employeeservice.dto.APIResponseDto;
import com.example.employeeservice.dto.DepartmentDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.service.APIClient;
import com.example.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    private APIClient apiClient;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee savedEmployee = modelMapper.map(employeeDto, Employee.class);
        employeeRepository.save(savedEmployee);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public APIResponseDto getEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id).get();
        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        return apiResponseDto;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());

    }
}

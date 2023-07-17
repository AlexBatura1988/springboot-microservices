package com.example.employeeservice.service.impl;

import com.example.employeeservice.dto.APIResponseDto;
import com.example.employeeservice.dto.DepartmentDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.dto.OrganizationDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.service.APIClient;
import com.example.employeeservice.service.EmployeeService;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    private APIClient apiClient;
    private OrganizationClient organizationClient;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee savedEmployee = modelMapper.map(employeeDto, Employee.class);
        employeeRepository.save(savedEmployee);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }
    //@CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id).get();
        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());
        OrganizationDto organizationDto = organizationClient.getOrganizationByCode(employee.getOrganizationCode());

        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        apiResponseDto.setOrganization(organizationDto);
        return apiResponseDto;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());

    }

    public APIResponseDto getDefaultDepartment(long id, Exception exception){
        Employee employee = employeeRepository.findById(id).get();
        //DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("111");
        departmentDto.setDepartmentDescription("DevDefault");

        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setOrganizationName("no name");
        organizationDto.setOrganizationDescription("no description");
        organizationDto.setOrganizationCode("no code");
        organizationDto.setCreatedDate(LocalDateTime.now());

        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        apiResponseDto.setOrganization(organizationDto);
        return apiResponseDto;
    }
}

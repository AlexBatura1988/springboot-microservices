package com.example.departmentservice.controller;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(
        name = "Department Service -Department Controller",
        description = "Department Controller Exposes REST APIs for Department-Service"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/departments")
public class DepartmentController {
    private DepartmentService departmentService;

    @Operation(
            summary = "Save Department Rest API",
            description = "Save Department REST API is used to save department to database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"

    )
    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto savedDepartment = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        List<DepartmentDto> departments = departmentService.getAllDepartments();
         return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @Operation(
            summary = "GET Department Rest API",
            description = "Save Department REST API is used to get department to database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"

    )
    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") long id) {
        DepartmentDto departmentDto = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    @GetMapping("code/{code}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable("code") String code) {
        DepartmentDto departmentDto = departmentService.getDepartmentByCode(code);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("id") long id, @RequestBody DepartmentDto departmentDto) {
        departmentDto.setId(id);
        DepartmentDto updatedDepartment = departmentService.updateDepartment(departmentDto);
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable long id) {
        departmentService.deleteDepartmentById(id);
        return new ResponseEntity<>("Department was successfully deleted ", HttpStatus.OK);
    }
}

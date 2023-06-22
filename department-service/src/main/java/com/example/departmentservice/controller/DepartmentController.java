package com.example.departmentservice.controller;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/departments")
public class DepartmentController {
    private DepartmentService departmentService;

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

package com.example.employeeservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name",nullable = false)
     private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column(name = "email",nullable = false, unique = true)
    private String email;
    @Column(name = "department_code")
    private String departmentCode;
    @Column(name = "organization_code")
    private String organizationCode;
}

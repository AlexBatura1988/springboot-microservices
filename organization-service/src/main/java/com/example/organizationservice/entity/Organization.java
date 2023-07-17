package com.example.organizationservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "organizations")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "organization_name", nullable = false)
    private String organizationName;
    @Column(name = "organization_description")
    private String organizationDescription;
    @Column(name = "organization_code", nullable = false, unique = true)
    private String organizationCode;
    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;
}

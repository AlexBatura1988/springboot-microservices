package com.example.employeeservice.service.impl;

import com.example.employeeservice.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ORGANIZATION-SERVICE")
public interface OrganizationClient {
    @GetMapping("api/organizations/{organizationCode}")
    OrganizationDto getOrganizationByCode(@PathVariable String organizationCode);
}

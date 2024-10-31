package com.obify.rms.controller;

import com.obify.rms.dto.OrganizationDTO;
import com.obify.rms.service.OrganizationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/rms/api/v1/organization")
@Validated
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationDTO> addOrganization(@Valid @RequestBody OrganizationDTO organization){
        organization = organizationService.addOrganization(organization);
        return new ResponseEntity<>(organization, HttpStatus.CREATED);
    }
}

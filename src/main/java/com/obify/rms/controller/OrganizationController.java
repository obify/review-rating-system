package com.obify.rms.controller;

import com.obify.rms.entity.OrganizationEntity;
import com.obify.rms.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/organization")
public class OrganizationController {

    @Autowired
    private OrganizationRepository organizationRepository;

    @PostMapping
    public ResponseEntity<OrganizationEntity> addOrg(@RequestBody OrganizationEntity organization){
        organization = organizationRepository.save(organization);
        return new ResponseEntity<>(organization, HttpStatus.CREATED);
    }
}

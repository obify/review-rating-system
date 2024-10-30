package com.obify.rms.service.impl;

import com.obify.rms.dto.OrganizationDTO;
import com.obify.rms.entity.OrganizationEntity;
import com.obify.rms.repository.OrganizationRepository;
import com.obify.rms.service.OrganizationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDTO addOrganization(OrganizationDTO dto) {
        OrganizationEntity oe = new OrganizationEntity();
        BeanUtils.copyProperties(dto, oe);
        oe.setCreatedAt(LocalDate.now());
        oe = organizationRepository.save(oe);
        BeanUtils.copyProperties(oe, dto);
        return dto;
    }
}

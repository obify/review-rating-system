package com.obify.rms.service.impl;

import com.obify.rms.dto.ErrorDTO;
import com.obify.rms.dto.OrganizationDTO;
import com.obify.rms.entity.OrganizationEntity;
import com.obify.rms.exception.BusinessException;
import com.obify.rms.repository.OrganizationRepository;
import com.obify.rms.service.OrganizationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDTO addOrganization(OrganizationDTO dto) {
        Optional<OrganizationEntity> optOrg = organizationRepository.findByEmail(dto.getEmail());
        if(optOrg.isPresent()){
            throw new BusinessException(List.of(new ErrorDTO("DUP_001", "Email already exists")));
        }
        OrganizationEntity oe = new OrganizationEntity();
        BeanUtils.copyProperties(dto, oe);
        oe.setApiKey(UUID.randomUUID().toString());
        oe.setActive(true);
        oe.setCreatedAt(LocalDate.now());
        oe = organizationRepository.save(oe);
        BeanUtils.copyProperties(oe, dto);
        return dto;
    }
}

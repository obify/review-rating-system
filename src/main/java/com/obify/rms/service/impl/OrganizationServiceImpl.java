package com.obify.rms.service.impl;

import com.obify.rms.dto.OrganizationDTO;
import com.obify.rms.entity.OrganizationEntity;
import com.obify.rms.repository.OrganizationRepository;
import com.obify.rms.service.OrganizationService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@NoArgsConstructor
@Service
public class OrganizationServiceImpl implements OrganizationService {

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

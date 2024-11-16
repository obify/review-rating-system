package com.obify.rms.util;

import com.obify.rms.entity.OrganizationEntity;
import com.obify.rms.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CommonUtil {

    @Autowired
    private OrganizationRepository organizationRepository;

    public Optional<OrganizationEntity> getOrganization(String apiKey){
        return organizationRepository.findByApiKeyAndActiveTrue(apiKey);
    }
}

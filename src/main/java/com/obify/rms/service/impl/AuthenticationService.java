package com.obify.rms.service.impl;

import com.obify.rms.entity.OrganizationEntity;
import com.obify.rms.repository.OrganizationRepository;
import com.obify.rms.security.AuthenticationMapper;
import com.obify.rms.util.CommonUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private CommonUtil commonUtil;

    private static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY";
    private static final String AUTH_TOKEN = "aasaddfdf";

    public Authentication getAuthentication(HttpServletRequest request){
        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);
        if(request.getRequestURI() != null && !(
                request.getRequestURI().equals("/rms/api/v1/organization/register") ||
                        request.getRequestURI().contains("/v3/api-docs") ||
                        request.getRequestURI().contains("/swagger-ui")
                )){
            Optional<OrganizationEntity> orgOpt = commonUtil.getOrganization(apiKey);
            if(apiKey == null || orgOpt.isEmpty()){
                throw new BadCredentialsException("Invalid API KEY");
            }
        }
        return new AuthenticationMapper(apiKey, AuthorityUtils.NO_AUTHORITIES);
    }

}

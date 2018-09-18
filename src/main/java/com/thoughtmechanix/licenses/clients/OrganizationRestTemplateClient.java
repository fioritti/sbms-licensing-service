package com.thoughtmechanix.licenses.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

import com.thoughtmechanix.licenses.model.Organization;
import com.thoughtmechanix.licenses.utils.UserContextHolder;

@Component
public class OrganizationRestTemplateClient {
	
    private static final Logger logger = LoggerFactory.getLogger(OrganizationRestTemplateClient.class);
//	private static final String ORGANIZATION_SERVICE_URI = "http://zuulservice/api/organization/v1/organizations/{organizationId}";
	private static final String ORGANIZATION_SERVICE_URI = "http://localhost:5555/api/organization/v1/organizations/{organizationId}";
    
	
	@Autowired
	OAuth2RestTemplate restTemplate;
	
	public Organization getOrganization(String organizationId) {
        logger.debug(">>> In Licensing Service.getOrganization: {}. Thread Id: {}", UserContextHolder.getContext().getCorrelationId(), Thread.currentThread().getId());
		
		ResponseEntity<Organization> restExchange = restTemplate.exchange(
				ORGANIZATION_SERVICE_URI,
				HttpMethod.GET,
				null,
				Organization.class,
				organizationId);
		return restExchange.getBody();
	}
	
}

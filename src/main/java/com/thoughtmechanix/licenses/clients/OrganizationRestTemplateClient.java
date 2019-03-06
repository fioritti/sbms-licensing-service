package com.thoughtmechanix.licenses.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.thoughtmechanix.licenses.model.Organization;
import com.thoughtmechanix.licenses.repository.OrganizationRedisRepository;
import com.thoughtmechanix.licenses.utils.UserContextHolder;

@Component
public class OrganizationRestTemplateClient {
	
    private static final Logger logger = LoggerFactory.getLogger(OrganizationRestTemplateClient.class);
    
	private static final String ORGANIZATION_SERVICE_URI = "http://zuulserver:5555/api/organization/v1/organizations/{organizationId}";
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Tracer tracer;
	
    @Autowired
    OrganizationRedisRepository orgRedisRepo;
    
    
    private Organization checkRedisCache(String organizationId) {
    	Span newSpan = tracer.createSpan("readLicensingDataFromRedis");
        try {
            return orgRedisRepo.findOrganization(organizationId);
        }
        catch (Exception ex){
            logger.error("Error encountered while trying to retrieve organization {} check Redis Cache.  Exception {}", organizationId, ex);
            return null;
        }finally {
			newSpan.tag("peer.service", "redis");
			newSpan.logEvent(Span.CLIENT_RECV);
			tracer.close(newSpan);
		}
    }

    private void cacheOrganizationObject(Organization org) {
        try {
            orgRedisRepo.saveOrganization(org);
        }catch (Exception ex){
            logger.error("Unable to cache organization {} in Redis. Exception {}", org.getId(), ex);
        }
    }
    
	
	public Organization getOrganization(String organizationId) {
        logger.debug(">>> In Licensing Service.getOrganization: {}. Thread Id: {}", UserContextHolder.getContext().getCorrelationId(), Thread.currentThread().getId());
		
        Organization org = checkRedisCache(organizationId);

        if (org!=null){
            logger.debug("I have successfully retrieved an organization {} from the redis cache: {}", organizationId, org);
            return org;
        }

        logger.debug("Unable to locate organization from the redis cache: {}.", organizationId);
//        
        ResponseEntity<Organization> restExchange = restTemplate.exchange(
				ORGANIZATION_SERVICE_URI,
				HttpMethod.GET,
				null,
				Organization.class,
				organizationId);
        
        
        /*Save the record from cache*/
        org = restExchange.getBody();

        if (org!=null) {
            cacheOrganizationObject(org);
        }
        
		return org;
	}
	
}

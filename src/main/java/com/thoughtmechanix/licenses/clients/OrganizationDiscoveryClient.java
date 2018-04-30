package com.thoughtmechanix.licenses.clients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.thoughtmechanix.licenses.model.Organization;

@Component
public class OrganizationDiscoveryClient {
	
	private static final String ORGANIZATION_SERVICE_URI = "%s/v1/organizations/%s";
	private static final String ORGANIZATION_SERVICE_ADDRESS = "organizationservice";
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	public Organization getOrganization(String organizationId) {
		RestTemplate restTemplate = new RestTemplate();
		List<ServiceInstance> instances = discoveryClient.getInstances(ORGANIZATION_SERVICE_ADDRESS);
		
		if(instances.size()==0)return null;
		String serviceUri = String.format(ORGANIZATION_SERVICE_URI, instances.get(0).getUri().toString(),organizationId);
		
		ResponseEntity<Organization> restExchange = restTemplate.exchange(
				serviceUri,
				HttpMethod.GET,
				null,
				Organization.class,
				organizationId);
		
		return restExchange.getBody();
	}

}

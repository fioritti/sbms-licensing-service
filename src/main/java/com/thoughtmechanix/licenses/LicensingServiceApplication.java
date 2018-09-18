package com.thoughtmechanix.licenses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 *  @EnableDiscoveryClient é necesário somente
 *  quando usar a abordagem de discovery client.
 *  Não é necessário para quando usar o client ribbon-aware
 *
 */
//@EnableDiscoveryClient

/**
 * 
 * @EnableFeignClients Somente necessário qdo usar o feing client.
*	Não é necessário para quando usar o client ribbon-aware 
 *
 */
//@EnableFeignClients



@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableResourceServer
public class LicensingServiceApplication {

	/**
	 * @LoadBalanced diz ao spring-cloud para criar ribbon backed resttemplate
	 * 
	 * 
	 * @return
	 */
//	@LoadBalanced
//	@Bean
//	public RestTemplate getRestTemplate() {
//		RestTemplate template = new RestTemplate();
//		List<ClientHttpRequestInterceptor> interceptors = template.getInterceptors();
//		if(interceptors==null) {
//			template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
//		}else {
//			interceptors.add(new UserContextInterceptor());
//			template.setInterceptors(interceptors);
//		}
//		return template;
//	}
	
	  @Bean
	    public OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext, OAuth2ProtectedResourceDetails details) {
	        return new OAuth2RestTemplate(details, oauth2ClientContext);
	    }

	public static void main(String[] args) {
		SpringApplication.run(LicensingServiceApplication.class, args);
	}
}

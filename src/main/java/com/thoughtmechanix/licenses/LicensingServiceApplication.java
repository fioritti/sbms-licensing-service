package com.thoughtmechanix.licenses;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import com.thoughtmechanix.licenses.utils.UserContextInterceptor;

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
public class LicensingServiceApplication {

	/**
	 * @LoadBalanced diz ao spring-cloud para criar ribbon backed resttemplate
	 * 
	 * 
	 * @return
	 */
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate template = new RestTemplate();
		List<ClientHttpRequestInterceptor> interceptors = template.getInterceptors();
		if(interceptors==null) {
			template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
		}else {
			interceptors.add(new UserContextInterceptor());
			template.setInterceptors(interceptors);
		}
		return template;
	}

	public static void main(String[] args) {
		SpringApplication.run(LicensingServiceApplication.class, args);
	}
}

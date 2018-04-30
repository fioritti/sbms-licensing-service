package com.thoughtmechanix.licenses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
/**
 *  @EnableDiscoveryClient é necesário somente
 *  quando usar a abordagem de discovery client.
 *  Não é necessário para quando usar o client ribbon-aware
 *
 */
@EnableDiscoveryClient
/**
 * 
 * @EnableFeignClients Somente necessário qdo usar o feing client.
*	Não é necessário para quando usar o client ribbon-aware 
 *
 */
@EnableFeignClients
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
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(LicensingServiceApplication.class, args);
	}
}

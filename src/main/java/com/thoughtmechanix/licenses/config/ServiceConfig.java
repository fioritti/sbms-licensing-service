package com.thoughtmechanix.licenses.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class ServiceConfig {

	@Value("${signing.key}")
	private String jwtSigningKey = "";


	@Value("${redis.server}")
	private String redisServer = "";

	@Value("${redis.port}")
	private String redisPort = "";

	@Value("${example.property}")
	private String exampleProperty = "";

	public String getExampleProperty() {
		return exampleProperty;
	}

	public String getJwtSigningKey() {
		return jwtSigningKey;
	}

	public String getRedisPort() {
		return redisPort;
	}

	public String getRedisServer() {
		return redisServer;
	}

}

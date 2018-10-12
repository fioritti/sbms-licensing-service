package com.thoughtmechanix.licenses.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class ServiceConfig {

	@Value("${signing.key}")
	private String jwtSigningKey = "";

	public String getJwtSigningKey() {
		return jwtSigningKey;
	}

	@Value("${example.property}")
	private String exampleProperty = "";

	public String getExampleProperty() {
		return exampleProperty;
	}

}

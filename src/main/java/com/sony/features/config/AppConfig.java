package com.sony.features.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Value("${base.features.regions}")
	private String[] availableRegions;

	public String[] getAvailableRegions() {
		return availableRegions;
	}

	public void setAvailableRegions(String[] availableRegions) {
		this.availableRegions = availableRegions;
	}
	
	
}

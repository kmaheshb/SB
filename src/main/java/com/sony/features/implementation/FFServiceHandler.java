package com.sony.features.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sony.features.config.FeatureFlagConfig;
import com.sony.features.dto.FeatureFlag;
import com.sony.features.dto.FeatureFlags;
import com.sony.features.error.APIResponseErrorHandler;
import com.sony.features.model.IFFServiceHandler;

@Component
public class FFServiceHandler implements IFFServiceHandler {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	FeatureFlagConfig ffConfig;	

	@Override
	public FeatureFlags getAllFeatureFlags() {
		FeatureFlags allFlags = restTemplate.getForObject(ffConfig.getFfServiceBaseURL()+ffConfig.getFeatureFlagGet(), FeatureFlags.class);
		return allFlags;
	}

	@Override
	public FeatureFlags setFeatureFlag(FeatureFlag flagToSet) {
		restTemplate.setErrorHandler(new APIResponseErrorHandler());
		ResponseEntity<FeatureFlags> allFlags = restTemplate.postForEntity(ffConfig.getFfServiceBaseURL()+ffConfig.getFeatureFlagPost(), flagToSet, FeatureFlags.class);
		return allFlags.getBody();
	}
}

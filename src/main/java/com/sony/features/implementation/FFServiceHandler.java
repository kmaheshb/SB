package com.sony.features.implementation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sony.features.FeaturesController;
import com.sony.features.config.FeatureFlagConfig;
import com.sony.features.dto.FeatureFlag;
import com.sony.features.dto.FeatureFlags;
import com.sony.features.error.APIResponseErrorHandler;
import com.sony.features.model.IFFServiceHandler;

@Component
public class FFServiceHandler implements IFFServiceHandler {
	
	Logger logger = LogManager.getLogger(FFServiceHandler.class);

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	FeatureFlagConfig ffConfig;	

	@Override
	public FeatureFlags getAllFeatureFlags() {
		FeatureFlags allFlags = new FeatureFlags(); 
		List<FeatureFlag> asList = new ArrayList<>();
		String result = restTemplate.getForObject(ffConfig.getFfServiceBaseURL()+ffConfig.getFeatureFlagGet(), String.class);
		ObjectMapper mapper = new ObjectMapper();
		try {
			asList = mapper.readValue(result, new TypeReference<List<FeatureFlag>>() { });
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		allFlags.setListofFeatureFlags(asList);	
		return allFlags;
	}

	@Override
	public FeatureFlags setFeatureFlag(FeatureFlag flagToSet) {
		restTemplate.setErrorHandler(new APIResponseErrorHandler());
		FeatureFlags allFlags = new FeatureFlags();
		ResponseEntity<String> result = restTemplate.postForEntity(ffConfig.getFfServiceBaseURL()+ffConfig.getFeatureFlagPost(), flagToSet, String.class);
		
		List<FeatureFlag> asList = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			asList = mapper.readValue(result.getBody(), new TypeReference<List<FeatureFlag>>() { });
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		allFlags.setListofFeatureFlags(asList);	
		
		return allFlags;
	}
}

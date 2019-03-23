package com.sony.features.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeatureFlagConfig {

	@Value("${base.features.regions}")
	private String[] availableRegions;
	
	@Value("${api.ffservice.base.url}")
	private String ffServiceBaseURL;
	
	@Value("${api.ffservice.ff.get}")
	private String featureFlagGet;
	
	@Value("${api.ffservice.ff.post}")
	private String featureFlagPost;

	public String[] getAvailableRegions() {
		return availableRegions;
	}

	public void setAvailableRegions(String[] availableRegions) {
		this.availableRegions = availableRegions;
	}

	public String getFfServiceBaseURL() {
		return ffServiceBaseURL;
	}

	public void setFfServiceBaseURL(String ffServiceBaseURL) {
		this.ffServiceBaseURL = ffServiceBaseURL;
	}

	public String getFeatureFlagGet() {
		return featureFlagGet;
	}

	public void setFeatureFlagGet(String featureFlagGet) {
		this.featureFlagGet = featureFlagGet;
	}

	public String getFeatureFlagPost() {
		return featureFlagPost;
	}

	public void setFeatureFlagPost(String featureFlagPost) {
		this.featureFlagPost = featureFlagPost;
	}		
}

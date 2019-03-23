package com.sony.features.dto;

import java.util.List;

public class FeatureFlags {
	private List<FeatureFlag> listofFeatureFlags;

	public FeatureFlags() {
		
	}

	public FeatureFlags(List<FeatureFlag> listofFeatureFlags) {
		super();
		this.listofFeatureFlags = listofFeatureFlags;
	}

	public List<FeatureFlag> getListofFeatureFlags() {
		return listofFeatureFlags;
	}

	public void setListofFeatureFlags(List<FeatureFlag> listofFeatureFlags) {
		this.listofFeatureFlags = listofFeatureFlags;
	}

	@Override
	public String toString() {
		return "FeatureFlags [listofFeatureFlags=" + listofFeatureFlags + "]";
	}
	
}

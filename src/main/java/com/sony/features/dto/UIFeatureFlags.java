package com.sony.features.dto;

import java.util.List;

public class UIFeatureFlags {
	List<UIFeatureFlag> listOfFeatureFlags;
	
	public UIFeatureFlags() {
		
	}
	
	public UIFeatureFlags(List<UIFeatureFlag> listOfFeatureFlags) {
		super();
		this.listOfFeatureFlags = listOfFeatureFlags;
	}

	public List<UIFeatureFlag> getListOfFeatures() {
		return listOfFeatureFlags;
	}

	public void setListOfFeatures(List<UIFeatureFlag> listOfFeatureFlags) {
		this.listOfFeatureFlags = listOfFeatureFlags;
	}

	@Override
	public String toString() {
		return "UIFeatureFlags [listOfFeatureFlags=" + listOfFeatureFlags + "]";
	}
	
}

package com.sony.features.dto;

import java.util.List;

public class UIFeature {
	private String name;
	private String displayName;
	private List<UIFeatureFlag> value;
	
	public UIFeature() {		
	}
	
	public UIFeature(String name, String displayName, List<UIFeatureFlag> value) {
		super();
		this.name = name;
		this.displayName = displayName;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public List<UIFeatureFlag> getValue() {
		return value;
	}

	public void setValue(List<UIFeatureFlag> value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "UIFeature [name=" + name + ", displayName=" + displayName + ", value=" + value + "]";
	}
}

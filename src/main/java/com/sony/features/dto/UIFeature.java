package com.sony.features.dto;

public class UIFeature {
	private String name;
	private UIFeatureFlags value;
	
	public UIFeature() {		
	}
	
	public UIFeature(String name, UIFeatureFlags value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UIFeatureFlags getValue() {
		return value;
	}

	public void setValue(UIFeatureFlags value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "UIFeature [name=" + name + ", value=" + value + "]";
	}
}

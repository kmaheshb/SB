package com.sony.features.dto;

public class Feature {
	String name;
	boolean isActive;
	
	public Feature() {
		
	}
	
	public Feature(String name, boolean isActive) {
		super();
		this.name = name;
		this.isActive = isActive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Feature [name=" + name + ", isActive=" + isActive + "]";
	}	
	
}

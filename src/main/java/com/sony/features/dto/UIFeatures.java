package com.sony.features.dto;

import java.util.List;

public class UIFeatures {
	List<UIFeature> listOfFeatures;

	public UIFeatures() {
		
	}
	
	public UIFeatures(List<UIFeature> listOfFeatures) {
		super();
		this.listOfFeatures = listOfFeatures;
	}

	public List<UIFeature> getListOfFeatures() {
		return listOfFeatures;
	}

	public void setListOfFeatures(List<UIFeature> listOfFeatures) {
		this.listOfFeatures = listOfFeatures;
	}

	@Override
	public String toString() {
		return "UIFeatures [listOfFeatures=" + listOfFeatures + "]";
	}	
}

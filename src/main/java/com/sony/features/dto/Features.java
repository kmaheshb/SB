package com.sony.features.dto;

import java.util.List;

public class Features {
	List<Feature> listOfFeatures;
	
	public Features() {
		
	}
	
	public Features(List<Feature> listOfFeatures) {
		super();
		this.listOfFeatures = listOfFeatures;
	}

	public List<Feature> getListOfFeatures() {
		return listOfFeatures;
	}

	public void setListOfFeatures(List<Feature> listOfFeatures) {
		this.listOfFeatures = listOfFeatures;
	}

	@Override
	public String toString() {
		return "Features [listOfFeatures=" + listOfFeatures + "]";
	}
	
}

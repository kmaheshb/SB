package com.sony.features.implementation;

import org.springframework.stereotype.Component;

import com.sony.features.model.IFeatureHandler;

@Component
public class FeatureHandler implements IFeatureHandler {

	@Override
	public int selectRegion(int val, int position) {
		
		int x = 1;
		int i=0;
		while(i<position) {
			x <<=1;
			i++;
		}
		return (int)(val ^ x);
	}

	@Override
	public int deselectRegion(int val, int position) {
		int x = 1;
		int i=0;
		while(i<position) {
			x <<=1;
			i++;
		}
		return (int)(val & (~x));
	}
	
	

}

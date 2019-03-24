package com.sony.features.implementation;

import org.springframework.stereotype.Component;

import com.sony.features.model.IFeatureHandler;

@Component
public class FeatureHandler implements IFeatureHandler {

	/**
	 * This function sets the bit value when selected
	 */
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

	/**
	 * This function negates the set bit value when unselected
	 */
	@Override
	public int unselectRegion(int val, int position) {
		int x = 1;
		int i=0;
		while(i<position) {
			x <<=1;
			i++;
		}
		return (int)(val & (~x));
	}
	
	

}

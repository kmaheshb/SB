package com.sony.features.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sony.features.config.FeatureFlagConfig;
import com.sony.features.dto.FeatureFlag;
import com.sony.features.dto.FeatureFlags;
import com.sony.features.dto.UIFeature;
import com.sony.features.dto.UIFeatureFlag;
import com.sony.features.dto.UIFeatures;

@Component
public class UIConversionUtil {
	
	@Autowired
	FeatureFlagConfig ffConfig;
	
	public UIFeatures setFlagsToUIDataModel(FeatureFlags allFlags) {
		
		UIFeatures result = new UIFeatures();
		
		List<UIFeature> uiFeatures = allFlags.getListofFeatureFlags().stream().map(feature -> 
			new UIFeature(feature.getName(), setFeaturesFromInteger(feature.getValue()))
		).collect(Collectors.toList());
		
		result.setListOfFeatures(uiFeatures);
		return result;
		
	}
	
	public List<UIFeatureFlag> setFeaturesFromInteger(int val) {
    	UIFeatureFlag currentFeature;
        List<UIFeatureFlag> listFeatures = new ArrayList<>();
        Stack<UIFeatureFlag> reverseOrderedFeatures = new Stack<>();
        
        
        String flags = Integer.toBinaryString(val);
        int j= flags.length()-1;
        
        String[] regions = ffConfig.getAvailableRegions();
        
        for (int i=regions.length-1; i>=0; i--) {
        	boolean isActive = false;
        	if (j>= 0 && flags.charAt(j) == '1') {
        		isActive = true;
        	}
        		
        	currentFeature = new UIFeatureFlag(regions[i], isActive, regions.length - 1 - i);
        	reverseOrderedFeatures.push(currentFeature);
        	j--;
        }
        
        while(!reverseOrderedFeatures.isEmpty()) {
        	listFeatures.add(reverseOrderedFeatures.pop());
        }
        
        return listFeatures;
    }
	
	public FeatureFlag setFeatureFromUIModel(UIFeature input) {
		return new FeatureFlag(input.getName(), setIntegerFromFeatures(input.getValue()));
	}
	
	/**
	 * This function takes the feature flags provided by the UI 
	 * and returns the corresponding integer value 
	 * @param flags List of feature flags (region and booleans) from the UI
	 * @return		An Integer value
	 */
	public int setIntegerFromFeatures(List<UIFeatureFlag> flags) {
    	
    	List<UIFeatureFlag> sortedListOfFeatures = sortFeaturesByRegion(flags, ffConfig.getAvailableRegions());
    	
    	StringBuilder sb = new StringBuilder();
    	//use stream map
    	for(UIFeatureFlag flag : sortedListOfFeatures) {
    		if (flag.isActive())
    			sb.append('1');
    		else
    			sb.append('0');
    	}
    	return Integer.parseInt(sb.toString(), 2);
    }
    
	/**
	 * 
	 * @param listOfFeatures
	 * @param regions
	 * @return
	 */
    public List<UIFeatureFlag> sortFeaturesByRegion(List<UIFeatureFlag> listOfFeatures, String[] regions) {
    	//use stream.comparator.then.then
    	Collections.sort(listOfFeatures, new Comparator<UIFeatureFlag>() {
    		@Override
    		public int compare(UIFeatureFlag f1, UIFeatureFlag f2) {
    			return Integer.compare(Arrays.asList(regions).indexOf(f1.getRegion()), Arrays.asList(regions).indexOf(f2.getRegion()));
    		}
    	});
    	return listOfFeatures;
    }

}

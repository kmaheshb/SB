package com.sony.features.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
	
	/**
	 * Ideally, we need UI display name from source (FF Service) or persisted data source (eg. Database)
	 * @return HashMap consisting names specified in FF service and corresponding display names
	 */
	@Bean
	public Map<String, String> featureNameMap(){
	    Map<String, String>  nameMap = new HashMap<>();
	    nameMap.put("useAwesomeGames", "Use Awesome Games");
	    nameMap.put("useNewFeature", "Use New Feature");
	    nameMap.put("Identity_Information", "Identity Information");
	    return nameMap;
	}
	
	/**
	 * This functions takes list of all features and their values in Integer format and 
	 * transforms them to the objects as required by the UI
	 * 
	 * @param allFlags All feature flags
	 * @return
	 */
	public UIFeatures setFlagsToUIDataModel(FeatureFlags allFlags) {
		
		UIFeatures result = new UIFeatures();
		
		List<UIFeature> uiFeatures = allFlags.getListofFeatureFlags().stream().map(feature -> 
			new UIFeature(feature.getName(), featureNameMap().get(feature.getName()), setFeaturesFromInteger(feature.getValue()))
		).collect(Collectors.toList());
		
		result.setListOfFeatures(uiFeatures);
		return result;
		
	}
	
	/**
	 * This function takes an integer and sets the boolean values per region
	 * as specified in the configuration
	 * 
	 * Ideally we need to get the list of regions and its order from a 
	 * persisted data source
	 * 
	 * @param val Integer value
	 * @return
	 */
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
	
	/**
	 * This functions transforms the value coming from UI which is in booleans to an 
	 * Integer which is being stored in the FF service 
	 * 
	 * @param input Feature name and value in booleans from the UI
	 * @return
	 */
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
	 * This function sorts the feature flags coming from the UI in the order of 
	 * regions specified in the configuration, since we cannot trust the order of flags
	 * coming from the UI
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

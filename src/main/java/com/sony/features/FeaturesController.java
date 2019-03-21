package com.sony.features;

import org.springframework.web.bind.annotation.RestController;

import com.sony.features.config.AppConfig;
import com.sony.features.dto.Feature;
import com.sony.features.dto.Features;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class FeaturesController {
	
	@Autowired
	AppConfig appConfig;
    
    @RequestMapping("/getFeatures")
    public Features index() {
        
        int val = 3;
        return setFeaturesFromInteger(val);
    }
    
    public Features setFeaturesFromInteger(int val) {
    	Feature currentFeature;
        List<Feature> listFeatures = new ArrayList<>();
        Stack<Feature> reverseOrderedFeatures = new Stack<>();
        Features result;
        
        String flags = Integer.toBinaryString(val);
        int j= flags.length()-1;
        
        String[] regions = appConfig.getAvailableRegions();
        
        for (int i=regions.length-1; i>=0; i--) {
        	boolean isActive = false;
        	if (j>= 0 && flags.charAt(j) == '1') {
        		isActive = true;
        	}
        		
        	currentFeature = new Feature(regions[i], isActive);
        	reverseOrderedFeatures.push(currentFeature);
        	j--;
        }
        
        while(!reverseOrderedFeatures.isEmpty()) {
        	listFeatures.add(reverseOrderedFeatures.pop());
        }
        result = new Features(listFeatures);
        return result;
    }
    
    public int setIntegerFromFeatures(Features flags) {
    	
    	List<Feature> sortedListOfFeatures = sortFeaturesByRegion(flags.getListOfFeatures(), appConfig.getAvailableRegions());
    	
    	StringBuilder sb = new StringBuilder();
    	
    	for(Feature flag : sortedListOfFeatures) {
    		if (flag.isActive())
    			sb.append('1');
    		else
    			sb.append('0');
    	}
    	return Integer.parseInt(sb.toString(), 2);
    }
    
    public List<Feature> sortFeaturesByRegion(List<Feature> listOfFeatures, String[] regions) {
    	Collections.sort(listOfFeatures, new Comparator<Feature>() {
    		@Override
    		public int compare(Feature f1, Feature f2) {
    			return Integer.compare(Arrays.asList(regions).indexOf(f1.getName()), Arrays.asList(regions).indexOf(f2.getName()));
    		}
    	});
    	return listOfFeatures;
    }
    
}

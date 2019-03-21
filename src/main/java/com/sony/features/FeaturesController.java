package com.sony.features;

import org.springframework.web.bind.annotation.RestController;

import com.sony.features.config.AppConfig;
import com.sony.features.dto.Feature;
import com.sony.features.dto.Features;

import java.util.ArrayList;
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
    	StringBuilder sb = new StringBuilder();
    	
    	for(Feature flag : flags.getListOfFeatures()) {
    		
    	}
    	return 0;
    }
    
}

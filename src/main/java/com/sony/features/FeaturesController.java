package com.sony.features;

import org.springframework.web.bind.annotation.RestController;

import com.sony.features.config.FeatureFlagConfig;
import com.sony.features.dto.FeatureFlags;
import com.sony.features.dto.UIFeatureFlag;
import com.sony.features.dto.UIFeatureFlags;
import com.sony.features.implementation.FFServiceHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/v1")
public class FeaturesController {
	
	Logger logger = LogManager.getLogger(FeaturesController.class);
	
	@Autowired
	FeatureFlagConfig ffConfig;
	
	@Autowired
	FFServiceHandler ffServiceHandler;
     
    @RequestMapping(value = "/features", method = {RequestMethod.GET})
    public UIFeatureFlags index() {
        logger.debug("Request Received: Fetch All Feature Flags");
        
        FeatureFlags allFeatures = ffServiceHandler.getAllFeatureFlags();
        
        int val = 3;
        return setFeaturesFromInteger(val);
    }
    
    @RequestMapping(value = "/feature", method = {RequestMethod.POST})
    public @ResponseBody UIFeatureFlags setFeatures(UIFeatureFlags input) {
		return input;
    	
    }
    /**
     * 
     * @param val
     * @return
     */
    public UIFeatureFlags setFeaturesFromInteger(int val) {
    	UIFeatureFlag currentFeature;
        List<UIFeatureFlag> listFeatures = new ArrayList<>();
        Stack<UIFeatureFlag> reverseOrderedFeatures = new Stack<>();
        UIFeatureFlags result;
        
        String flags = Integer.toBinaryString(val);
        int j= flags.length()-1;
        
        String[] regions = ffConfig.getAvailableRegions();
        
        for (int i=regions.length-1; i>=0; i--) {
        	boolean isActive = false;
        	if (j>= 0 && flags.charAt(j) == '1') {
        		isActive = true;
        	}
        		
        	currentFeature = new UIFeatureFlag(regions[i], isActive);
        	reverseOrderedFeatures.push(currentFeature);
        	j--;
        }
        
        while(!reverseOrderedFeatures.isEmpty()) {
        	listFeatures.add(reverseOrderedFeatures.pop());
        }
        result = new UIFeatureFlags(listFeatures);
        return result;
    }
    
    public int setIntegerFromFeatures(UIFeatureFlags flags) {
    	
    	List<UIFeatureFlag> sortedListOfFeatures = sortFeaturesByRegion(flags.getListOfFeatures(), ffConfig.getAvailableRegions());
    	
    	StringBuilder sb = new StringBuilder();
    	//uset stream map
    	for(UIFeatureFlag flag : sortedListOfFeatures) {
    		if (flag.isActive())
    			sb.append('1');
    		else
    			sb.append('0');
    	}
    	return Integer.parseInt(sb.toString(), 2);
    }
    
    public List<UIFeatureFlag> sortFeaturesByRegion(List<UIFeatureFlag> listOfFeatures, String[] regions) {
    	//user stream.ciomparator.then.then
    	Collections.sort(listOfFeatures, new Comparator<UIFeatureFlag>() {
    		@Override
    		public int compare(UIFeatureFlag f1, UIFeatureFlag f2) {
    			return Integer.compare(Arrays.asList(regions).indexOf(f1.getName()), Arrays.asList(regions).indexOf(f2.getName()));
    		}
    	});
    	return listOfFeatures;
    }
    
}

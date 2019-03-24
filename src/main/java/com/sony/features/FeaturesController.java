package com.sony.features;

import org.springframework.web.bind.annotation.RestController;

import com.sony.features.config.FeatureFlagConfig;
import com.sony.features.dto.FeatureFlag;
import com.sony.features.dto.FeatureFlags;
import com.sony.features.dto.UIFeature;
import com.sony.features.dto.UIFeatures;
import com.sony.features.implementation.FFServiceHandler;
import com.sony.features.util.UIConversionUtil;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@Autowired
	UIConversionUtil uiConversionUtil;
     
	@RequestMapping(value = "/features", method = {RequestMethod.GET})
    public UIFeatures index() {
        logger.debug("Request Received: Fetch All Feature Flags");
        
        FeatureFlags allFeatures = ffServiceHandler.getAllFeatureFlags();        
        
        return uiConversionUtil.setFlagsToUIDataModel(allFeatures);
    }
    
	@RequestMapping(value = "/feature", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody UIFeatures setFeature(@RequestBody UIFeature input) {
		logger.debug("Request Received: Save A Feature Flag");
        return saveFeature(input);
    	
    }
	
	private UIFeatures saveFeature(UIFeature input) {
		FeatureFlag updatedFlag = uiConversionUtil.setFeatureFromUIModel(input);
    	FeatureFlags allFeatures = ffServiceHandler.setFeatureFlag(updatedFlag);
        return uiConversionUtil.setFlagsToUIDataModel(allFeatures);
	}
    
}

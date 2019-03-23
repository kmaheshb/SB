package com.sony.features.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sony.features.FeatureFlagApplication;
import com.sony.features.FeaturesController;
import com.sony.features.dto.UIFeatureFlag;
import com.sony.features.dto.UIFeatureFlags;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {FeatureFlagApplication.class, FeaturesController.class })
public class FeaturesControllerTest {
	
	@Autowired
	FeaturesController featuresController;
	
	List<UIFeatureFlag> listOfFeatures;
	
	String[] regions = {"Asia","Korea","Europe","Japan","America"};
	
	@Before
	public void setup() {
		listOfFeatures = new ArrayList<>();
		//Asia,Korea,Europe,Japan,America
		UIFeatureFlag a = new UIFeatureFlag("Japan", false);
		listOfFeatures.add(a);
		
		a = new UIFeatureFlag("Asia", false);
		listOfFeatures.add(a);
		a = new UIFeatureFlag("America", true);
		listOfFeatures.add(a);
		a = new UIFeatureFlag("Europe", false);
		listOfFeatures.add(a);
		a = new UIFeatureFlag("Korea", false);
		listOfFeatures.add(a);
	}

	@Test
	public void sortFeaturesByRegion() {
		
		List<UIFeatureFlag> expectedResult = new ArrayList<>();
		UIFeatureFlag a = new UIFeatureFlag("Asia", false);
		expectedResult.add(a);
		a = new UIFeatureFlag("Korea", false);
		expectedResult.add(a);
		a = new UIFeatureFlag("Europe", false);
		expectedResult.add(a);
		a = new UIFeatureFlag("Japan", false);
		expectedResult.add(a);	
		a = new UIFeatureFlag("America", true);
		expectedResult.add(a);
		
		List<UIFeatureFlag> result = featuresController.sortFeaturesByRegion(listOfFeatures, regions);
		assertEquals(expectedResult, result);
		System.out.println(result);
	}
	
	@Test
	public void integerFromFeatures() {
		UIFeatureFlags flag = new UIFeatureFlags(listOfFeatures);
		int result = featuresController.setIntegerFromFeatures(flag);
		assertEquals(1, result);
	}

}

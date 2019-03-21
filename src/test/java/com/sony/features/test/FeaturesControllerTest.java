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

import com.sony.features.Application;
import com.sony.features.FeaturesController;
import com.sony.features.dto.Feature;
import com.sony.features.dto.Features;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class, FeaturesController.class })
public class FeaturesControllerTest {
	
	@Autowired
	FeaturesController featuresController;
	
	List<Feature> listOfFeatures;
	
	String[] regions = {"Asia","Korea","Europe","Japan","America"};
	
	@Before
	public void setup() {
		listOfFeatures = new ArrayList<>();
		//Asia,Korea,Europe,Japan,America
		Feature a = new Feature("Japan", false);
		listOfFeatures.add(a);
		
		a = new Feature("Asia", false);
		listOfFeatures.add(a);
		a = new Feature("America", true);
		listOfFeatures.add(a);
		a = new Feature("Europe", false);
		listOfFeatures.add(a);
		a = new Feature("Korea", false);
		listOfFeatures.add(a);
	}

	@Test
	public void sortFeaturesByRegion() {
		
		List<Feature> expectedResult = new ArrayList<>();
		Feature a = new Feature("Asia", false);
		expectedResult.add(a);
		a = new Feature("Korea", false);
		expectedResult.add(a);
		a = new Feature("Europe", false);
		expectedResult.add(a);
		a = new Feature("Japan", false);
		expectedResult.add(a);	
		a = new Feature("America", true);
		expectedResult.add(a);
		
		List<Feature> result = featuresController.sortFeaturesByRegion(listOfFeatures, regions);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void integerFromFeatures() {
		Features flag = new Features(listOfFeatures);
		int result = featuresController.setIntegerFromFeatures(flag);
		assertEquals(1, result);
	}

}

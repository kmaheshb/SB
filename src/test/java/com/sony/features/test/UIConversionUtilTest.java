package com.sony.features.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sony.features.dto.FeatureFlag;
import com.sony.features.dto.FeatureFlags;
import com.sony.features.dto.UIFeatureFlag;
import com.sony.features.dto.UIFeatures;
import com.sony.features.util.UIConversionUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UIConversionUtilTest {
	
	@Autowired
	UIConversionUtil uiConversionUtil;
	
	List<UIFeatureFlag> listOfFeatures;
	List<FeatureFlag> listofFeatureFlag;
	
	String[] regions = {"Asia","Korea","Europe","Japan","America"};
	
	@Before
	public void setup() {
		listOfFeatures = new ArrayList<>();
		//Asia,Korea,Europe,Japan,America
		UIFeatureFlag a = new UIFeatureFlag("Asia", false, 4);
		listOfFeatures.add(a);
		a = new UIFeatureFlag("Korea", false, 3);
		listOfFeatures.add(a);		
		a = new UIFeatureFlag("Europe", false, 2);
		listOfFeatures.add(a);
		a = new UIFeatureFlag("Japan", false, 1);
		listOfFeatures.add(a);
		a = new UIFeatureFlag("America", true, 0);
		listOfFeatures.add(a);
		
		listofFeatureFlag = new ArrayList<>();
		
		FeatureFlag b = new FeatureFlag("Feature A", 1);
		listofFeatureFlag.add(b);
		b = new FeatureFlag("Feature B", 3);
		listofFeatureFlag.add(b);
		
		
	}

	@Test
	public void testSetFeaturesFromInteger() {
		
		List<UIFeatureFlag> actual = uiConversionUtil.setFeaturesFromInteger(1);
		
		assertEquals(listOfFeatures, actual);
	}

	@Test
	public void testSetFlagsToUIDataModel() {
		FeatureFlags input = new FeatureFlags(listofFeatureFlag);
		UIFeatures actual = uiConversionUtil.setFlagsToUIDataModel(input);
		System.out.println(actual);
	}
}

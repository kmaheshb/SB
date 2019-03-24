package com.sony.features.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.sony.features.implementation.FeatureHandler;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FeatureHandlerTest {
	
	@Autowired
	FeatureHandler featureHandler;

	@Test
	public void testSelectRegion() {
		int result = featureHandler.selectRegion(3, 2);
		assertEquals(7, result);
	}

	@Test
	public void testDeselectRegion() {
		int result = featureHandler.unselectRegion(7, 2);
		assertEquals(3, result);
	}
}

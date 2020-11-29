package com.windmillsmartsolutions.app;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.windmillsmartsolutions.controller.v1.api.StringOperationsController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StringoperationsApplicationTests {

	@Autowired
	private StringOperationsController controller;

	@Test
	void contextLoads() {

		//assert controller object is available
		assertNotNull(controller, "Controller Object is Not initialized");
		
		//assert the method returns longest string
		Map<String, List<String>> input = new HashMap<String, List<String>>();		
		String data[] = {"This is short String", "This is another short String", "This is the longest String in array", "Too short"};
		input.put("data", Arrays.asList(data));

		assertTrue("This is the longest String in array".equals(controller.getLongesString(input)), "This is not the longest String");

		//assert that the method returns a list by removing string of length > 10
		Map<String, List<String>> input1 = new HashMap<String, List<String>>();		
		String data1[] = {"123456789", "123", "7895", "24546456465864", "75542", "546462132133"};
		input1.put("data", Arrays.asList(data1));

		String expected[] = {"123456789", "123", "7895", "75542"};
		
		assertLinesMatch(Arrays.asList(expected), controller.deleteStrings(input1), "List contains strings more than 10 chars");
	}

}

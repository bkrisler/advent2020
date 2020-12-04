package com.bkds.advent2020.day1;

import java.io.InputStream;

public class DayBase {
	protected InputStream getFileAsResource(String filename) {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream stream = classLoader.getResourceAsStream(filename);
		if(stream == null) {
			throw new IllegalArgumentException("Unknown file: " + filename);
		} 
		
		return stream;		
	}	
}

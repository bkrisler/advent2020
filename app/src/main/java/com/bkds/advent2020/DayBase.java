package com.bkds.advent2020;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public abstract class DayBase {
	
	public abstract void store(String input);
	
	protected void readData(String day, String person) {
		System.out.println("Reading in data for: " + person);
		InputStream is = this.getFileAsResource("data/" + day + "/" + person + ".txt");
		try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
				BufferedReader reader = new BufferedReader(streamReader)) {

			String line;
			while ((line = reader.readLine()) != null) {
				store(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private InputStream getFileAsResource(String filename) {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream stream = classLoader.getResourceAsStream(filename);
		if(stream == null) {
			throw new IllegalArgumentException("Unknown file: " + filename);
		} 
		
		return stream;		
	}	
}

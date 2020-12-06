package com.bkds.advent2020.day6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bkds.advent2020.DayBase;

public class Day6 extends DayBase {

	private List<List<String>> personGroups = new ArrayList<>();
		
	public Day6() {
		readDataMultiRow("day6", "brian");
		//dumpData();
	}

	private void dumpData() {
		for(List<String> group : personGroups) {
			System.out.println(group);
		}
	}
	
	@Override
	public void store(String input) {
		// Not used for this problem		
	}

	protected void readDataMultiRow(String day, String person) {
		System.out.println("Reading in data for: " + person + " and " + day);
		List<String> group = new ArrayList<>();
		InputStream is = this.getFileAsResource("data/" + day + "_" + person + ".txt");
		try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
				BufferedReader reader = new BufferedReader(streamReader)) {
			String line;
			while ((line = reader.readLine()) != null) {
				if(!line.isBlank()) {
					group.add(line);
				} else {
					List<String> record = new ArrayList<>(group);
					this.personGroups.add(record);
					group.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}

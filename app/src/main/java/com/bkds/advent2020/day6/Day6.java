package com.bkds.advent2020.day6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bkds.advent2020.DayBase;

public class Day6 extends DayBase {

	private List<List<String>> personGroups = new ArrayList<>();

	public Day6() {
		readDataMultiRow("day6", "brian");
		// dumpData();
	}

	private void dumpData() {
		for (List<String> group : personGroups) {
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
				if (!line.isBlank()) {
					group.add(line);
				} else {
					List<String> record = new ArrayList<>(group);
					this.personGroups.add(record);
					group.clear();
				}
			}
			// Add the final record
			List<String> record = new ArrayList<>(group);
			this.personGroups.add(record);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void brianSolution() {
		int sum = 0;
		List<String> map = new ArrayList<>();
		for (List<String> group : personGroups) {
			for (String person : group) {
				String[] parts = person.split("");
				for (String p : parts) {
					if (!map.contains(p)) {
						map.add(p);
					}
				}
			}
			sum += map.size();
			Collections.sort(map);
			System.out.println("Group yes: " + map.size() + " --> " + map);
			map.clear();
		}
		System.out.println("All: " + sum);
	}

	protected void brianPartTwo() {
		int groupSum = 0;
		Map<String, Integer> counter = new HashMap<>();
		for (List<String> group : personGroups) {
			for (String person : group) {
				String[] parts = person.split("");
				for (String p : parts) {
					if (counter.containsKey(p)) {
						counter.put(p, counter.get(p) + 1);
					} else {
						counter.put(p,  1);
					}
				}
			}
			//System.out.println("Total People: " + group.size());
			int groupCount = 0;
			for(Integer c : counter.values()) {
				if(c == group.size()) {
					groupCount++;
				}
			}
			System.out.println("Group Count: " + groupCount);
			groupSum += groupCount;
			//System.out.println(counter);
			counter.clear();
		}
		System.out.println("Final: " + groupSum);
	}

	public static void main(String[] args) {
		Day6 d6 = new Day6();
		// d6.brianSolution();
		d6.brianPartTwo();
	}

}

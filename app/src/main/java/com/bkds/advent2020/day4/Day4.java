package com.bkds.advent2020.day4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bkds.advent2020.DayBase;

public class Day4 extends DayBase {

	private List<Map<String, String>> passports = new ArrayList<>();
	
	public Day4() {
		readDataMultiRow("day4", "brian");
		dumpPassports();
	}

	private void dumpPassports() {
		for(Map<String,String> passport : passports) {
			System.out.println(passport);
		}
	}
	
	@Override
	public void store(String input) {
		Map<String, String> eMap = new HashMap<>();
		String[] fields = input.split(" ");
		for(String field : fields) {
			String[] fieldParts = field.split(":");
			eMap.put(fieldParts[0], fieldParts[1]);
		}
		passports.add(eMap);
	}

	public static void main(String[] args) {
		Day4 d4 = new Day4();
	}
}

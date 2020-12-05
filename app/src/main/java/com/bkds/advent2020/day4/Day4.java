package com.bkds.advent2020.day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bkds.advent2020.DayBase;

public class Day4 extends DayBase {

	private List<Map<String, String>> passports = new ArrayList<>();

	public Day4() {
		readDataMultiRow("day4", "brian");
		System.out.println("Passports: " + passports.size());
		// dumpPassports();
	}

	private void dumpPassports() {
		for (Map<String, String> passport : passports) {
			System.out.println(passport);
		}
	}

	@Override
	public void store(String input) {
		Map<String, String> eMap = new HashMap<>();
		String[] fields = input.split(" ");
		for (String field : fields) {
			String[] fieldParts = field.split(":");
			eMap.put(fieldParts[0], fieldParts[1]);
		}
		passports.add(eMap);
	}

	private List<String> requiredFieldsAll() {
		List<String> required = new ArrayList<>();
		required.add("byr");
		required.add("iyr");
		required.add("eyr");
		required.add("hgt");
		required.add("hcl");
		required.add("ecl");
		required.add("pid");
		required.add("cid");
		Collections.sort(required);
		return required;
	}

	public void bPartOne() {
		int valid = 0;
		for (Map<String, String> passport : passports) {
			List<String> keys = new ArrayList<String>(passport.keySet());
			List<String> diff = new ArrayList<String>(requiredFieldsAll());
			diff.removeAll(keys);
			if ((diff.size() == 0) || (diff.size() == 1 && diff.contains("cid"))) {
				valid++;
			} 
		}
		System.out.println("Number of valid passports: " + valid);
	}
	
	public void bPartTwo() {
	int valid = 0;
	for (Map<String, String> passport : passports) {
		List<String> keys = new ArrayList<String>(passport.keySet());
		List<String> diff = new ArrayList<String>(requiredFieldsAll());
		diff.removeAll(keys);
		if ((diff.size() == 0) || (diff.size() == 1 && diff.contains("cid"))) {
			if(valid(passport)) {
				valid++;
			}
		} 
	}
	System.out.println("Number of valid passports: " + valid);
}

	private boolean valid(Map<String, String> passport) {
		boolean isByr = false;
		boolean isIyr = false;
		boolean isEyr = false;
		boolean isHgt = false;
		boolean isHcl = false;
		boolean isEcl = false;
		boolean isPid = false;
		
		Integer byr = Integer.valueOf(passport.get("byr"));
		if(byr >= 1920 && byr <= 2002) {
			isByr = true;
		} else {
			isByr = false;
		}

		Integer iyr = Integer.valueOf(passport.get("iyr"));
		if(iyr >= 2010 && iyr <= 2020) {
			isIyr = true;
		} else {
			isIyr = false;
		}

		Integer eyr = Integer.valueOf(passport.get("eyr"));
		if(eyr >= 2020 && eyr <= 2030) {
			isEyr = true;
		} else {
			isEyr = false;
		}

		if(passport.get("pid").length() == 9) {
			try {
				Integer.valueOf(passport.get("pid"));
				isPid = true;
			} catch (NumberFormatException e) {
				isPid = false;
			}
		} else {
			isPid = false;
		}
		
		String hcl = passport.get("hcl");
		if(hcl.startsWith("#") && hcl.length() == 7) {
			List<String> req = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f");
			String[] parts = hcl.substring(1).split("");
			isHcl = true;
			for(String p : parts) {
				if(!req.contains(p)) {
					isHcl = false;
					break;
				}
			}
		} else {
			isHcl = false;
		}

		String ecl = passport.get("ecl");
		if(ecl.equals("amb") || ecl.equals("blu") || ecl.equals("brn")|| 
				ecl.equals("gry")|| ecl.equals("grn")|| ecl.equals("hzl")|| ecl.equals("oth")) {
			isEcl = true;
		} else {
			isEcl = false;
		}
		
		String hgt = passport.get("hgt");
		if(hgt.endsWith("cm")) {
			Integer h = Integer.valueOf(hgt.substring(0, hgt.length() - 2));
			if(h >= 150 && h <= 193) {
				isHgt = true;
			} else {
				isHgt = false;
			}
		} else if(hgt.endsWith("in")) {
			Integer h = Integer.valueOf(hgt.substring(0, hgt.length() - 2));
			if(h >= 59 && h <= 76) {
				isHgt = true;
			} else {
				isHgt = false;
			}
		} else {
			isHgt = false;
		}
		return isByr && isIyr && isEyr && isPid && isHcl && isEcl && isHgt;
	}

	public static void main(String[] args) {
		Day4 d4 = new Day4();
		d4.bPartOne();
		d4.bPartTwo();		
	}
}

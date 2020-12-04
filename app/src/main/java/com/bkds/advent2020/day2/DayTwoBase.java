package com.bkds.advent2020.day2;

import java.util.ArrayList;
import java.util.List;

import com.bkds.advent2020.DayBase;

public class DayTwoBase extends DayBase {

	protected List<PasswordPolicy> entries = new ArrayList<>();
	
	@Override
	public void store(String input) {
		entries.add(PasswordPolicy.fromInput(input));
	}

	public void dumpData() {
		for(PasswordPolicy entry : entries) {
			System.out.println(entry);
		}
	}

}

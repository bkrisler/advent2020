package com.bkds.advent2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.bkds.advent2020.model.PasswordPolicy;

public class DayTwoBase extends DayBase {

	protected List<String> rawData = new ArrayList<>();
	protected List<PasswordPolicy> entries = new ArrayList<>();
	
	public void readData(String person) {
		System.out.println("Reading in data for: " + person);
		InputStream is = this.getFileAsResource("data/day2_" + person + ".txt");
		try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
				BufferedReader reader = new BufferedReader(streamReader)) {

			String line;
			while ((line = reader.readLine()) != null) {
				entries.add(PasswordPolicy.fromInput(line));
				rawData.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void dumpData() {
		for(PasswordPolicy entry : entries) {
			System.out.println(entry);
		}
	}
}

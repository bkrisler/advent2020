package com.bkds.advent2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DayOne extends DayBase {

	private List<Integer> expenses = new ArrayList<>();
	
	public DayOne() {		
		super();
		this.readData();
	}

	public void readData() {
		InputStream is = this.getFileAsResource("data/day1.txt");
		try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
				BufferedReader reader = new BufferedReader(streamReader)) {

			String line;
			while ((line = reader.readLine()) != null) {
				expenses.add(Integer.valueOf(line));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void dumpData() {
		for(Integer expense : expenses) {
			System.out.println(expense);
		}
	}
	
	/**
	 * Solve the Day 1 problem. Objective:
	 * 
	 * Find the two numbers that add up to 2020,
	 * multiply them and return the result.
	 * 
	 */
	public int solve() {
		// Step 1: Sort the list
		
		// Step 2: Create 3 temporary lists:
		//   -- Numbers less than (2020 / 2)
		//   -- Numbers equal to (2020 /2)
		//   -- Numbers greater than (2020 /2)
		
		
		return 0;
	}

	public static void main(String[] args) {
		DayOne dOne = new DayOne();
		int answer = dOne.solve();
		System.out.println("The answer is: " + answer);
	}

}

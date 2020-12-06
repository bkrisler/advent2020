package com.bkds.advent2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public abstract class DayBase {

	/**
	 * Called on each data input row received. This allows us to store the input as
	 * we need for each day.
	 * 
	 * @param input A single line of data from the input
	 */
	public abstract void store(String input);

	protected void readData(String day, String person) {
		System.out.println("Reading in data for: " + person + " and " + day);
		InputStream is = this.getFileAsResource("data/" + day + "_" + person + ".txt");
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

	protected void readDataMultiRow(String day, String person) {
		System.out.println("Reading in data for: " + person + " and " + day);
		String entry = "";
		InputStream is = this.getFileAsResource("data/" + day + "_" + person + ".txt");
		try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
				BufferedReader reader = new BufferedReader(streamReader)) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.isEmpty()) {
					store(entry);
					entry = "";
				} else {
					entry += line + " ";
				}
			}
			store(entry);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected InputStream getFileAsResource(String filename) {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream stream = classLoader.getResourceAsStream(filename);
		if (stream == null) {
			throw new IllegalArgumentException("Unknown file: " + filename);
		}

		return stream;
	}
}

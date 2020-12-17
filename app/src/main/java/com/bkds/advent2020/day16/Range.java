package com.bkds.advent2020.day16;

public class Range {

	private int min;
	private int max;
	
	
	public Range(int min, int max) {
		this.min = min;
		this.max = max;
	}

	public boolean inRange(int value) {
		//System.out.println("Check: " + value + " in: " + min + " - " + max + " result = " + (value > min && value < max));
		return value >= min && value <= max;
	}

	@Override
	public String toString() {
		return "Range [min=" + min + ", max=" + max + "]";
	}

	public int getMin() {
		return this.min;
	}
	
	public int getMax() {
		return this.max;
	}
	
}

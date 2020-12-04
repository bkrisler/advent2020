package com.bkds.advent2020.day3;

import java.util.ArrayList;
import java.util.List;

public class CircularList {

	private List<String> contents = new ArrayList<>();
	private int index = 0;
	
	public CircularList() {
	}

	public int size() {
		return contents.size();
	}
	
	public void add(String item) {
		contents.add(item);
	}
	
	public String getNext() {		
		if(index == contents.size()) {
			index = 0;
		}
		String item =  contents.get(index++);		
		return item;
	}
	
	public void reset() {
		this.index = 0;
	}
}

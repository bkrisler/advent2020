package com.bkds.advent2020.day8;

import java.util.ArrayList;
import java.util.List;

import com.bkds.advent2020.DayBase;

public class Day8 extends DayBase {

	private List<String> stack = new ArrayList<>();
	private int accumulator = 0;
	private int pointer = 0;
	
	public Day8() {
		readData("day8", "brian");
	}

	public void incrementACC(String countVal) {
		int number = Integer.valueOf(countVal.substring(1));
		if(countVal.startsWith("+")) {
			this.accumulator += number;
		} else {
			this.accumulator -= number;
		}
	}
	
	public int jump(String dist) {
		int newIndex = 0;
		int number = Integer.valueOf(dist.substring(1));
		if(dist.startsWith("+")) {
			newIndex = pointer + number;
		} else {
			newIndex = pointer - number;
		}		
		
		return newIndex;
	}
	
	public boolean execute(List<String> code) {
		List<Integer> called = new ArrayList<>();		
		while(pointer < code.size()) {
			if(called.contains(pointer)) {
				System.out.println("About to Loop!");
				System.out.println("ACC: " + this.accumulator);
				return false;
			}
			called.add(pointer);
			String command = code.get(pointer);
			String[] sep = command.split(" ");
			switch(sep[0]) {
			case "acc":
				incrementACC(sep[1]);
				pointer +=1;
				break;
			case "jmp":
				pointer = jump(sep[1]);
				break;
			case "nop":
				pointer += 1;
				break;
			}
		}
		return true;
	}
	
	@Override
	public void store(String input) {
		stack.add(input);		
	}
	
	public List<String> getStack() {
		return stack;
	}

	public void testSwap() {
		
	}
	
	public static void main(String[] args) {
		Day8 d8 = new Day8();
		d8.execute(d8.getStack());
	}
}

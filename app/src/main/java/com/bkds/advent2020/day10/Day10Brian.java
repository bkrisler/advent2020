package com.bkds.advent2020.day10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import com.bkds.advent2020.DayBase;
import com.bkds.advent2020.Tree;

public class Day10Brian extends DayBase {

	int CHARGING_OUTLET_JOLTS = 0;

	List<Integer> data = new ArrayList<>();

	public Day10Brian() {
		readData("day10", "sample");
		Collections.sort(data);
	}

	@Override
	public void store(String input) {
		data.add(Integer.valueOf(input));
	}

	public void solve() {
		int oneJoltDiff = 0;
		int threeJoltDiff = 1; // The built in is always 3 higher than the max, so start at 1.
		int source = CHARGING_OUTLET_JOLTS;
		List<Integer> result = findPluggable(source);
		while (result != null && !result.isEmpty()) {
			int selected = result.get(0);
			if (selected - source == 1) {
				oneJoltDiff++;
			} else if (selected - source == 3) {
				threeJoltDiff++;
			}
			source = selected;
			result = findPluggable(result.get(0));
		}
		
		System.out.println("1 Jolt Difference: " + oneJoltDiff);
		System.out.println("3 Jolt Difference: " + threeJoltDiff);
		System.out.println("Result = " + oneJoltDiff * threeJoltDiff);
	}

	public Tree<Integer> addChildren(Tree<Integer> treeNode) {
		List<Integer> paths = getPaths(treeNode.getValue());

		if(paths == null || paths.isEmpty()) {
			return null;
		}
		
		for(Integer node : paths) {
			addChildren(treeNode.addChild(node));
		}
		
		return null;
	}
	
	private List<Integer> getPaths(Integer start) {
		List<Integer> paths = new ArrayList<>();
		int idx = data.indexOf(start) + 1;
		if(idx >= data.size()) return paths;
		int nxt = data.get(idx);
		while((idx < data.size() - 1) && (nxt - start < 4)) {
			idx++;
			paths.add(nxt);
			nxt = data.get(idx);
		}
		
		return paths;
	}

	private List<Integer> getPaths2(int startIndex) {
		List<Integer> paths = new ArrayList<>();
		Integer startValue = data.get(startIndex);
		int idx = startIndex + 1;
		if(idx >= data.size()) return paths;
		int nxt = data.get(idx);
		while((idx < data.size() - 1) && (nxt - startValue < 4)) {
			idx++;
			paths.add(nxt);
			nxt = data.get(idx);
		}
		
		return paths;
	}
	
	public void solve2() {
		Tree<Integer> root = new Tree<>(0);
		Tree<Integer> child = new Tree<>(data.get(0));
		addChildren(child);
		
//		//		Tree<Integer> currentNode = tree;
//		for(int i=0; i < data.size(); i++) {
//			Tree<Integer> crnt = new Tree<>(data.get(i));
//			Tree<Integer> child = addChildren(crnt, data.get(i)); 			
//		}
		System.out.println("Here");
	}
	
	public List<Integer> findPluggable(int source) {
		List<Integer> result = data.stream().filter(x -> (x >= source + 1 & (x <= source + 3)))
				.collect(Collectors.toList());

		return result;
	}

	public int buildInJoltage() {
		IntSummaryStatistics stats = data.stream().mapToInt((x) -> x).summaryStatistics();
		int max = stats.getMax();
		return max + 3;
	}

	public static void main(String[] args) {
		Day10Brian b = new Day10Brian();
		b.solve2();
	}
}

package com.bkds.advent2020.day16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.bkds.advent2020.DayBase;

public class Day16Brian extends DayBase {

	private String yourTicket;
	private List<String> nearbyTickets = new ArrayList<>();
	private Map<String, List<Range>> notes = new HashMap<>();

	public Day16Brian() {
		readTicketData("day16", "brian");
	}

	@Override
	public void store(String input) {
		System.out.println(input);
	}

	public void storeNote(String input) {
		String[] parts = input.split(":");
		String[] ranges = parts[1].split(" or ");
		List<Range> rangeList = new ArrayList<>();
		for (String range : ranges) {
			String[] rangeParts = range.trim().split("-");
			Range mRange = new Range(Integer.valueOf(rangeParts[0]), Integer.valueOf(rangeParts[1]));
			rangeList.add(mRange);
		}
		notes.put(parts[0], rangeList);
	}

	public void storeNearby(String input) {
		if (input.startsWith("nearby")) {
			return;
		}
		nearbyTickets.add(input);
	}

	public void storeYourTicket(String input) {
		if (input.startsWith("your")) {
			return;
		}
		yourTicket = input;
	}

	protected void readTicketData(String day, String person) {
		String dataMode = "notes";
		System.out.println("Reading in data for: " + person + " and " + day);
		InputStream is = this.getFileAsResource("data/" + day + "_" + person + ".txt");
		try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
				BufferedReader reader = new BufferedReader(streamReader)) {

			String line;

			while ((line = reader.readLine()) != null) {
				if (line.isEmpty() || line.isBlank()) {
					continue;
				}

				if (line.startsWith("your ticket:"))
					dataMode = "your_ticket";
				else if (line.startsWith("nearby tickets")) {
					dataMode = "nearby";
				}

				if (dataMode.equals("notes")) {
					storeNote(line);
				} else if (dataMode.equals("your_ticket")) {
					storeYourTicket(line);
				} else if (dataMode.equals("nearby")) {
					storeNearby(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Day16Brian [yourTicket=" + yourTicket + ", nearbyTickets=" + nearbyTickets + ", notes=" + notes + "]";
	}

	List<Range> getAllRanges() {
		List<Range> l = new ArrayList<>();
		for (String key : notes.keySet()) {
			for (Range r : notes.get(key)) {
				l.add(r);
			}
		}
		return l;
	}

	public List<String> validTickets(List<String> tickets) {
		List<String> invalid = invalidTickets(tickets);
		tickets.removeAll(invalid);
		return tickets;
	}

	public List<String> invalidTickets(List<String> tickets) {
		List<String> ticketList = new ArrayList<>();

		List<Range> allRanges = getAllRanges();
		for (String ticket : tickets) {
			int[] numbers = Arrays.stream(ticket.split(",")).mapToInt(Integer::parseInt).toArray();
			for (int i = 0; i < numbers.length; i++) {
				boolean valid = false;
				for (Range range : allRanges) {
					if (range.inRange(numbers[i])) {
						valid = true;
					}
				}
				if (!valid) {
					ticketList.add(ticket);
				}
			}
		}

		return ticketList;
	}

	public int calculateErrorRate(List<String> tickets) {
		List<Range> allRanges = getAllRanges();
		List<Integer> invalid = new ArrayList<>();
		for (String ticket : tickets) {
			int[] numbers = Arrays.stream(ticket.split(",")).mapToInt(Integer::parseInt).toArray();
			for (int i = 0; i < numbers.length; i++) {
				boolean valid = false;
				for (Range range : allRanges) {
					if (range.inRange(numbers[i])) {
						valid = true;
					}
				}
				if (!valid) {
					invalid.add(numbers[i]);
				}
			}
		}
		int result = 0;
		for (Integer in : invalid) {
			result += in;
		}

		return result;
	}

	public void solvePartOne() {
		int errorRate = calculateErrorRate(nearbyTickets);
		System.out.println("Error Rate: " + errorRate);
	}

	public List<List<Integer>> groupByColumn(List<String> tickets) {
		List<List<Integer>> cols = new ArrayList<List<Integer>>();

		for (String ticket : tickets) {
			int[] numbers = Arrays.stream(ticket.split(",")).mapToInt(Integer::parseInt).toArray();
			for (int i = 0; i < numbers.length; i++) {
				try {
					List<Integer> column = cols.get(i);
					column.add(numbers[i]);
					cols.set(i, column);
				} catch (Exception e) {
					List<Integer> cList = new ArrayList<>();
					cList.add(numbers[i]);
					cols.add(cList);
				}
			}
		}

		return cols;
	}

	public List<String> noMatches(List<Integer> colVals) {
		List<String> noMatches = new ArrayList<>();
		for (Integer val : colVals) {
			for (String note : notes.keySet()) {
				List<Range> ranges = notes.get(note);
				Range rangeA = ranges.get(0);
				Range rangeB = ranges.get(1);
				if (!rangeA.inRange(val) && !rangeB.inRange(val)) {
					noMatches.add(note);
				}
			}
		}
		return noMatches;
	}

	public String findPossibleMatch(int min, int max) {
		for (String note : notes.keySet()) {
			List<Range> ranges = notes.get(note);
			Range rangeA = ranges.get(0);
			Range rangeB = ranges.get(1);
			if (rangeA.inRange(min) || rangeB.inRange(min)) {
				if (rangeA.inRange(max) || rangeB.inRange(max)) {
					return note;
				}
			}
		}
		return null;
	}

	public void solvePartTwo() {
		List<String> validTickets = validTickets(nearbyTickets);
		List<List<Integer>> columns = groupByColumn(validTickets);
		Map<Integer, List<String>> colNotHappening = new HashMap<>();
		
		for (int i = 0; i < columns.size(); i++) {
//			IntSummaryStatistics intSummaryStatistics = new IntSummaryStatistics();
//			columns.get(i).stream().mapToInt(Integer::intValue).forEach(intSummaryStatistics);
			colNotHappening.put(i, noMatches(columns.get(i)));
		}

		Map<Integer, List<String>> flipped = flip(colNotHappening);
		
		Map<Integer, String> finalMapping = new HashMap<>();
		
		boolean done = false;
		while(!done) {
			Iterator<Map.Entry<Integer, List<String>>> iter = flipped.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<Integer, List<String>> entry = iter.next();
				if(entry.getValue().size() == 0) {
					iter.remove();
				} else if(entry.getValue().size() == 1) {
					String value = entry.getValue().get(0);
					finalMapping.put(entry.getKey(), value);
					iter.remove();
					recalibrate(flipped, value);
					break;					
				}
			}
			
			if(flipped.size() == 0) {
				done = true;
			}			
		}		
		
		//System.out.println(finalMapping);
		int[] your_numbers = Arrays.stream(yourTicket.split(",")).mapToInt(Integer::parseInt).toArray();
		System.out.println(yourTicket);
		System.out.println(finalMapping);
		long result = 1;
		for(int i=0; i < your_numbers.length; i++) {		
			String v = finalMapping.get(i);
			
			if(v != null && v.startsWith("departure")) {
				result *= your_numbers[i];
			}
			System.out.println(v + " : " + your_numbers[i]);
//			if(finalMapping.get(i).startsWith("departure")) {
//				result *= i;
//			}
		}
		System.out.println("Result: " + result);
	}

	private Map<Integer, List<String>> flip(Map<Integer, List<String>> colNotHappening) {
		Map<Integer, List<String>> result = new HashMap<Integer, List<String>>();
		List<String> columns = new ArrayList<>(notes.keySet());
		for(Integer key : colNotHappening.keySet()) {
			columns = new ArrayList<>(notes.keySet());
			List<String> vals = colNotHappening.get(key);
			columns.removeAll(vals);
			//System.out.println(key + " Before: " + vals + " After: " + columns);
			result.put(key, columns);
		}
		return result;
	}

	private void recalibrate(Map<Integer, List<String>> colNotHappening, String value) {
		for(Integer idx : colNotHappening.keySet()) {
			List<String> vals = colNotHappening.get(idx);
			if(vals.contains(value)) {
				vals.remove(vals.indexOf(value));
				colNotHappening.put(idx, vals);
			}
		}		
	}

	private List<String> tickets() {
		return nearbyTickets;
	}

	public static void main(String[] args) {
		Day16Brian b = new Day16Brian();
		// b.solvePartOne();
		b.solvePartTwo();
	}

}

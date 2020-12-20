package com.bkds.advent2020.day17;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.bkds.advent2020.DayBase;

public class Day17 extends DayBase {

	private List<Point> cubes = new ArrayList<>();

	public static final String ACTIVE = "#";
	public static final String INACTIVE = ".";
	
	int y = 0;
	public Day17() {
		readData("day17", "brian");
	}

	@Override
	public void store(String input) {
		String[] row = input.split("");
		for(int x=0; x < row.length; x++) {
			cubes.add(new Point(x, y, 0, row[x]));
		}
		y++;
	}

	/**
	 *   x  y  z       s
	 *   ---------------
	 *  -1  0 -1      -2
	 *  -1  1 -1      -1
	 *  -1 -1 -1      -3
	 *   0  1 -1       0
	 *   0  0 -1      -1
	 *   0 -1 -1      -2
	 *   1  0 -1       0
	 *   1  1 -1       1
	 *   1 -1 -1      -1

	 *  -1  0  0      -1
	 *  -1  1  0       0
	 *  -1 -1  0      -2
	 *   0  0  0       0  -- target
	 *   0  1  0       1
	 *   0 -1  0      -1
	 *   1 -1  0       0
	 *   1  1  0       2
	 *   1  0  0       1

	 *  -1 -1  1      -1
	 *  -1  1  1       1
	 *  -1  0  1       0
	 *   0  0  1       1
	 *   0  1  1       2
	 *   0 -1  1       0
	 *   1  0  1       2
	 *   1  1  1       3
	 *   1 -1  1       1
	 *   
	 * 
	 * @param point
	 * @return
	 */
	public boolean shouldDeactivate(Point point) {
		Point n1 = hasCube(point.x, point.y+1, point.z);
		return true;
	}
	
	public void solve() {
		for(int i=0; i < cubes.size(); i++) {
			Point point = cubes.get(i);
			if(point.isActive()) {
				// check if 2 or 3 neighbors are also active.
				if(shouldDeactivate(point)) {
					point.state = INACTIVE;
				}
			} else {
				// check if exactly 3 are active.
			}
		}
	}
	
	public Point hasCube(int x, int y, int z) {
		Point p = null;
		List<Point> matches = cubes.stream().filter(c -> c.same(x, y, z)).collect(Collectors.toList());
		if(matches == null || matches.isEmpty()) {
			System.out.println("Create.");
		} else if(matches.size() > 1) {
			System.err.println("That ain't right.");
		} else {
			p = matches.get(0);
		}
		return p;
	}
	
	public static void main(String[] args) {
		Day17 d17 = new Day17();
		d17.solve();
	}

	class Point {
		int x;
		int y;
		int z;
		String state;
		
		Point(int x, int y, int z, String state) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.state = state;
		}
		
		public int x() {
			return this.x;			
		}

		public int y() {
			return this.y;			
		}
		
		public int z() {
			return this.z;			
		}

		public boolean same(int x, int y, int z) {
			if(this.x == x && this.y == y && this.z == z) {
				return true;
			}
			return false;
		}
		
		public boolean isActive() {
			return this.state.equals("#");
		}
		
		public String state() {
			return this.state;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", z=" + z + ", state=" + state + "]";
		}
		
	}
}

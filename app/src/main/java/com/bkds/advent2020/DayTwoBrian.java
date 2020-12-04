package com.bkds.advent2020;

import com.bkds.advent2020.model.PasswordPolicy;

public class DayTwoBrian extends DayTwoBase {

	public DayTwoBrian() {
		readData("brian");
		dumpData();
	}

	public int solve() {
		int validCount = 0;
		for (PasswordPolicy policy : this.entries) {
			String password = policy.getPassword();
			int count = 0;
			for (int i = 0; i < password.length(); i++) {
				if (password.charAt(i) == policy.getCharacter().charAt(0)) {
					count++;
				}
			}
			if (count >= policy.getMin() && count <= policy.getMax()) {
				validCount++;
			}
		}
		return validCount;
	}

	public int solvePartTwo() {
	    int validCount = 0;
	    for (PasswordPolicy policy : this.entries)
	    {
	      char c = policy.getCharacter().charAt(0);
	      String password = policy.getPassword();
	      char firstCharacter = password.charAt(policy.getMin() - 1);
	      char secondCharacter = password.charAt(policy.getMax() - 1);
	      // System.out.println(policy.getMin() + " " + policy.getMax());
	      // System.out
	      // .println(password + " " + firstCharacter + " " + secondCharacter);
	      if ((firstCharacter == c || secondCharacter == c)
	        && !(firstCharacter == c && secondCharacter == c))
	      {
	        validCount++;
	      }
	    }
	    return validCount;
		
	}
	
	public static void main(String[] args) {
		DayTwoBrian day2 = new DayTwoBrian();
		System.out.println(day2.solve());
		System.out.println(day2.solvePartTwo());
	}
}

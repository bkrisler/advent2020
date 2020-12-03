package com.bkds.advent2020.model;

public class PasswordPolicy {
	private int min;
	private int max;
	private String character;
	private String password;

	protected PasswordPolicy() {
		super();
	}

	public static PasswordPolicy fromInput(String input) {
		PasswordPolicy policy = new PasswordPolicy();
		String[] parts = input.split(" ");
		String[] range = parts[0].split("-");
		policy.setMin(Integer.valueOf(range[0]));
		policy.setMax(Integer.valueOf(range[1]));
		policy.setCharacter(parts[1].substring(0,1));
		policy.setPassword(parts[2]);
		return policy;
	}
	
	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "PasswordPolicy [min=" + min + ", max=" + max + ", character=" + character + ", password=" + password
				+ "]";
	}

}

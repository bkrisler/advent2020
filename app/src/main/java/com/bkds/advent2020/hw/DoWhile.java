package com.bkds.advent2020.hw;

import java.util.Scanner;

public class DoWhile {

	public DoWhile() {
		// TODO Auto-generated constructor stub
	}

	public void test3() {
		int n = 0;
		Scanner stdin = new Scanner(System.in);
		
		stdin.close();
		System.out.println("Count = " + n);	
	}
	
	public void tst1() {
		int n = 0;
		Scanner stdin = new Scanner(System.in);
		String current = stdin.next();;
		String previous = "";
		do {
			System.out.println("Test: " + previous + " with " + current);
			if(!previous.equals("") && !current.equals(previous)) {
				n++;
			}
			previous = current;
			current = stdin.next();
		} while(!current.equals("xxx"));
		stdin.close();
		System.out.println("Count = " + n);
	}

	public void tst2() {
		int n = 0;
		Scanner stdin = new Scanner(System.in);
		String input = stdin.next();
		String previous = "";
		while(!input.equals("xxx")) { 
			System.out.println("Input: " + input + " Previous: " + previous);
			n++;
			previous = input;
			input = stdin.next();
		}
		stdin.close();
		System.out.println("Count = " + n);
	}

	public void nextTest() {
		int counter = 0;
		boolean done = false;	
		boolean isRepeating = true;
		Scanner input = new Scanner(System.in);
		String prev = input.next();		
		while(!done) {			
			String pos = input.next();
			//System.out.println("Compare: " + prev + " with " + pos);
			if(!pos.equals(prev) && !isRepeating) {
				counter++;
				isRepeating = false;
			} else {
				isRepeating = true;
			}
			if(pos.equals("x")) {
				done = true;
			}
			prev = pos;
		}
		input.close();
		System.out.println("Counter = " + counter);
	}
	
	public static void main(String[] args) {
		DoWhile w = new DoWhile();
		w.nextTest();
		//System.out.println(111 % 100);
	}
}

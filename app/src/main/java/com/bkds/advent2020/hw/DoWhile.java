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
		while(!input.equals("xxx")) { 
			System.out.println("Input: " + input);
		}
		stdin.close();
		System.out.println("Count = " + n);
	}

	public static void main(String[] args) {
		DoWhile w = new DoWhile();
		//w.tst1();
		System.out.println(111 % 100);
	}
}

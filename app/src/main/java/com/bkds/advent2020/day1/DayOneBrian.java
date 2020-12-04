package com.bkds.advent2020.day1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.bkds.advent2020.DayBase;

public class DayOneBrian extends DayBase
{

  private List<Integer> expenses = new ArrayList<>();
  
  public DayOneBrian(String person)
  {
    super();
    this.readData("day1", person);
    
  }

  @Override
  public void store(String input) {
  	expenses.add(Integer.valueOf(input));	
  }

  public void dumpData()
  {
    for (Integer expense : expenses)
    {
      System.out.println(expense);
    }
  }

  public int solve1() {
	  Collections.sort(expenses);
	  
	  List<Integer> less = expenses
			  .stream()
			  	.filter(i -> i < 2020 /2)
			    .collect(Collectors.toList());

	  List<Integer> more = expenses.stream()
			  	.filter(i -> i > 2020 /2)
			    .collect(Collectors.toList());

	  for(Integer n1 : more) {
		  for(Integer n2 : less) {
			  if(n1 + n2 == 2020) {
				  return n1 * n2;
			  }
		  }
	  }
	  return 0;
  }

  public int solve2() {
	  Collections.sort(expenses);
	  
	  List<Integer> less = expenses
			  .stream()
			  	.filter(i -> i < 2020 /2)
			    .collect(Collectors.toList());

	  List<Integer> more = expenses.stream()
			  	.filter(i -> i > 2020 /2)
			    .collect(Collectors.toList());

	  for(Integer n1 : more) {
		  for(Integer n2 : less) {
			  if(n1 + n2 < 2020) {
				  int required = 2020 - (n1 + n2);
				  if(less.contains(required)) {
					  int n3 = less.get(less.indexOf(required));
					  System.out.println("Found 3rd: " + n1 + " + " + n2 + " + " + n3 + " = 2020");
					  return n1 * n2 * n3;
				  }
			  }
		  }
	  }
	  return 0;
  }
  
  /**
   * Solve the Day 1 problem. Objective:
   * 
   * Find the two numbers that add up to 2020,
   * multiply them and return the result.
   * 
   */
  public int solvePart1()
  {
    // Step 1: Sort the list
    Collections.sort(expenses);
    dumpData();
    Integer result = 0;

    // Step 2: Create 3 temporary lists:
    ArrayList<Integer> lessThan = new ArrayList<>();
    ArrayList<Integer> moreThan = new ArrayList<>();
    ArrayList<Integer> equalTo = new ArrayList<>();

    for (Integer expense : expenses)
    {
      if (expense < 2020 / 2)
      {
        lessThan.add(expense);
      }

      if (expense > 2020 / 2)
      {
        moreThan.add(expense);
      }

      if (expense == 2020 / 2)
      {
        equalTo.add(expense);
      }
    }

    for (Integer lList : lessThan)
    {
      for (Integer mList : moreThan)
      {
        if (lList + mList == 2020)
        {
          return lList * mList;
        }
      }
    }

    System.out.println(lessThan.size());
    System.out.println(moreThan.size());
    System.out.println(equalTo.size());

    return result;
  }

  public static void main(String[] args)
  {
	String person = "unknown";
	if(args.length > 0) {
		person = args[0];
	}
	
    DayOneBrian dOne = new DayOneBrian(person);
    int answer1 = dOne.solve1();
    System.out.println("The answer for part one is: " + answer1);

    int answer2 = dOne.solve2();
    System.out.println("The answer for part two is: " + answer2);

  }

}

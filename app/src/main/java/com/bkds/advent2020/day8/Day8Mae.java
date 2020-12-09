package com.bkds.advent2020.day8;

import java.util.ArrayList;
import java.util.List;

import com.bkds.advent2020.DayBase;

public class Day8Mae extends DayBase
{

  // Step 1: Create an array list of strings
  // That will hold your "code".
  List<String> day8Solve = new ArrayList<>();

  public Day8Mae()
  {
    readData("day8", "mae");
  }

  @Override
  public void store(String input)
  {
    // Step 2: Add input to your "code" list.
    day8Solve.add(input);
  }

  // Step 3: Create a void method to "execute" your code
  private boolean execute(List<String> code)
  {
    boolean result = false;
    int acc = 0;
    int jmp = 0;
    List<Integer> callHistory = new ArrayList<>();
    for (int i = 0; i < code.size(); i++)
    {
      if (callHistory.contains(i))
      {
        System.out.println("error!");
        return result;
      }
      callHistory.add(i);
      String tempv = code.get(i);
      // aSystem.out.println(day8Solve.get(i));
      String[] parts = tempv.split(" ");
      String opp = parts[0];
      Integer instr = Integer.valueOf(parts[1]);
      if (opp.equals("acc"))
      {
        acc += instr;
      }
      else if (opp.equals("jmp"))
      {
        i += instr;
        i--;
      }
      else if (opp.equals("nop"))
      {
        // System.out.println(opp);
      }
    }
    System.out.println("Final ACC: " + acc);
    result = true;
    return true;
  }

  private void partTwo()
  {

    for (int i = 0; i < day8Solve.size(); i++)
    {
      String tempv = day8Solve.get(i);
      String[] parts = tempv.split(" ");
      String opp = parts[0];

      if (opp.equals("jmp"))
      {
        List<String> code = new ArrayList<>(day8Solve);
        code.set(i, "nop " + parts[1]);
        if (execute(code))
        {
          System.out.println("it worked!");
          return;
        }
      }
      else if (opp.equals("nop"))
      {
        List<String> code = new ArrayList<>(day8Solve);
        code.set(i, "jmp " + parts[1]);
        if (execute(code))
        {
          System.out.println("it worked!");
          return;
        }
      }
    }

  }

  public static void main(String[] args)
  {

    Day8Mae d8 = new Day8Mae();
    // Step 4: Call your execute method.
    d8.execute(d8.getDay8Solve());
    d8.partTwo();
  }

  public List<String> getDay8Solve()
  {
    return day8Solve;
  }
}

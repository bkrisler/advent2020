/**
 * Preference file version 092020
 * 
 * @author: Your First and Last name goes here!
 */

package com.bkds.advent2020.day2;

public class DayTwoMae extends DayTwoBase
{
  public DayTwoMae()
  {
    readData("day3", "mae");
    // dumpData();
  }

  public int findValidPasswords()
  {
    int validCount = 0;
    for (PasswordPolicy policy : this.entries)
    {
      String c = policy.getCharacter();
      String password = policy.getPassword();
      int count = 0;
      for (int i = 0; i < password.length(); i++)
      {
        if (password.charAt(i) == c.charAt(0))
        {
          count++;
        }
      }
      if (count >= policy.getMin() && count <= policy.getMax())
      {
        validCount++;
      }
    }
    return validCount;
  }

  public int findValidPasswordsPart2()
  {
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

  public static void main(String[] args)
  {
    DayTwoMae dtm = new DayTwoMae();
    int valid = dtm.findValidPasswords();
    System.out.println(valid);
    valid = dtm.findValidPasswordsPart2();
    System.out.println(valid);
  }

}

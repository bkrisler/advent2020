package com.bkds.advent2020.day6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bkds.advent2020.DayBase;

public class Day6 extends DayBase
{

  private List<List<String>> personGroups = new ArrayList<>();

  public Day6()
  {
    readDataMultiRow("day6", "mae");
    // dumpData();
  }

  private void dumpData()
  {
    for (List<String> group : personGroups)
    {
      System.out.println(group);
    }
  }

  @Override
  public void store(String input)
  {
    // Not used for this problem
  }

  protected void readDataMultiRow(String day, String person)
  {
    System.out.println("Reading in data for: " + person + " and " + day);
    List<String> group = new ArrayList<>();
    InputStream is =
      this.getFileAsResource("data/" + day + "_" + person + ".txt");
    try (
      InputStreamReader streamReader =
        new InputStreamReader(is, StandardCharsets.UTF_8);
      BufferedReader reader = new BufferedReader(streamReader))
    {
      String line;
      while ((line = reader.readLine()) != null)
      {
        if (!line.isBlank())
        {
          group.add(line);
        }
        else
        {
          List<String> record = new ArrayList<>(group);
          this.personGroups.add(record);
          group.clear();
        }
      }
      List<String> record = new ArrayList<>(group);
      this.personGroups.add(record);

    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void customsQuestions()
  {
    int finalCount = 0;

    for (List<String> group : personGroups)
    {
      List<String> yesAnswers = new ArrayList<>();
      for (String person : group)
      {
        String[] answers = person.split("");

        for (int i = 0; i < answers.length; i++)
        {
          if (!yesAnswers.contains(answers[i]))
          {
            yesAnswers.add(answers[i]);
          }
        }
      }
      System.out.println(yesAnswers.size());
      finalCount += yesAnswers.size();
      yesAnswers.clear();

    }
    System.out.println(finalCount);

  }

  public void partTwo()
  {
    int finalAnswer = 0;
    for (List<String> group : personGroups)
    {
      Map<String, Integer> answerTotals = new HashMap<>();
      for (String person : group)
      {
        String[] answers = person.split("");

        for (int i = 0; i < answers.length; i++)
        {
          if (answerTotals.containsKey((answers[i])))
          {
            answerTotals.put(answers[i], answerTotals.get(answers[i]) + 1);
          }
          else
          {
            answerTotals.put(answers[i], 1);
          }
        }
      }
      int groupAnswers = 0;
      for (String key : answerTotals.keySet())
      {
        Integer count = answerTotals.get(key);
        if (count == group.size())
        {
          groupAnswers++;
        }
      }

      System.out.println(groupAnswers);
      // System.out.println(answerTotals);
      // finalCount += answerTotals.size();
      answerTotals.clear();
      finalAnswer += groupAnswers;
    }
    System.out.println(finalAnswer);
  }

  public static void main(String[] args)
  {
    Day6 d6 = new Day6();
    // d6.customsQuestions();
    d6.partTwo();
  }

}

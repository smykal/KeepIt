package org.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

  public static String analyze(String html) {
    var findAllUl = findAllUl(html);
    var onlyULAndLi = leaveOnlyHypertext(findAllUl);
    var elementsList = putIntoList(onlyULAndLi);
    elementsList = cleanList(elementsList);


//    List<String> mergedList = mergeInList(elementsList);
//    for (String element : mergedList) {
//      System.out.println(element);
//    }

    return findMaxDirectChildren(elementsList);
  }

  private static String findMaxDirectChildren(List<String> elementsList) {

    Iterator<String> iterator = elementsList.iterator();

    int embededStage = 0;
    boolean parentUl = true;


    HashMap<Integer, Integer> map = new HashMap<>();
    while (iterator.hasNext()) {
      String element = iterator.next();

      if (element.equals("<ul>")) {
        embededStage++;
      } else if (element.equals("<li></li>") && embededStage == 1 && parentUl) {
        int value = map.getOrDefault(embededStage, 0);
        value++;
        map.put(embededStage, value);

      } else if (element.equals("<li>") && embededStage == 1 && parentUl) {
        int value = map.getOrDefault(embededStage, 0);
        value++;
        map.put(embededStage, value);
      } else if (element.equals("</ul>")) {
        if (embededStage == 1) {
          parentUl = false;
        }
        embededStage--;
      }
    }

    try {
      return map.get(1)
          .toString();
    } catch (NullPointerException e) {
      return "there are any <ul> selectors";
    }
  }


  private static List<String> cleanList(List<String> elementsList) {
    List<String> cleanedList = new ArrayList<>();

    Pattern pattern = Pattern.compile("</?(ul|li)(\\s+[^>]*)?>");

    for (String element : elementsList) {
      Matcher matcher = pattern.matcher(element);
      if (matcher.find()) {
        if (element.startsWith("</")) {
          cleanedList.add("</" + matcher.group(1) + ">");
        } else {
          cleanedList.add("<" + matcher.group(1) + ">");
        }
      }
    }

    return cleanedList;
  }

  private static List<String> mergeInList(List<String> list) {
    List<String> mergedList = new ArrayList<>();

    for (int i = 0; i < list.size(); i++) {
      String current = list.get(i);

      if (current.equals("<li>") && i + 1 < list.size() && list.get(i + 1)
          .equals("</li>")) {
        mergedList.add("<li></li>");
        i++;
      } else {
        mergedList.add(current);
      }
    }

    return mergedList;
  }

  private static List<String> putIntoList(String onlyULAndLi) {
    List<String> elementsList = new ArrayList<>();
    Pattern pattern = Pattern.compile("</?(ul|li)[^>]*>");
    Matcher matcher = pattern.matcher(onlyULAndLi);

    while (matcher.find()) {
      elementsList.add(matcher.group());
    }

    return elementsList;
  }


  private static String findAllUl(String htmlContent) {
    int startIndex = htmlContent.indexOf("<ul");
    if (startIndex != -1) {
      htmlContent = htmlContent.substring(startIndex);
    }

    StringBuilder ulContent = new StringBuilder();
    Pattern pattern = Pattern.compile("<ul[^>]*>.*?</ul>|<li[^>]*>.*?</li>|</ul>|</li>", Pattern.DOTALL);
    Matcher matcher = pattern.matcher(htmlContent);

    while (matcher.find()) {
      ulContent.append(matcher.group())
          .append("\n\r");
    }

    return ulContent.toString();
  }


  private static String leaveOnlyHypertext(String contentWithTags) {
    Pattern pattern = Pattern.compile("</?(ul|li)[^>]*>", Pattern.DOTALL);
    Matcher matcher = pattern.matcher(contentWithTags);

    StringBuilder tagsOnly = new StringBuilder();

    while (matcher.find()) {
      tagsOnly.append(matcher.group());
    }

    return tagsOnly.toString();
  }
}

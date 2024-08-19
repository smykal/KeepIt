package org.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

  private final static String UL_OPEN = "<ul>";
  private final static String UL_CLOSE = "</ul>";
  private final static String LI_OPEN = "<li>";
  private final static String LI_CLOSE = "</li>";

  public static String analyze(String html) {
    var findAllUl = findAllUl(html);
    var onlyULAndLi = leaveOnlyHypertext(findAllUl);
    var elementsList = putIntoList(onlyULAndLi);
    elementsList = cleanList(elementsList);

    return findMaxDirectChildren(elementsList);
  }

  private static String findMaxDirectChildren(List<String> elementsList) {

    Iterator<String> iterator = elementsList.iterator();

    int embededStage = 0;
    boolean parentUl = true;


    LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
    while (iterator.hasNext()) {
      String element = iterator.next();

      if (element.equals(UL_OPEN)) {
        embededStage++;
      } else if (element.equals(LI_OPEN.concat(LI_CLOSE)) && embededStage == 1 && parentUl) {
        int value = map.getOrDefault(embededStage, 0);
        value++;
        map.put(embededStage, value);

      } else if (element.equals(LI_OPEN) && embededStage == 1 && parentUl) {
        int value = map.getOrDefault(embededStage, 0);
        value++;
        map.put(embededStage, value);
      } else if (element.equals(UL_CLOSE)) {
        if (embededStage == 1) {
          parentUl = false;
        }
        embededStage--;
      }
    }
    return map.getOrDefault(1, 0)
        .toString();
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

package org.example;

import org.example.service.GetHtml;
import org.example.service.GetUrl;
import org.example.service.Parser;

public class Main {
  public static void main(String[] args) {
    String urlFromUser = GetUrl.getUrlFromUser();
    String html = GetHtml.getHtml(urlFromUser);
    var result = Parser.analyze(html);
    System.out.println(result);
  }
}
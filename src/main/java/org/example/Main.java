package org.example;

import org.example.service.getHtml.Content;
import org.example.service.getUrl.Url;
import org.example.service.Parser;

public class Main {
  public static void main(String[] args) {
    var url = Url.getUrlFromUser();
    var html = Content.getHtml(url);
    var result = Parser.analyze(html);
    System.out.println(result);
  }
}
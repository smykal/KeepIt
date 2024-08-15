package org.example.service.getUrl;

import java.util.Scanner;

public class Url {
  private final static String HTTP = "http://";

  public static String getUrlFromUser() {
    UrlValidatorImpl validator = UrlValidatorImpl.getInstance();
    Scanner scanner = new Scanner(System.in);
    String url = "";

    while (!validator.valid(url)) {
      System.out.println("Please enter the URL:");
      url = scanner.nextLine();
    }

    return url.startsWith(HTTP) ? url : HTTP + url;
  }
}

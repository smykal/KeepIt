package org.example.service;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetUrl {

  private static final String URL_REGEX = "^(http://|https://)?[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
  private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

  public static String getUrlFromUser() {
    Scanner scanner = new Scanner(System.in);
    String url = "";

    while (url.trim().isEmpty() || !isValidUrl(url)) {
      System.out.println("Please enter the URL:");
      url = scanner.nextLine();

      if (url.trim().isEmpty()) {
        System.out.println("URL cannot be empty. Please enter a valid URL.");
      } else if (!isValidUrl(url)) {
        System.out.println("The URL is not valid. Please enter a valid URL.");
      }
    }

    return url;
  }

  private static boolean isValidUrl(String url) {
    Matcher matcher = URL_PATTERN.matcher(url.trim());
    return matcher.matches();
  }
}

package org.example.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

public class GetHtml {

  private final static String HTTP = "http://";
  private final static String GET = "GET";

  public static String getHtml(String inputURL) {
    String finalContent = null;
    try {
      String urlString = inputURL.startsWith(HTTP) || inputURL.startsWith(HTTP)
          ? inputURL
          : HTTP + inputURL;
      boolean redirect;
      HttpURLConnection connection;

      do {
        URL url = new URL(urlString);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(GET);
        connection.setInstanceFollowRedirects(false);

        int status = connection.getResponseCode();

        if (status == HttpURLConnection.HTTP_MOVED_TEMP || status == HttpURLConnection.HTTP_MOVED_PERM) {
          urlString = connection.getHeaderField("Location");
          System.out.println("Redirecting to: " + urlString);
          redirect = true;
        } else {
          redirect = false;
        }
      } while (redirect);

      BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String inputLine;
      StringBuilder content = new StringBuilder();
      while ((inputLine = in.readLine()) != null) {
        content.append(inputLine);
      }
      in.close();
      connection.disconnect();

      finalContent = content.toString();

//      System.out.println(finalContent);

    } catch (UnknownHostException e) {
      System.out.println("Unknown host: " + inputURL + ". Please check the URL and try again.");
    } catch (Exception e) {
      System.out.println("Something went wrong while fetching the HTML content.");
    }

    return finalContent;
  }
}

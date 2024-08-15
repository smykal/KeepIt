package org.example.service.getHtml;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import org.example.service.connection.HttpConnectionManager;

public class Content {

  public static String getHtml(String inputURL) {
    String finalContent = null;
    try {
      HttpConnectionManager connectionManager = HttpConnectionManager.getInstance();
      var connection = connectionManager.createConnection(inputURL);

      BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String inputLine;
      StringBuilder content = new StringBuilder();
      while ((inputLine = in.readLine()) != null) {
        content.append(inputLine);
      }
      in.close();
      connection.disconnect();

      finalContent = content.toString();

    } catch (UnknownHostException e) {
      System.out.println("Unknown host: " + inputURL + ". Please check the URL and try again.");
    } catch (Exception e) {
      System.out.println("Something went wrong while fetching the HTML content.");
    }

    return finalContent;
  }
}

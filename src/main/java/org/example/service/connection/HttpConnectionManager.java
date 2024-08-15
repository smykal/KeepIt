package org.example.service.connection;

import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnectionManager {

  private static final HttpConnectionManager instance = new HttpConnectionManager();

  private HttpConnectionManager() {
  }

  public static HttpConnectionManager getInstance() {
    return instance;
  }

  private final static String GET = "GET";


  public HttpURLConnection createConnection(String urlString) throws Exception {
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
    return connection;
  }
}

package org.example.service.getUrl;

public class UrlValidatorImpl implements Validator {

  private static final UrlValidatorImpl instance = new UrlValidatorImpl();

  private UrlValidatorImpl() {
  }

  public static UrlValidatorImpl getInstance() {
    return instance;
  }

  public boolean valid(String url) {
    if (isEmpty(url)) {
      System.out.println("URL is empty.");
      return false;
    } else if (isValidUrl(url)) {
      System.out.println("URL is valid.");
      return true;
    } else {
      System.out.println("URL is invalid.");
      return false;
    }
  }
}

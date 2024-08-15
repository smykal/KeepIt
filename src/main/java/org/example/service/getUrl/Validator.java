package org.example.service.getUrl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Validator {

  String URL_REGEX = "^(http://|https://)?[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
  Pattern URL_PATTERN = Pattern.compile(URL_REGEX);


  default boolean isValidUrl(String url) {
    Matcher matcher = URL_PATTERN.matcher(url.trim());
    return matcher.matches();
  }

  default boolean isEmpty(String url) {
    return url.trim()
        .isEmpty();
  }
}

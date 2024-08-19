package org.example.service.getUrl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UrlValidatorImplTest {

  private UrlValidatorImpl urlValidator;

  @BeforeEach
  void setUp() {
    urlValidator = UrlValidatorImpl.getInstance();
  }

  @Test
  void testSingletonInstance() {
    UrlValidatorImpl anotherInstance = UrlValidatorImpl.getInstance();
    assertSame(urlValidator, anotherInstance, "Both instances should be the same");
  }

  @Test
  void testValidUrl() {
    assertTrue(urlValidator.valid("http://zapiszto.com"), "Should return true for a valid URL with http");
    assertTrue(urlValidator.valid("https://zapiszto.com"), "Should return true for a valid URL with https");
    assertTrue(urlValidator.valid("google.com"), "Should return true for a valid URL without http/https");
  }

  @Test
  void testInvalidUrl() {
    assertFalse(urlValidator.valid("htt://domain"), "Should return false for an invalid URL");
    assertFalse(urlValidator.valid("domain"), "Should return false for an invalid URL with no domain");
  }

  @Test
  void testEmptyUrl() {
    assertFalse(urlValidator.valid(""), "Should return false for an empty URL");
    assertFalse(urlValidator.valid("   "), "Should return false for a URL with only spaces");
  }
}
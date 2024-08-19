package org.example.service;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ParserTest {

  @Test
  void testAnalyze_simpleList() {
    String html = "<ul><li>Item 1</li><li>Item 2</li></ul>";
    String result = Parser.analyze(html);
    assertEquals("2", result, "Should return '2' as there are 2 direct children in the root <ul>");
  }

  @Test
  void testAnalyze_nestedList() {
    String html = "<ul><li>Item 1</li><li>Item 2<ul><li>Subitem 1</li></ul></li><li>Item 3</li></ul>";
    String result = Parser.analyze(html);
    assertEquals("3", result, "Should return '3' as there are 3 direct children in the root <ul>");
  }

  @Test
  void testAnalyze_listWithAttributes() {
    String html = "<ul id='list'><li class='item'>Item 1</li><li>Item 2</li></ul>";
    String result = Parser.analyze(html);
    assertEquals("2", result, "Should return '2' ignoring any attributes in the tags");
  }

  @Test
  void testAnalyze_complexNestedLists() {
    String html = "<ul><li>Item 1<ul><li>Subitem 1</li><li>Subitem 2<ul><li>Subsubitem 1</li></ul></li></ul></li><li>Item 2</li></ul>";
    String result = Parser.analyze(html);
    assertEquals("2", result, "Should return '2' as there are 2 direct children in the root <ul>");
  }
}
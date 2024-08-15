package org.example.service;


class ParserTest {
  public static void main(String[] args) {

    //check if there is no <li>
    //given
    String html_1 = "<html><body><p>Content</p></body></html>";
    //when
    var result_1 = Parser.analyze(html_1);
    //then
    boolean r_1 = result_1.equals("there are any <ul> selectors");
    System.out.println("Check if no ul found:" + r_1);


    //check if there are 3 <li>
    //given
    String html_2 = "<html><body><p><ul><li>Content</li><li>Content</li><li>Content</li></ul></p></body></html>";
    //when
    var result_2 = Parser.analyze(html_2);
    //then
    boolean r_2 = result_2.equals("3");
    System.out.println("Checki if found 3 <li>: " + r_2);

    //check if there are 3 <li> even with embeded <ul> list inside master list
    //given
    String html_3 = "<html><body><p><ul><li>Content</li><li>Content</li><li><ul><li>Content</li><li>Content</li><li>Content</li></ul>/li></ul></p></body></html>";
    //when
    var result_3 = Parser.analyze(html_3);
    //then
    boolean r_3 = result_3.equals("3");
    System.out.println("Checki if found 3 <li> even with ebeded list: " + r_3);

  }
}
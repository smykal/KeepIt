package org.example.service;


import org.example.service.getHtml.Content;

public class GetHtmlTest {

  public static void main(String[] args) {
    //given
    String url = "http://zapiszto.pl";
    //when
    var result = Content.getHtml(url);
    //then
    boolean r = !result.isEmpty();
    System.out.println("GetHtmlTest passed" + r);


    //given
    String url2 = "http://keepit.com";
    //when
    var result2 = Content.getHtml(url2);
    //then
    boolean r2 = !result.isEmpty();
    System.out.println("GetHtmlTest passed" + r);
  }
}
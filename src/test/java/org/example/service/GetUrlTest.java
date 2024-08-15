package org.example.service;


class GetUrlTest {

  //check if URL is being recived
  public static void main(String[] args) {
    //given //when
    var result = GetUrl.getUrlFromUser();
    boolean r = !result.isEmpty();

    //then
    System.out.println("Check if url has been provided by user: " + r);
  }
}
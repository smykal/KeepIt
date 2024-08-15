1. project environment:
    * java 17 (temurin 17)

2. project structure:
    * maven

3. project requirements:
    * Write a program in a programming language of choice that receives the URL to a web site as a command line argument, --OK
retrieves the HTML web page content,                                                                                --OK
finds the HTML unordered list with the most direct children,                                                        --OK    
and returns the number of items in that list.                                                                       --OK    
Implement this using only the standard library.                                                                     --OK

        * project recives url as a command line input
        * project retrieves the HTML web page content
        * project finds the HTML unordered lists with the most direct children
        * project returns the number of items in that list
        * project is implemented by only standard library

4. handled issues:
   * GetUrl.class
     * -checking if is null
     * -checking by regex if is in valid format
   * GetHtml.class
     * -handling redirection: HTTP Status-Code 301: Moved Permanently / HTTP Status-Code 302: Temporary Redirect.
     * -handling when domain is not recognized: UnknownHostException
   * Parser.class
     * -findMaxDirectChilder - map <ul> / <li> structure
     * rest of methods prepare input for findMaxDirectChildren by few steps
     * handle embeded lists

5. tests
    * folder src/main/resources contains tests
    * tests are without external libraries like JUnit5 or Mockito
6. Main.class
    * main class is located at:  src/main/java/org/example/Main.java





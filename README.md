# Overview

- This repository will follow this [playlist](https://www.youtube.com/playlist?list=PL4gCdGOq-cxJrbRMWjrIvGhYqQO1tvYyX) using Spring and AngularJS.

- The video tutorial uses Spring 4.0.5 but I am using Spring 5.1.7

1. Notes for video #1.
    - Setting up the source of the Java compiler [here](https://maven.apache.org/plugins/maven-compiler-plugin/examples/set-compiler-source-and-target.html)  
    - This video discusses setting up Tomcat 7 but I am going to be using Tomcat 9
        - The documentation for this setup is [here](https://www.mkyong.com/intellij/intellij-idea-run-debug-web-application-on-tomcat/)
            - I am using IntelliJ ultimate but for users without the ultimate edition the article mentioned [Smart Tomcat](https://plugins.jetbrains.com/plugin/9492-smart-tomcat) 
    - This is the [documentation](https://docs.spring.io/spring/docs/5.0.0.M5/spring-framework-reference/htmlsingle/#mvc-config-conversion) 
    I used to assist with setting up the mvc-dispatcher-servlet.xml file.
        - I also used this same web page to look up the documentation for the <web-app> tag inside of web.xml.

2. Notes for video #2.
    - When creating the test directory make sure to mark it as a test directory.
        - To mark as a test directory I marked my java directory I opened File ---> Project Settings ---> Modules tab ---> 
        I selected the src/test/java directory and right clicked it then selected "Tests."
        
3. Notes for video #3.
    - This is the [GitHub page](https://github.com/FasterXML/jackson) discussing the necessary modules.
    - Since we have the HTTPJacksonMessageConverter then the standaloneSetup creates a series of message converters it
    does not use the configuration we have defined inside of our mvc-dispatcher-servlet.xml file.  
    - For the BlogEntryController and BlogEntryControllerTest I am using a lot of near identical methods just so I can have
    these as reference.
    - In the video for the 400 error it was shown that we must properly remember to format the JSON under the "content"
    when building the mock POST request.
    - JsonPath can check various parts of the object being passed in is correct.
        - JsonPath grabs elements out of a JSON object.
    - While following the video tutorial I forgot to use the "@RequestBody" annotation in the testPostBlogEntry method
    which led to getting a null value using the jsonPath operator to grab the value.

4. Notes for video #4.
    - HateOAS summary: The client should be able to interact with the REST endpoints completely through Hypermedia which
    allows the client and server to grow/be updated independently.
    - Note: I slightly differ from the naming convention presented in this video.
    - Another difference was that I decided to just create repositories and services without any implementations.

5. Notes for video #5.
    - The two classes in services.util are used by the "Asm" classes to assemble lists.
        - These two classes wrap our entities and wrap data (such as support for pagination).
    - For the failing test of createBlogExistingBlogName we are expecting a status of isConflict() so when we threw the
    ConflictException in the createBlog method of the AccountController we were able to fix this failing this.

6. Notes for video #6.
    - Below are some references to using the h2 database engine.
        - A link to the h2 database engine website is [here](https://www.h2database.com/html/cheatSheet.html).
        - The cheat sheet is [here](https://www.h2database.com/html/cheatSheet.html).
        - At about 20:39 there is a discussion about "transaction demarcation"
            - Entities attached during the transaction get detached after the transaction.
        - The documentation with the "tx" namespace is [here](https://docs.spring.io/spring/docs/4.0.6.RELEASE/spring-framework-reference/htmlsingle/)
        - It is important to note that we do not want to set the id for the Account object because it will be automatically
        be created for us when the Account object is created. 

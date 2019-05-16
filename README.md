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
        

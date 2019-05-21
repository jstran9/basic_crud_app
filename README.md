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

7. Notes for video #7.
    - When updating an entity we must remember to attach the entity to the persistence context by calling the corresponding
    find method of the entity we are updating.
    - When having trouble with properly injecting the services I had to add in extra configuration into the web.xml file
    using the <context-param/> and <listener/> tags.
    - When having issue with the h2 jdbc driver not loading I just had to remove the <test> scope from the pom.xml file
    for the h2 dependency.
    - I had issues with this error, "javax.persistence.TransactionRequiredException: No EntityManager with actual 
    transaction available for current thread" and the fix for this was that I had the improper component scan and I needed
    to scan the directory that all the controllers were inside of.
    - I had to remove the title field from the BlogEntryListResource class.

8. Notes for video #8.
    - Grunt file: a configuration of the Grunt build system and Bower.
        - Grunt is a task manager and is comparable to Ant.
        - Bower is a package manager that is comparable to Maven.
            - Grunt and Bower are taken together in the ng-boilerplate project.
    - We want to place the ng-boilerplate inside of the webapp directory and we can use a folder inside of webapp
    to publish it as static resources and have our JavaScript files updated in real time.
    - Inside of the Grunt.JS file we use a taskConfig JSONObject to set up different configurations for a variety of tasks.
        - For "copy" we should note that each copy task has a number of subtasks and each subtask has different parameters
        for specifying what to copy and where to copy it to. 
        - Grunt runs a different number of tasks in order to compile as few .js files as possible (minify) and for example
        if there are many views then Grunt will ideally output one file so only one file is needed to use multiple views.
    - We will be running "npm install" inside of the web-inf/app directory in order to produce contents inside of node_module.
    - Bower is used for client-side dependencies and front-end development.
    - File ".bowerrc" configures properties.
        - A directory to download Bower dependencies to
        - A configuration file for Bower, "bower.json"
        - The bower.json file is also read which contains a different number of client-side dependencies for the application.
        - To install the dependencies inside of bower.json we use the command below.
            - "bower install" (we run this command inside of the webapp/app directory)
        - File, "build.config.js" we can see that there is "vendor_files" which includes a number of different dependencies
        from the vendor folder.
    - Inside of web/app/index.html, there is "compiled javascript" and this is where Grunt loops through a number of files
    and for each file it prints out a JavaScript link to the file.
    - As we add to the vendor_files, the corresponding JavaScript link (script tag) containing the vendor file will also be
    added to our index.html file.
    - We will be more modifying contents inside of webapp/app/src/app.
    - To start the AngularJS application (currently ng boilerplate up to this point).
    - "Grunt watch" looks for changes in our JavaScript files and rebuilds the application whenever there is a change.

9. Notes for video #9.
    - ng-app: a directive that defines a root module to bootstrap the application.
        - a module that "asks" other modules in the application to configure themselves so that they can be properly
        initialized.   
    - ui.router uses "states"
        - states are a way to represent a part of a web page that can be navigated to. 
    - ui-view: a directive that specifies a place in the file that you would like to replace with a custom generated view.
        - ui-view="main" (from index.html) says that the <div> with the ui-view=main contains the main view.
            - note: the states on the web page can say if they will replace the main view.
    - $stateProvider: a service defined by ui.router that allows us to create different states.
        - stateProvider.state: allows us to create a "state" (parts of a page that we can navigate to)      
        - url key: if we go to the state then the address bar is changed to "/about" and if we go to the state then the
        address bar is changed to "/about." 
    - when we are doing "ng-model='account.name'" in the login.tpl.html we are creating an "account" object in the $scope
    object.
        - This allows us to get a user's name and password when we click on the submit button on the login page.
    - The controller (inside of app.js) binds data to the pageTitle variable inside of the $scope object and then in index.html
    using "ng-bind" we can specify a variable that was declared in the variable then we can specify in inside of our view
    (in this case index.html).

10. Notes for video #10.
    - ng-show, ng-hide, ng-click, ui-sref (changes state when user clicks a link), and service and local storage to store
    information.
    - factory method on account module.
        - a service in AngularJS is similar to a Spring singleton bean.
            - the service can be injected anywhere in the application and is a single instance.
    - for "ui-sref" directive:
        - each state has a unique name and we just specify the name.
            - for ui-sref="login" we would like to go to the login state when we click on the login button.     

11. Notes from video #11.
    - this tutorial focuses on allowing user to register/create new accounts and to login into these accounts.
        - ng-resource is used to interact with the RESTful endpoints.
    - to install angular resource, "bower install angular-resource"
        - I had to remove "json": "bower.json" from the .bowerrc file in order to execute the command
        and I ended up installing angular-resource-1.7.8 (in the video tutorial the version used is 1.2.22). 
        - This is the stackoverflow thread [here](https://stackoverflow.com/questions/36679810/bower-install-display-prompt-input-message-debian).
    - we added 'vendor/angular-resource/angular-resource.min.js' to the build.config.js file to include angular-resource
    in the build system.         

12. Notes from video #12.
    - 

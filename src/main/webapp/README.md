## simple lift web project ( liftweb.net ) for question-answer-comet example

How to:
- 1. check out the project
- 2. be sure, you have installed maven 3
- 3. go to the path of the pom.xml and execute following commands:
- 3.1. mvn clean install # clean may existing artifacts and compile, build, package the project and finally install it into your local repository
- 3.2. ( if you have jRebel installed ) mvn scala:cc # enables continuous compilation if you want to edit the project
- 3.3. mvn jetty:run # will start the embedded jetty server. Go to localhost:8080/lift to start the web app

NOTICE:
In this example all javascript code is placed inside the html file. Normally it should be better to create an extra js
file for that stuff.
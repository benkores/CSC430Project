# CSC430Project

Before running program for the first time:

Step 1: Create the database:

   1) Complete all of step 1: https://db.apache.org/derby/papers/DerbyTut/install_software.html
   2) Launch database server with:```java -jar "C:\Apache\db-derby-10.16.1.1-bin\lib\derbyrun.jar" server start -noSecurityManager```
   3) Start ij (in new cmd window) with: ```java -jar "C:\Apache\db-derby-10.16.1.1-bin\lib\derbyrun.jar" ij```
   4) Create teamviper database with: ```CONNECT 'jdbc:derby://localhost:1527/my_database;create=true;user=teamviper;password=teamviper';```

Step 2: Execute all SQL files in sql folder with an SQL editor

 Step 3: add all jars in C:\Apache\db-derby-10.16.1.1-bin\lib to build path in your IDE

Before running program:

  Launch database server with:```java -jar "C:\Apache\db-derby-10.16.1.1-bin\lib\derbyrun.jar" server start -noSecurityManager```


Connecting to database after first time (for manual entries):
   1) Launch database server with: ```java -jar "C:\Apache\db-derby-10.16.1.1-bin\lib\derbyrun.jar" server start -noSecurityManager```
   2) Start ij (in new cmd window) with: ```java -jar "C:\Apache\db-derby-10.16.1.1-bin\lib\derbyrun.jar" ij```
   3) Connect to teamviper database with: ```CONNECT 'jdbc:derby://localhost:1527/my_database;user=teamviper;password=teamviper';```

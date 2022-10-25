# CSC430Project

Creating database:

1) Complete all of step 1: https://db.apache.org/derby/papers/DerbyTut/install_software.html
2) Launch database server with:```java -jar "C:\Apache\db-derby-10.16.1.1-bin\lib\derbyrun.jar" server start -noSecurityManager```
4) Start ij with: ```java -jar "C:\Apache\db-derby-10.16.1.1-bin\lib\derbyrun.jar" ij```
5) Create teamviper database with: ```CONNECT 'jdbc:derby://localhost:1527/my_database;create=true;user=teamviper;password=teamviper';```

Connecting to database after first time:
1) Launch database server with: ```java -jar "C:\Apache\db-derby-10.16.1.1-bin\lib\derbyrun.jar" server start -noSecurityManager```
2) Start ij with: ```java -jar "C:\Apache\db-derby-10.16.1.1-bin\lib\derbyrun.jar" ij```
3) Connect to teamviper database with: ```CONNECT 'jdbc:derby://localhost:1527/my_database;user=teamviper;password=teamviper';```

Make sure you execute all sql files in sql folder to create tables and insert example values AND you launch the database server before running the program!

Shutdown database server:
```java -jar "C:\Apache\db-derby-10.16.1.1-bi\lib\derbyrun.jar" server shutdown```
 
If any issues with SQLConnect add all jars in C:\Apache\db-derby-10.16.1.1-bin\lib to build path in your IDE

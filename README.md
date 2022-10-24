# CSC430Project

Creating database:

1) Complete all of step 1: https://db.apache.org/derby/papers/DerbyTut/install_software.html
2) Launch database server with:
   '''java -jar "C:\Apache\db-derby-10.16.1.1-bin\lib\derbyrun.jar" server start -noSecurityManager'''
4) Start ij with: java -jar "C:\Apache\db-derby-10.16.1.1-bin\lib\derbyrun.jar" ij
5) Create teamviper database with: CONNECT 'jdbc:derby://localhost:1527/my_database;create=true;user=teamviper;password=teamviper';

Connecting to database after first time:
1) Launch database server with: java -jar "C:\Apache\db-derby-10.16.1.1-bin\lib\derbyrun.jar" server start -noSecurityManager
2) Run SQLConnect.Java

Shutdown database server:
java -jar "C:\Apache\db-derby-10.16.1.1-bi\lib\derbyrun.jar" server shutdown
 
If any issues with SQLConnect or Material GUI add all jars in C:\Apache\db-derby-10.16.1.1-bin\lib and CSC430Project\material to build path in your IDE

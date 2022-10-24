# CSC430Project

Connecting to database (FIRST TIME):

1) Complete all of step 1: https://db.apache.org/derby/papers/DerbyTut/install_software.html
2) Launch database server with: java -jar "C:\Apache\db-derby-10.16.1.1-bin\lib\derbyrun.jar" server start -noSecurityManager
3) Start ij with: java -jar "C:\Apache\db-derby-10.16.1.1-bin\lib\derbyrun.jar" ij
4) Create teamviper database with: CONNECT 'jdbc:derby://localhost:1527/my_database;create=true;user=teamviper;password=teamviper';

Connecting to database after first time:
1) Launch database server with: java -jar "C:\Apache\db-derby-10.16.1.1-bin\lib\derbyrun.jar" server start -noSecurityManager
2) Run SQLConnect.Java


If any issues with SQLConnect or Material GUI add all jars in C:\Apache\db-derby-10.16.1.1-bin\lib and CSC430Project\material to build path
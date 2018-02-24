README
====

This solution is built on using MySql as the database.
It there is no mysql database ready it can be created in minutes on docker :

To start the database and the simple web based admin tool: 
run 'docker-compose up' in the directory of this file

After a minute or two you will see something similar to this:

db_1       | 2018-02-18T09:13:45.220563Z 0 [Note] mysqld: ready for connections.
db_1       | Version: '5.7.21'  socket: '/var/run/mysqld/mysqld.sock'  port: 3306  MySQL Community Server (GPL)

The next step is to setup the database: 
Use the Adminer web based interface on  http://<server>/8090
Login as 'root' with the password from the MYSQL_ROOT_PASSWORD in the docker-compose.yml file in this directory

Run these commands
create database expenses

create user 'expenses'@'%' identified by <password> ;
	
grant all PRIVILEGES  on expenses.* to  'expenses'@'%';


To build and deploy this application on Tomcat 
 run 'mvn clean install' in the directory  <root>/solution/expenses
 
This will create the war-file app.war in <root>/solution/expenses/target
Copy that file to <TomcatHome>/webapps 

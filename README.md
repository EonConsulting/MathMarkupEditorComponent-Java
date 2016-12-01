# MathMarkupEditorComponent-Java

Project Title

Mathematical notations are even more complex with structures like fractions, square roots or matrices that are likely to require their own tags. As a consequence,a good MathMarkupEditor is more important and we describe some tools below. In particular, the Javascript Unicode LaTeX-to-MathML converter that is intended to be used in many scenarios described here. Of course, the list is by no means exhaustive and you are invited to check out the MathMarkupEditor where you can find various other tools.

Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

Prerequisites

Install GIT so that it works at the command prompt.

Install a PHP/MySQL Environment like XAMPP / MAMP following the instructions at:

http://www.wa4e.com/install.php

To install this software follow these steps:

Installation

Check the code out from GitHub and put it in a directory where your web server can read it

git clone https://github.com/csev/tsugi.git
Checkout and compile the Java Tsugi Library so that it is in your maven repository
Create a database and get authentication info for the database

CREATE DATABASE tsugi DEFAULT CHARACTER SET utf8;
GRANT ALL ON tsugi.* TO 'ltiuser'@'localhost' IDENTIFIED BY 'ltipassword';
GRANT ALL ON tsugi.* TO 'ltiuser'@'127.0.0.1' IDENTIFIED BY 'ltipassword';
Copy the file config-dist.php to config.php and edit the file to put in the appropriate values. Make sure to change all the secrets. If you are just getting started turn on DEVELOPER mode so you can launch the tools easily. Each of the fields is documented in the config-dist.php file - here is some additional documentation on the configuration values:

http://do1.dr-chuck.com/tsugi/phpdoc/classes/Tsugi.Config.ConfigInfo.html

Go to the main page, and click on "Admin" to make all the database tables - you will need the Admin password you just put into config.php If all goes well, lots of tables should be created. You can run upgrade.php more than once - it will automatically detect that it has been run.

At that point you can play with and/or develop new tools

Note: Make sure that none of the folders in the path to the tsugi folder have any spaces in them. You may get signature errors if you use folders with blanks in them.

MAMP NOTES (Macintosh)

cd /Applications/MAMP/htdocs/
git clone https://github.com/csev/tsugi.git
cd tsugi
cp config-dist.php config.php

edit config.php using a text editor - some values

Make sure to change $wwwroot to reflect where your server is 
hosted or the CSS files will not be loaded.

$wwwroot = 'http://localhost:8888/tsugi';
$CFG->pdo = 'mysql:host=127.0.0.1;port=8889;dbname=tsugi';
$CFG->dbprefix  = '';
$CFG->adminpw = ....;

Make a database using PhpMyAdmin:

CREATE DATABASE tsugi DEFAULT CHARACTER SET utf8;
GRANT ALL ON tsugi.* TO 'ltiuser'@'localhost' IDENTIFIED BY 'ltipassword';
GRANT ALL ON tsugi.* TO 'ltiuser'@'127.0.0.1' IDENTIFIED BY 'ltipassword';

Visit  http://localhost:8888/tsugi and go to 'Admin' and enter the
adminpw to automatically create all necessary tables.
XAMPP NOTES (Windows)

cd \xampp\htdocs
git clone https://github.com/csev/tsugi.git
cd tsugi
copy config-dist.php config.php

edit config.php using a text editor - some values

Make sure to change $wwwroot to reflect where your server is 
hosted or the CSS files will not be loaded.

$wwwroot = 'http://localhost/tsugi';
$CFG->dbprefix  = '';
$CFG->adminpw = ....;

Make a database using PhpMyAdmin:

CREATE DATABASE tsugi DEFAULT CHARACTER SET utf8;
GRANT ALL ON tsugi.* TO 'ltiuser'@'localhost' IDENTIFIED BY 'ltipassword';
GRANT ALL ON tsugi.* TO 'ltiuser'@'127.0.0.1' IDENTIFIED BY 'ltipassword';

Visit  http://localhost/tsugi and go to 'Admin' and enter the
adminpw to automatically create all necessary tables.
If you are setting this up on some variation of Linux, the Macintosh instructions will be the most help.

Running the tests

Build / Run

If you are running on Windows, you need to change the details in the file

./src/main/resources/tsugi.properties
Change a line to be something like:

tsugi.datasource.url=jdbc:mysql://localhost:3306/tsugi
Sorry about the Mac/MAMP-friendly defaults.

mvn clean compile install jetty:run
Then navigate to

http://localhost:8080/tsugi-servlet/hello
It will say something like this:

HTTP ERROR: 500
This tool must be launched using LTI
RequestURI=/tsugi-servlet/hello
It does not talk to the database if it rejects the request.

Launching with LTI

To launch this app, go to:

https://online.dr-chuck.com/sakai-api-test/lms.php
Launch to:

http://localhost:8080/tsugi-servlet/hello
12345 / secret
If all goes well for the launch, your Java Tsugi application will be showing in the window with some kind of output like:

Welcome to hello world!
Click here to see if we stay logged in with a GET
Content Title: Introduction to Programming
Context Settings: {"zap":"1234","count":"1"}
If your database connection is mis-configured or not working in the Tsugi servlet you will be splashed with plenty of traceback in the iframe:

HTTP ERROR: 500
Database server is down or tsugi database is missing
RequestURI=/tsugi-servlet/hello
Caused by:
java.lang.RuntimeException: Database server is down or tsugi database is missing
at org.tsugi.impl.jdbc.Tsugi_JDBC.getConnection(Tsugi_JDBC.java:82)
at org.tsugi.impl.jdbc.Tsugi_JDBC.getLaunch(Tsugi_JDBC.java:114)
at org.tsugi.base.BaseTsugi.getLaunch(BaseTsugi.java:51)
at org.tsugi.sample.TsugiServlet.doGet(TsugiServlet.java:50)
...
Fix the tsugi.properties and try again.


Built With

Spring MVC
Maven - Dependency Management


Versioning

We use SemVer for versioning. For the versions available, see the tags on this repository.

Authors

Quintin de clarcq
Benedict M Pabazhira 
Isaac Motale Mbaka 

License

This project is licensed under the University of South Africa and/or its affiliates License - see the LICENSE.md file for details


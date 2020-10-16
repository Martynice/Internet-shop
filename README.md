# Internet-shop
Internet-shop with basic operations. 

In this project used N-tier architecture with DB layer, DAO layer, Service layer, Controllers layer and View layer.<br>
Project was developed according to SOLID principles with authorization and authentication by RBAC filter strategy.

One user can have multiple roles.<br>
##### No role:<br>
  - Registration
  - Authorization
  - View products
##### User:<br>
  - View products
  - Add / remove products to / from shopping cart
  - Make an order
  - Logout
##### Admin:<br>
  - View / delete users
  - View / add / remove products
  - View / delete user orders.
  - Logout

# Used technologies<br>
**Backend:** Java, Servlets, Tomcat, JDBC, Log4j<br>
**Frontend:** HTML, CSS, Bootstrap, JSP, JSTL<br>
**Database:** MySQL<br>

# To run this project you need do several steps

* Download, install and configure JDK https://www.oracle.com/java/technologies/javase-downloads.html
* Download and install IDEA Ultimate Edition
* Download, install and configure Tomcat https://tomcat.apache.org/download-90.cgi
    * Configure Tomcat in IDEA
        * Application server > add
        * Deployment > add war_exploded
        * Deployment > Application context: \
* Download, install and configure MySQL WorkBench https://dev.mysql.com/downloads/installer/
    * Setup new connection with 
        * Username: root
        * Password: 1234
        * Port: 3306
        * Hostname: 127.0.0.1
    * Create schema internet_shop
    * Create tables using command from init_db.sql file

# Available for admin (login: admin, password: 1111)

# Author 
[Andrew Nepyivoda](https://github.com/Martynice)

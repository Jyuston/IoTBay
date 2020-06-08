# Introduction
This project consists the intial R0 architecture spike (software prototype) of the IoTBay webstore application.

# Deployment Information
The project has been packaged into a .war file - the web application that can be deployed on jsp containers and serverlets. Alternativley, the project can be launched through supported IDEs (e.g. Apache NetBeans).

On a Windows machine, the .war file can be renamed to .zip. Then, by extracting the zip folder, source files are available for viewing. This includes the application model (java classes), JSP pages, and supporting css (bootstrap framework and css customisation). 

Note, the directory structure should be preserved to ensure that the css files are loaded correctly.

### Database Configuration
1. Create a new Java Database
2. Record the database name, username, password, and connection string.
3. Start the database `asadmin start-database` or via other methods E.g NetBeans
3. Execute the provided database scripts (creation and insertion scripts found within `web/db_scripts`).
4. Within the DBConnection.java file (within the DAO package), update the information in the connection information.
   Ensure that the URL, username, and password information match with the database details created.

### Prerequisites
1. You must have Glassfish Server installed along with Java EE 8 on your machine

### Steps to Deploy
1. Open the folder in which your glassfish installation is located (on a Windows machine - typically in C:\Users\<your user name>\GlassFish_Server\bin)
2. Using a terminal, start the glassfish server `asadmin start-domain`
3. Deploy the war file `asdmin deploy path/to/IoTBay.war`
4. Open the application by navigating to 'http://localhost:8080/IoTBay/' in a browser
5. To stop the server, run `asadmin undeploy IoTBay`, `asadmin stop-domain` and finally `asadmin stop-database`.
 

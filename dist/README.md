# Introduction
This project consists the intial R0 architecture spike (software prototype) of the IoTBay webstore application.

# Project Outline
This project consists of the following files:

|File/Item | Description|
| --- | --- |
|index.jsp | Is the index page that users are presented with when the application is first started. Users are also redirected to it when they are logged out. |
|login.jsp | Contains the login form. When the form is submitted, the data is 'POST'ed to the welcome page. |
|register.jsp | Contains the registration form. When the form is submitted, the data is 'POST'ed to the welcome page. |
|welcome.jsp | Retrieves the request data and insantiates a JavaBean with the user adata. |
|mainPage.jsp | mainPage.jsp retrievs the customer javabean from the session and displays a registered users' information in a table.|
|logout.jsp | When the logout hyperlink is pressed, the user is redirected to this page. The session is invalidated and the user is provided with a link to the index.jsp page. |
|editInformation.jsp | This page allows a user to edit the information that they have supplied. The javabean is retrieved from session and displayed to the user in a form. When the user submits the form, the data is posted to editInformation.jsp and the javabean in the session updated. |
|boostrap.css| Contains the Bootstrap css framework. Bootstrap is an open source toolkit for developing with HTML, CSS, and JS. Used for styling and element poistioning.|
|workshop.css| Applies customisations on top of bootstrap css. This can achieved as css applies in a cascading manner. |
|Customer.java| Contains the class definition (blueprint) for the Customer javabean. This class extends the 'User' class.|
|Order.java| Contains the class definition (blueprint) for the Order javabean. |
|PaymentInformation.java| Contains the class definition (blueprint) for the Order javabean. |
|Product.java| Contains the class definition (blueprint) for the Product javabean. |
|ProductSnapshot.java| Contains the class definition (blueprint) for the ProductSnapshot javabean. This captures a product's information at the time of order. It is equivalent to the 'OrderLine' entity within the database. This information is captured to maintain correct multiplicities, correct cardinality within the database - thereby preventing data anamolies. |
|User.java| Contains Contains the class definition (blueprint) for the User javabean.|

# Deployment Information
The project has been packaged into a .war file - the web application that can be deployed on jsp containers and serverlets. Alternativley, the project can be launched through supported IDEs (e.g. Apache NetBeans).

On a Windows machine, the .war file can be renamed to .zip. Then, by extracting the zip folder, source files are available for viewing. This includes the application model (java classes), JSP pages, and supporting css (bootstrap framework and css customisation). 

Note, the directory structure should be preserved to ensure that the css files are loaded correctly.

## Pre-reqs

Prerequisites:
1. You must have Glassfish Server installed along with Java EE 8 on your machine

Steps:
1. Open the folder in which your glassfish installation is located (on a Windows machine - typically in C:\Users\<your user name>\GlassFish_Server\bin)
2. Using terminal / command prompt / PowerShell run the asadmin file along side start-domain - 'asadmin start-domain'
3. Then deploy the war file by using {asdmin deploy <directoryPath>\13571649-ISD-A1-R0.war}
4. The application will then be deployed on your local host on port 8080 by default.
5. To open the application, open a browser to 'http://localhost:8080/13571649-ISD-A1-R0/'
6. To stop running the server in terminal write the command 'asadmin stop-domain' while in the bin directory holding the asadmin file.
7. Press 'Ctrl-C' to stop the command execution.
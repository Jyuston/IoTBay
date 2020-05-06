<%-- 
    Document    welcome
    Created on  8 Apr 2020, 21907 pm
    Author      denni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.Customer"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/workshop.css">
        <title>IoT Bay</title>
    </head>
    
    <%
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String dateOfBirth = request.getParameter("dob");
        String email = request.getParameter("email");              
        String contactNumber = request.getParameter("contactNumber");
        String streetAddressLine1 = request.getParameter("streetAddressLine1");
        String streetAddressLine2 = request.getParameter("streetAddressLine2");
        String suburb = request.getParameter("suburb");
        String postcode = request.getParameter("postCode");
        String state = request.getParameter("state");
        String cardNumber = request.getParameter("cardNumber");
        String cardCVC = request.getParameter("cardCVC");
        String expiryMonth = request.getParameter("expiryMonth");
        String expiryYear = request.getParameter("expiryYear");
        
        
        String registrationFormSubmitted = request.getParameter("registrationFormSubmitted");
    %>
    
    <%
       if (registrationFormSubmitted != null && registrationFormSubmitted.equals("yes")) {
        Customer customer = new Customer(firstName, lastName, password, dateOfBirth, email, contactNumber, streetAddressLine1, streetAddressLine2, suburb, postcode, state, cardNumber, cardCVC, expiryMonth, expiryYear);
        session.setAttribute("customer", customer);
        
        String entranceMethod = "registration";
        session.setAttribute("entranceMethod", entranceMethod);
       }
       
       else {
        String entranceMethod = "logIn";
        session.setAttribute("entranceMethod", entranceMethod);
        session.setAttribute("enteredEmail", email);
       }
   
    %>
    
    <body>
        <div class="container">        
            <nav class="navbar navbar-expand-lg fixed-top navbar-light bg-light" style="background-color: #e3f2fd;">
                <span class="navbar-brand mb-0 h1">IoTBay</span>
                <div class="navbar-nav mr-auto mt-2 mt-lg-0">
                    <a class="nav-item nav-link border-top active" href="#">Home</a>
                </div>
                <a class="nav-item nav-link my-2 my-sm-0" href="logout.jsp">Logout</a>
            </nav>
            
            <div class="row">
                <div class="col">
                    
                </div>
                
                <div class="col--md-auto">
                    <h1>Welcome, <%= email %></h1>                       
                    <a class="btn btn-primary" href="mainPage.jsp">CLICK HERE TO ENTER THE MAIN PAGE</a>
                </div>
                
                <div class="col">
                </div>
            </div>
        </div>
    </body>
</html>

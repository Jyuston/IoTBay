<%-- 
    Document   : Main Page
    Created on : 8 Apr 2020, 2:57:07 pm
    Author     : denni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/workshop.css">
        <title>IoT Bay</title>
    </head>
    
    <%
        Customer customer = (Customer)session.getAttribute("customer");
    %>
    <body>
        <div class="container">        
            <nav class="navbar navbar-expand-lg fixed-top navbar-light bg-light" style="background-color: #e3f2fd;">
                <span class="navbar-brand mb-0 h1">IoTBay</span>
                <div class="navbar-nav mr-auto mt-2 mt-lg-0">
                    <a class="nav-item nav-link border-top active" href="#">Home</a>
                </div>
                <a class="nav-item nav-link my-2 my-lg-0" href="logout.jsp">Logout</a>                
            </nav>
            
            <div class="row">
                <div class="col">                    
                </div>
                
                <div class="col-6">                   
                    <h1>Welcome, ${customer.firstName}!</h1>
                    <h2> A little about you... </h2>                    
                    <p>Here's what you've told us about yourself so far: </p>
                    <div class="table-responsive">
                        <table class="table">
                            <thead class="thead-dark">
                                <tr class="table-light">
                                    <th scope="col">#</th>
                                    <th scope="col">Attribute</th>
                                    <th scope="col">Value</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="table-light">
                                    <th scope="row"></th>
                                    <td>First Name</td>
                                    <td>${customer.firstName}</td>
                                </tr>
                                <tr class="table-light">
                                    <th scope="row"></th>
                                    <td>Last Name</td>
                                    <td>${customer.lastName}</td>
                                </tr>
                                <tr class="table-light">
                                    <th scope="row"></th>
                                    <td>Email</td>
                                    <td>${customer.email}</td>
                                </tr>
                                <tr class="table-light">
                                    <th scope="row"></th>
                                    <td>Date of Birth</td>
                                    <td>//</td>
                                </tr>
                                <tr class="table-light">
                                    <th scope="row"></th>
                                    <td>Address Line 1</td>
                                    <td>${customer.addressLine1}</td>
                                </tr>
                                <tr class="table-light">
                                    <th scope="row"></th>
                                    <td>Address Line 2</td>
                                    <td>${customer.addressLine2}</td>
                                </tr>
                                <tr class="table-light">
                                    <th scope="row"></th>
                                    <td>Suburb</td>
                                    <td>${customer.suburb}</td>
                                </tr>
                                <tr class="table-light">
                                    <th scope="row"></th>
                                    <td>Post Code</td>
                                    <td>${customer.postCode}</td>
                                </tr>
                                <tr class="table-light">
                                    <th scope="row"></th>
                                    <td>State</td>
                                    <td>${customer.state}</td>
                                </tr>
                                <tr class="table-light">
                                    <th scope="row"></th>
                                    <td>Card Number</td>
                                    <td>${customer.customerPaymentInfo.cardNumber}</td>
                                </tr>
                                <tr class="table-light">
                                    <th scope="row"></th>
                                    <td>CVV</td>
                                    <td>${customer.customerPaymentInfo.cvv}</td>
                                </tr>                            
                                <tr class="table-light">
                                    <th scope="row"></th>
                                    <td>Card Expiry Month</td>
                                    <td>${customer.customerPaymentInfo.expiryMonth}</td>
                                </tr>
                                <tr class="table-light">
                                    <th scope="row"></th>
                                    <td>Card Expiry Year</td>
                                    <td>${customer.customerPaymentInfo.expiryYear}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>            
                
                <div class="col">
                </div>
            </div>
        </div>
    </body>
</html>

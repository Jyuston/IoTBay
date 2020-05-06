<%-- 
    Document   : logout
    Created on : 8 Apr 2020, 2:57:07 pm
    Author     : denni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.User"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/workshop.css">
        <title>IoT Bay</title>
    </head>
    
    <%
        session.invalidate();
    %>
    <body>
        <div class="container">        
            <nav class="navbar fixed-top navbar-light bg-light" style="background-color: #e3f2fd;">
                <span class="navbar-brand mb-0 h1">IoTBay</span>              
                <a class="nav-item nav-link active my-2 my-lg-0 border-top" href="index.jsp">Logout</a>                
            </nav>
            
            <div class="row">
                <div class="col">                    
                </div>
                
                <div class="col--md-auto">                   
                    <p> You are now logged out.</p>                                      
                    <a class="btn btn-primary" href="index.jsp">Return Home</a>
                </div>
                
                <div class="col">
                </div>
            </div>
        </div>
    </body>
</html>
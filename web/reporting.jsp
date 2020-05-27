<%-- 
    Document    index
    Created on  8 Apr 2020, 21907 pm
    Author      denni
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

    <body>
        <div class="container">        
            <nav class="navbar navbar-expand-lg fixed-top navbar-light bg-light" style="background-color: #e3f2fd;">
                <span class="navbar-brand mb-0 h1">IoTBay</span>
                <div class="navbar-nav mr-auto mt-2 mt-lg-0">
                    <a class="nav-item nav-link border-top" href="#">Home</a>
                </div>                
                <a class="nav-item nav-link border-top active" href="#">Reporting</a>
                <a class="nav-item nav-link my-2 my-lg-0" href="logout.jsp">Logout</a>                
            </nav>
            
            <div class="row">
                <div class="col">
                    
                </div>
                
                <div class="col--md-auto">
                    <h1>Welcome!</h1>                       
                    <p>Select a report to view it.</p>
                </div>
                
                <div class="col">
                </div>
            </div>
        </div>
    </body>
</html>

<%-- 
    Document    login
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
    
    <body>
        <div class="container">        
            <nav class="navbar navbar-expand-lg fixed-top navbar-light bg-light" style="background-color: #e3f2fd;">
                <span class="navbar-brand mb-0 h1">IoTBay</span>
                <div class="navbar-nav mr-auto mt-2 mt-lg-0">
                    <a class="nav-item nav-link border-top active" href="index.jsp">Home</a>
                </div>
            </nav>
            
            <div class="row">
                <div class="col">
                    
                </div>
                
                <div class="col--md-auto">
                    <h1 id="loginHeader"> Returning Customer? </h1>
                    <p> Login Below: </p>
                    <form class="form-signin" method="post" action="Welcome.jsp">
                        <label for="inputEmail" class="sr-only">Email address</label>
                        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" name="email" required autofocus>
                        <label for="inputPassword" class="sr-only">Password</label>
                        <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
                        <input class="form-control" type="hidden" name="loginFormSubmitted" value="yes">
                        <a href="Welcome.jsp"><input type="submit" class="btn btn-primary btn-lg btn-block" value="Sign In"></a>
                    </form>                        
                </div>
                
                <div class="col">
                </div>
            </div>
        </div>
    </body>
</html>

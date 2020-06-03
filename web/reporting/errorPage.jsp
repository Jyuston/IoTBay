<%-- 
    Document    reporting
    Created on  8 Apr 2020, 21907 pm
    Author      denni
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="uts.isd.model.reporting.*" %>
<%@ page import="uts.isd.controller.reporting.*" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/workshop.css">
        <title>IoT Bay</title>
    </head>
    <jsp:include page="/templates/header.jsp"/>

    <%
        String errorMessage = (String)request.getAttribute("error");
        
    %>
    <body>
        <div class="alert alert-danger my-4" role="alert">
            <h1>Oops. Something went wrong.</h1>
            <p class="lead">An error occured whilst processing your request. Please see the attached error message.</p>
            <% out.println(errorMessage); %>
        </div>
        <a href="ReportingServlet" class="btn btn-primary btn-lg btn-block">Return to Reports</a>
    </body>

    <jsp:include page="/templates/footer.jsp"/>
</html>

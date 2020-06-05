<%-- 
    Document    reporting
    Created on  8 Apr 2020, 21907 pm
    Author      denni
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="uts.isd.model.reporting.*" %>
<%@ page import="uts.isd.controller.reporting.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/workshop.css">
        <title>IoT Bay</title>
    </head>
    <jsp:include page="templates/header.jsp"/>

    <%
        Report report = (Report)session.getAttribute("report");
        DecimalFormat revenueFormatter = new DecimalFormat("####0.00");
    %>
    
    <body>
        <h1>${report.name} </h1>
        <p>${report.description} </p>
        <form method="POST" action="/IoTBay/ReportingServlet">
            <input class="form-control" type="hidden" name="reportExit" value="yes">
            <input type="submit" class="btn btn-primary" value="Exit"></a>
        </form>

        <br>
        <br>
        
        <h2> Stock by Category by Product</h2>

        <c:forEach items="${report.stockReport}" var="entry" varStatus="loop">
                    <h4>${entry.key}</h4>
                    <div class="table-responsive-sm">
                        <table class="table table-bordered table-sm table-dark">
                            <thead class="thead-light">
                                <th scope="col">Product ID</th>
                                <th scope="col">Product Name</th>
                                <th scope="col">Stock</th>
                            </thead>
                            <tbody>
                                <c:forEach items="${entry.value}" var="item" varStatus="loop">
                                        <tr>
                                            <td>${item.ID}</td>
                                            <td>${item.name}</td>
                                            <td>${item.unitsSold}</td>                                            
                                        </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>       
                </c:forEach>
    </body>
        
    <jsp:include page="templates/footer.jsp"/>
</html>

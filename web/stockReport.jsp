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
    
    <body>
        <h1>${report.name} </h1>
        <p>${report.description} </p>

        <form method="POST" action="/IoTBay/ReportingServlet">
            <%-- Form for triggering logic to exit the report view. %--%>
            <input class="form-control" type="hidden" name="reportExit" value="yes">
            <input type="submit" class="btn btn-primary" value="Exit"></a>
        </form>

        <br>
        <br>
        
        <h2> Stock by Category by Product</h2>
        <%-- Iterates through the hashmap to display each category. --%>
        <%-- JSTL abstracts this process, preventing the need to explicity code in use of keys etc. --%>
        <c:forEach items="${report.stockReport}" var="entry" varStatus="loop">
                    <h4>${entry.key}</h4>
                    <div class="table-responsive-sm">
                        <table id="reportingTable" class="table table-bordered table-sm table-dark">

                            <thead class="thead-light">
                                <th scope="col">Product ID</th>
                                <th scope="col">Product Name</th>
                                <th scope="col">Stock</th>
                            </thead>

                            <tbody>
                                <%-- Iterates through the ArrayList stored inside the value of the hashmap key to display each category. --%>
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

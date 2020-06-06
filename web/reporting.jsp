<%-- 
    Document    reporting
    Created on  8 Apr 2020, 21907 pm
    Author      denni
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="uts.isd.model.reporting.*" %>
<%@ page import="uts.isd.controller.reporting.*" %>
<%@ page import="java.util.ArrayList" %>
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

    <c:choose>
        <c:when test="${not empty error}">
            <div class="alert alert-danger my-4" role="alert">
                <h1>Oops. Something went wrong.</h1>
                <p class="lead">An error occured whilst processing your request. Please see the attached error message.</p>
                <p>${error}</p>
            </div>
            <a href="ReportingServlet" class="btn btn-primary btn-lg btn-block">Return to Reports</a>
        </c:when>
    
        <c:otherwise>   
            <body>
                <div class="container">     
                    <div class="row">
                        
                        <div class="col-2">
                        </div>
                        
                        <div class="col-8">
                            <h1>Reporting</h1>
                            <p>Select an existing report to view, or create a new report. </p>
                            <h2> View Existing Reporting</h2>
                            <form method="post" action="ReportingServlet">
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label>Report Name</label>
                                        <select class="form-control" name="selectedReport" required">
                                            <option>Stock Report</option>
                                            <c:forEach items="${reportNames}" var="name" varStatus="loop">
                                               <option>${name}</option> 
                                            </c:forEach>
                                        </select>
                                        <input type="submit" class="btn btn-primary" value="View Report"></a>
                                    </div>
                                    
                                    <div class="form-group col-md-6">
                                        <input class="form-control" type="hidden" name="existingReportSelected" value="yes">
                                    </div>                        
                                </div>
                            </form>

                            <br>
                            <br>
                            <h2>New Sales Report</h2>
                            <c:choose>
                                <c:when test="${user.admin}">
                                    <jsp:include page="/reportForm.jsp"/>
                                </c:when>

                                <c:otherwise>
                                    <p class="font-weight-light">If you would like a new report to be created, please contact your system administrator with your requirements.</p>
                                </c:otherwise>
                            </c:choose>
                           
                        </div>
                    </div>
                </div>
            </body>
        </c:otherwise>
    </c:choose>

    <jsp:include page="templates/footer.jsp"/>
</html>

<%-- 
    Document    reportForm
    Created on  8 Apr 2020, 21907 pm
    Author      denni
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="uts.isd.model.reporting.*" %>
<%@ page import="uts.isd.controller.reporting.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/workshop.css">
    </head>
    
    <%-- Declares a global variable. --%>
    <%! String action; %>

    <c:choose>
        <%-- Check if an error occured. If so, display the error message to the user. --%>
        <c:when test="${not empty error}">
            <%-- Includes the header for page styling purposes--%>
            <jsp:include page="templates/header.jsp"/>

            <div class="alert alert-danger my-4" role="alert">
                <h1>Oops. Something went wrong.</h1>
                <p class="lead">An error occured whilst processing your request. Please see the attached error message.</p>
                <p>${error}</p>
            </div>

            <%-- 'Closes' the error message by returning the user back to the form. --%>
            <a href="ReportFormServlet" class="btn btn-primary btn-lg btn-block">Return</a>
        </c:when>

        <%-- No error, show the form.--%>
        <c:otherwise>
            <c:choose>
                <%-- As page is being used, logic is implemented to determine if it being used to create a new report, or update report. --%>
                <c:when test="${not empty editReport}">
                    <%-- Set the form usage type (for styling and form purposes). --%>
                    <c:set var="action" scope="request" value="updateReport"/>
                    <%-- Header included for styling purposes (navbar). --%>
                    <jsp:include page="templates/header.jsp"/>
                    <h1> Edit Report Details </h1>
                </c:when>

                <c:otherwise>
                    <c:set var="action" scope="request" value="newReportCreated"/>                  
                </c:otherwise>
            </c:choose>
            
            <body>
                <%-- Displays the report form. If the form is being used to edit a report, form controls are pre-filled. --%>
                <form method="post" action="/IoTBay/ReportFormServlet">
                    <div class="form-row">                                                                         
                        <div class="form-group col-md-6">                                           
                            <label>Report Name</label>
                            <input class="form-control" type="text" name="reportName" value="${editReport eq 'true' ? report.name : ''}">
                        </div>
    
                        <div class="form-group col-md-6">
                            <label>Report Description</label> 
                            <input class="form-control" type="text" name="reportDescription" value="${editReport eq 'true' ? report.description : ''}">
                        </div>
                    </div>
    
                    <div class="form-row">
                        <div class="form-group col-md-6">                                           
                            <label>Report Start Date</label>
                            <input class="form-control" type="date" name="startDate" value=${editReport eq 'true' ? report.startDate : ''}>
                        </div>
    
                        <div class="form-group col-md-6">
                            <label>Report End Date</label> 
                            <input class="form-control" type="date" name="endDate" value=${editReport eq 'true' ? report.endDate : ''}>
                        </div>
                    </div>

                    <c:choose>
                        <%-- Set the value of the hidden attribute based on what the form is being used for. --%>
                        <c:when test="${editReport eq 'true'}">
                            <input class="form-control" type="hidden" name="updateReport" value="yes">
                            <input type="submit" class="btn btn-success" value="Update Report"></a>
                            <a class="btn btn-warning" href="/IoTBay/reportView.jsp">Cancel</a>
                        </c:when>

                        <c:otherwise>
                            <input class="form-control" type="hidden" name="newReportCreated" value="yes">
                            <input type="submit" class="btn btn-primary btn-lg btn-block" value="Create Report"></a>
                        </c:otherwise>
                    </c:choose>                
                </form>
            
            <br>
                <%-- If the page is being used to edit the report, the option to delete the report is presented. --%>
                <c:if test="${editReport eq 'true'}">
                    <form method="post" action="/IoTBay/ReportFormServlet" onsubmit="return confirm('Are you sure you want to delete this report?');">
                        <input class="form-control" type="hidden" name="deleteReport" value="yes">
                        <input type="submit" class="btn btn-danger" value="Delete Report">
                    </form>
                </c:if>
            </body>
        </c:otherwise>
    </c:choose>
</html>
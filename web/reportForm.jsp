<%-- 
    Document    reporting
    Created on  8 Apr 2020, 21907 pm
    Author      denni
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="uts.isd.model.reporting.*" %>
<%@ page import="uts.isd.controller.reporting.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/workshop.css">
    </head>
    
    <%! String buttonText; %>
    <%! String action; %>
    <%
        if (session.getAttribute("editReport") != null && session.getAttribute("editReport").equals("yes") ) {
            Report report = (Report)session.getAttribute("report");
            buttonText = "Update Report";
            action = "updateReport";
        
    %>
        <jsp:include page="templates/header.jsp"/>
        <h1> Edit Report Details </h1>
    <%
        }

        else {
            action = "newReport";
            buttonText = "Create Report";
        }
    %>
    <body>
        <form method="post" action="/IoTBay/ReportFormServlet">
            <div class="form-row">                                                                         
                <div class="form-group col-md-6">                                           
                    <label>Report Name</label>
                    <input class="form-control" type="text" name="reportName" value="${report.name}" required">
                </div>

                <div class="form-group col-md-6">
                    <label>Report Description</label> 
                    <input class="form-control" type="text" name="reportDescription" value="${report.description}" required">
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-6">                                           
                    <label>Report Start Date</label>
                    <input class="form-control" type="date" name="startDate" value=${report.startDate} required">
                </div>

                <div class="form-group col-md-6">
                    <label>Report End Date</label> 
                    <input class="form-control" type="date" name="endDate" value=${report.endDate} required">
                </div>
            </div>
            <input class="form-control" type="hidden" name="<%=action%>" value="yes">
            <input type="submit" class="btn btn-primary btn-lg btn-block" value="<% out.println(buttonText); %>"></a>
        </form> 
    </body>
    
</html>
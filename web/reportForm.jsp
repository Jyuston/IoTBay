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
    <%! Boolean modifyingReport; %>

    <%
        if (request.getAttribute("editReport") != null && request.getAttribute("editReport").equals("yes")) {
            Report report = (Report)session.getAttribute("report");
            buttonText = "Update Report";
            action = "updateReport";
            modifyingReport = true;
            session.setAttribute("modifyingReport", true);
        
    %>
        <jsp:include page="templates/header.jsp"/>
        <h1> Edit Report Details </h1>
    <%
        }

        else {
            action = "newReportCreated";
            buttonText = "Create Report";
            modifyingReport = false;
        }
    %>
    <body>
            <form method="post" action="/IoTBay/ReportFormServlet">
                <div class="form-row">                                                                         
                    <div class="form-group col-md-6">                                           
                        <label>Report Name</label>
                        <input class="form-control" type="text" name="reportName" value="${modifyingReport eq 'true' ? report.name : ''}" required">
                    </div>

                    <div class="form-group col-md-6">
                        <label>Report Description</label> 
                        <input class="form-control" type="text" name="reportDescription" value="${modifyingReport eq 'true' ? report.description : ''}" required">
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-6">                                           
                        <label>Report Start Date</label>
                        <input class="form-control" type="date" name="startDate" value=${modifyingReport eq 'true' ? report.startDate : ''} required">
                    </div>

                    <div class="form-group col-md-6">
                        <label>Report End Date</label> 
                        <input class="form-control" type="date" name="endDate" value=${modifyingReport eq 'true' ? report.endDate : ''} required">
                    </div>
                </div>
                <input class="form-control" type="hidden" name="<%=action%>" value="yes">
                <%
                    if (modifyingReport) {                                    
                %>
                        <input type="submit" class="btn btn-success" value="Update Report"></a>
                        <a class="btn btn-warning" href="/IoTBay/reporting/reportView.jsp">Cancel</a>                                                      
                <%
                    } else {
                %>
                        <input type="submit" class="btn btn-primary btn-lg btn-block" value="<% out.println(buttonText); %>"></a>
                <%
                    }
                %>
        </form>
        
        <br>

        <% if (modifyingReport) { %>
            <form method="post" action="/IoTBay/ReportFormServlet">
                <input class="form-control" type="hidden" name="deleteReport" value="yes">
                <input type="submit" class="btn btn-danger" value="Delete Report">
            </form>
        <% } %>
        
    </body>
    
</html>
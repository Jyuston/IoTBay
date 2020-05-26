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
    <jsp:include page="templates/header.jsp"/>

    <%
        String newReportCreated = request.getParameter("newReportCreated");
        String reportName = request.getParameter("reportName");
        String reportDescription = request.getParameter("reportDescription");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        if (newReportCreated != null && newReportCreated.equals("yes")) {
            //ReportingController rc = new ReportingController();
            //rc.createReport(reportName, reportDescription, startDate, endDate);
        
    %>
    <body>
        <div class="row">
            <div class="col"></div>
            <div class="col-md-auto">
                <div class="alert alert-success" role="alert">
                    <h4 class="alert-heading"> Report Created!</h4>
                    <a class="btn btn-primary btn-lg btn-block" href="main.jsp"> View Report </a>
                </div>
            </div>
            <div class="col"></div>
        </div>
    </body>
    
    <% } else { %>
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
                                    <!-- <option hidden>...</option> -->
                                    <!-- will need to put in verification logic later if placeholder is desired -->
                                    <option>Stock Report</option>
                                    <% 
                                        ArrayList<String> reportNames = (ArrayList<String>)session.getAttribute("reportNames");
                                        for (String name : reportNames) {
                                            String currentReportName = name;
                                            session.setAttribute("currentReportName", currentReportName);
                                    %>        
                                        
                                        <option>${currentReportName}</option>
                                    <%
                                        }                 
                                    %>
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
                    <form method="post" action="reporting.jsp">
                        <div class="form-row">                                                                         
                            <div class="form-group col-md-6">                                           
                                <label>Report Name</label>
                                <input class="form-control" type="text" name="reportName" required">
                            </div>

                            <div class="form-group col-md-6">
                                <label>Report Description</label> 
                                <input class="form-control" type="text" name="reportDescription" required">
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-6">                                           
                                <label>Report Start Date</label>
                                <input class="form-control" type="date" name="startDate" required">
                            </div>

                            <div class="form-group col-md-6">
                                <label>Report End Date</label> 
                                <input class="form-control" type="date" name="endDate" required">
                            </div>
                        </div>
                        <input class="form-control" type="hidden" name="newReportCreated" value="yes">
                        <a href=""><input type="submit" class="btn btn-primary btn-lg btn-block" value="Create Report"></a>
                    </form>                    
                </div>
            </div>
        </div>
    </body>
    <% } %>

    <jsp:include page="templates/footer.jsp"/>
</html>

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
    <jsp:include page="../templates/header.jsp"/>

    <%
        String reportName = request.getParameter("selectedReport");

        ReportingController rc = new ReportingController();
        Report report = rc.getReport(reportName);
        session.setAttribute("report", report);
        String totalSalesRevenue = rc.totalRevenue(report.getStartDate(), report.getEndDate());
        session.setAttribute("totalSalesRevenue", totalSalesRevenue);

        String topCategory = rc.highestSellingCategory(report.getStartDate(), report.getEndDate());
        session.setAttribute("topCategory", topCategory);

        String topCategoryRevenue = rc.topCategoryRevenue(report.getStartDate(), report.getEndDate());
        session.setAttribute("topCategoryRevenue", topCategoryRevenue);
    %>
    
    <body>
        <h1> Report: ${report.name} </h1>
        <p>${report.description}</p>

        <div>
            <div class="row">
                <div class="col">
                    <p> Start Date </p>
                </div>

                <div class="col">
                    <p> End Date </p>
                </div>

                <div class="col">
                    
                </div>
            </div>
            
            <div class="row">
                <div class="col">
                    <input class="form-control" type="date" value=${report.startDate} disabled>
                </div>

                <div class="col">
                    <input class="form-control" type="date" value=${report.endDate} disabled>
                </div>

                <div class="col">
                    <a href="reporting/reportView.jsp"><input type="submit" class="btn btn-primary" value="Edit Report Details"></a>
                    <a href="reporting/reportView.jsp"><input type="submit" class="btn btn-primary" value="Delete Report"></a>            
                </div>
            </div>
        </div>
        
        <br>
        <br>

        <h2> Key Sales Statisitics & KPIs</h2>
        <div class="card-deck">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Total Revenue</h5>
                    <p class="card-text">$ ${totalSalesRevenue}</p>
                </div>
            </div>

            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Top Selling Item</h5>
                    <p class="card-text">Name: 0.25 Ohm Resistor</p>
                    <p class="card-text">Qty: 2561</p>
                    <p class="card-text">Revenue: $3461</p>
                </div>
            </div>

            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Total Selling Category</h5>
                    <p class="card-text">Name: ${topCategory}</p>
                    <p class="card-text">Revenue: $ ${topCategoryRevenue}</p>
                </div>
            </div>
        </div>
    </body>
        

    <jsp:include page="../templates/footer.jsp"/>
</html>

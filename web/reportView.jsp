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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <h1> Report: ${report.name} </h1>
        <p>${report.description} </p>

        <div>
            <%-- Using bootstrap divs to setup a grid for report information section. --%>
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
                    <%-- Shows start date (formatted) --%>
                    <input class="form-control" type="date" value=${report.startDate} disabled>
                </div>

                <div class="col">
                    <%-- Shows end date (formatted) --%>
                    <input class="form-control" type="date" value=${report.endDate} disabled>
                </div>

                <div class="col">
                    <div class="btn-group" role="group">
                        <c:choose>
                            <%-- If the user is an admin, they are able to access and modify report settings. --%>
                            <c:when test="${user.admin}">
                                <%-- If the user is an admin, they are able to access and modify report settings. --%>
                                <a class="btn btn-primary" href="/IoTBay/ReportFormServlet">Report Settings</a>
                            </c:when>

                            <%-- If the user is NOT an admin, show them the link, but have it disabled. --%>
                            <c:otherwise>
                                <a class="btn disabled btn-primary" href="/IoTBay/ReportFormServlet">Report Settings</a>
                            </c:otherwise>
                        </c:choose>

                        <form method="POST" action="/IoTBay/ReportingServlet">
                            <%-- Used to trigger logic for exiting the report view (i.e. clean session) --%>
                            <input class="form-control" type="hidden" name="reportExit" value="yes">
                            <input type="submit" class="btn btn-primary" value="Exit"></a>
                        </form>                    
                    </div>
                </div>
            </div>

            <c:if test="${not user.admin}">
                <p class="font-weight-light">Note: If you would like the report settings to be modified, or to delete the report, please contact your system administrator.</p>
            </c:if>
        </div>
        
        <br>
        <br>
        
        <%-- A check is placed to show report information and trigger calculations if there were sales in the reporting period. --%>
        <%-- This prevents NullPointerException if no sales occured. --%>
        <c:if test="${report.salesExist}">
            <h2> Key Sales Statisitics & KPIs Dashboard</h2>

            <%-- A bootstrap card-deck if used to present KPIs. --%>
            <div class="card-deck">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Total Revenue</h5>
                        <%-- Ensures that the revenue is formatted to 2dp. --%>
                        <p class="card-text">$ <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${report.totalRevenue}"/></p>
                    </div>
                </div>

                <%-- A test is conduced to check how many 'Top Products' occured. --%>
                <%-- If 1, then show info in a card. --%>
                <c:if test="${report.topProducts.size() == 1}">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Top Selling Item</h5>
                                <p class="card-text">Name: ${report.topProducts[0].name}</p>
                                <p class="card-text">Product ID: ${report.topProducts[0].ID}</p>
                                <p class="card-text">Quantity Sold: ${report.topProducts[0].unitsSold}</p>
                                <p class="card-text">Revenue: $ <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${report.topProducts[0].revenue}"/></p>
                        </div>
                    </div>
                </c:if>

                <%-- A test is conduced to check how many 'Top Categories' occured. --%>
                <%-- If 1, then show info in a card. --%>
                <c:if test="${report.topCategories.size() == 1}">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Top Revenue Generating Category</h5>
                            <p class="card-text">Name: ${report.topCategories[0]} </p>
                            <p class="card-text">Revenue: $ <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${report.topCategoryRevenues}"/></p>
                        </p>
                        </div>
                    </div>
                </c:if>
            </div>

            <br>
            <%-- A test is conduced to check how many 'Top Products' occured. --%>
            <%-- If more than 1, info is displayed in a table --%>
            <c:if test="${report.topProducts.size() > 1}">
                <h3> Top Products</h2>
                <p class="font-weight-light">Note: This table is only displayed when multiple products had the highest quantity sold.</p>
                <table class="table table-bordered table-sm table-dark">
                    
                    <thead class="thead-light">
                        <th scope="col">Product ID</th>
                        <th scope="col">Product Name</th>
                        <th scope="col">Quantity Sold</th>
                        <th scope="col">Revenue</th>
                    </thead>

                    <tbody>
                        <%-- Iterates through the array to display each product. --%>
                        <c:forEach items="${report.topProducts}" var="product" varStatus="loop">
                            <tr>
                                <td><c:out value="${product.ID}"/></td>
                                <td><c:out value="${product.name}"/></td>
                                <td><c:out value="${product.unitsSold}"/></td>
                                <td>$ <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${product.revenue}"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>

                </table>
            </c:if>

            <br>

            <%-- A test is conduced to check how many 'Top Categories' occured. --%>
            <%-- If 1, then show info in a table. --%>
            <c:if test="${report.topCategories.size() > 1}">
                <%-- Retrives the revenue for the categories, formats, and stores in variable. --%>
                <%-- Used to prevent un-needed calls to recalculate the revenue (as the revenue would be the same for all 'Top Categories'). --%>
                <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${report.topCategoryRevenues}" var="topCatRevenue"/>

                <h3>Top Revenue Generating Categories</h3>
                <p class="font-weight-light">Note: This table is only displayed when there are multiple top revenue generating categories.</p>
                <table id="reportingTable" class="table table-bordered table-sm table-dark">
                    
                    <thead class="thead-light">
                        <th scope="col">Category Name</th>
                        <th rowspan="2" scope="col">Revenue</th>
                    </thead>

                    <tbody>
                        <%-- Iterates through the array to display each category. --%>
                        <c:forEach items="${report.topCategories}" var="product" varStatus="loop">
                            <tr>
                                <td>${product}</td>
                                <td>$ ${topCatRevenue}</td>
                            </tr>
                        </c:forEach>
                    </tbody>

                </table>
            </c:if>

            <h2> Sales Distribution Overview</h2>
            <h3> Sales by State</h3>
            <table id="reportingTable" class="table table-bordered table-sm table-dark">
                
                <thead class="thead-light">
                    <th scope="col">State</th>
                    <th scope="col">Revenue</th>
                </thead>

                <tbody>
                    <%-- Iterates through the array to display each result. --%>
                    <c:forEach items="${report.salesByState}" var="item" varStatus="loop">
                        <tr>
                            <td>${item.key}</td>
                            <td>$ <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${item.value}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>

            <br>

            <h3> Sales by Category</h3>
            <p class="font-weight-light">Note: Categories where no products were sold will not appear in this report.</p>
            <table id="reportingTable" class="table table-bordered table-sm table-dark">

                <thead class="thead-light">
                    <th scope="col">Category</th>
                    <th scope="col">Revenue</th>
                </thead>

                <tbody>
                    <%-- Iterates through the array to display each category. --%>
                    <c:forEach items="${report.salesByCategory}" var="item" varStatus="loop">
                        <tr>
                            <td>${item.key}</td>
                            <td>$ <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${item.value}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>

            <br>
            <br>

            <h2> Sales by Category by Product</h2>
            <p class="font-weight-light">Note: Categories where no products were sold will not appear in this report.</p>

            <%-- Iterates through the hashmap to display each category. --%>
            <%-- JSTL abstracts this process, preventing the need to explicity code in use of keys etc. --%>
                <c:forEach items="${report.salesBreakdown}" var="entry" varStatus="loop">
                    <h4>${entry.key}</h4>
                    <div class="table-responsive-sm">
                        <table id="reportingTable" class="table table-bordered table-sm table-dark">

                            <thead class="thead-light">
                                <th scope="col">Product ID</th>
                                <th scope="col">Product Name</th>
                                <th scope="col">Units Sold</th>
                                <th scope="col">Total Revenue</th>
                            </thead>

                            <tbody>
                                <%-- Iterates through the ArrayList stored inside the value of the hashmap key to display each category. --%>
                                <c:forEach items="${entry.value}" var="item" varStatus="loop">
                                        <tr>
                                            <td>${item.ID}</td>
                                            <td>${item.name}</td>
                                            <td>${item.unitsSold}</td>
                                            <td>$ <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${item.revenue}"/></td>
                                        </tr>
                                </c:forEach>
                            </tbody>

                        </table>
                    </div>       
                </c:forEach>
        </c:if>

        <c:if test="${not report.salesExist}">
            <p>No sales occured during the selected period. Please select a different time period and try again.</p>
        </c:if>
    </body>
        
    <jsp:include page="templates/footer.jsp"/>
</html>

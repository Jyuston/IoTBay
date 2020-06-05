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

    <%
        Report report = (Report)session.getAttribute("report");
        DecimalFormat revenueFormatter = new DecimalFormat("####0.00");
    %>
    
    <body>
        <h1> Report: ${report.name} </h1>
        <p>${report.description} </p>

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
                    <div class="btn-group" role="group">
                        <a class="btn btn-primary" href="/IoTBay/ReportFormServlet">Report Settings</a>
                        <form method="POST" action="/IoTBay/ReportingServlet">
                            <input class="form-control" type="hidden" name="reportExit" value="yes">
                            <input type="submit" class="btn btn-primary" value="Exit"></a>
                        </form>                    
                    </div>
                </div>
            </div>
        </div>
        
        <br>
        <br>
        
        <c:if test="${report.salesExist}">
            <h2> Key Sales Statisitics & KPIs Dashboard</h2>
            <div class="card-deck">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Total Revenue</h5>
                        <p class="card-text">$ <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${report.totalRevenue}"/></p>
                    </div>
                </div>

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
            <c:if test="${report.topCategories.size() > 1}">
                <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${report.topCategoryRevenues}" var="topCatRevenue"/>
                <h3>Top Revenue Generating Categories</h3>
                <p class="font-weight-light">Note: This table is only displayed when there are multiple top revenue generating categories.</p>
                <table id="reportingTable" class="table table-bordered table-sm table-dark">
                    <thead class="thead-light">
                        <th scope="col">Category Name</th>
                        <th rowspan="2" scope="col">Revenue</th>
                    </thead>
                    <tbody>
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

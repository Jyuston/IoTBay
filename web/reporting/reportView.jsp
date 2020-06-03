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
                        <a class="btn btn-primary" href="/IoTBay/ReportFormServlet">Modify Report</a>
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
        <% if (report.salesExist()) { %>
        <h2> Key Sales Statisitics & KPIs</h2>
        <div class="card-deck">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Total Revenue</h5>
                    <p class="card-text">$ <% out.println(revenueFormatter.format(report.getTotalRevenue())); %></p>
                </div>
            </div>

            <%
                if (report.getTopSellingItem().size() == 1) {
            %>
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Top Selling Item</h5>
                                <p class="card-text">Name: ${report.topSellingItem[0].productName}</p>
                                <p class="card-text">Product ID: ${report.topSellingItem[0].productID}</p>
                                <p class="card-text">Quantity Sold: ${report.topSellingItem[0].unitsSold}</p>   
                        </div>
                    </div>
            <%
                }
            %>

            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Top Selling Category</h5>
                    <p class="card-text">Name: ${report.topCategory} </p>
                    <p class="card-text">Revenue: $ <% out.println(revenueFormatter.format(report.getTopCategoryRevenue())); %> </p>
                </div>
            </div>
        </div>

        <br>
        <%
            if (report.getTopSellingItem().size() > 1) {
        %>
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
                        <%
                                ArrayList<ProductSummary> topProducts = report.getTopSellingItem();
                                for (ProductSummary product : topProducts) {
                        %>
                                    <tr>
                                        <td><% out.println(product.getProductID()); %></td>
                                        <td><% out.println(product.getProductName()); %></td>
                                        <td><% out.println(product.getUnitsSold()); %></td>
                                        <td>$ <% out.println(revenueFormatter.format(product.getProductRevenue())); %> </td>
                                    </tr>
                        <%
                                }
                        %>
                    </tbody>
                </table>
        <%
            }
        %>
        <br>

        <h2> Sales Distribution Overview</h2>
        <h3> Sales by State</h3>
        <table class="table table-bordered table-sm table-dark">
            <thead class="thead-light">
                <th scope="col">State</th>
                <th scope="col">Revenue</th>
            </thead>
            <tbody>
                <%  
                    Hashtable<String, Double> salesByState = report.getSalesByState();
                    Set<String> dictionaryKeys = salesByState.keySet();

                    for (String key : dictionaryKeys) {
                        String state = key;
                        String revenue = revenueFormatter.format(salesByState.get(key));
                        session.setAttribute("state", state);
                        session.setAttribute("revenue", revenue);
                %>
                    <tr>
                        <td>${state}</td>
                        <td>$ ${revenue}</td>
                    </tr>
                <%     
                    }
                %>
            </tbody>
        </table>

        <br>

        <h3> Sales by Category</h3>
        <p class="font-weight-light">Note: Categories where no products were sold will not appear in this report.</p>
        <table class="table table-bordered table-sm table-dark">
            <thead class="thead-light">
                <th scope="col">Category</th>
                <th scope="col">Revenue</th>
            </thead>
            <tbody>
                <%
                    Hashtable<String, Double> salesByCategory = report.getSalesByCategory();
                    Set<String> dictionaryKeys2 = salesByCategory.keySet();

                    for (String key : dictionaryKeys2) {
                        String category = key;
                        String revenue = revenueFormatter.format(salesByCategory.get(key));
                        session.setAttribute("category", category);
                        session.setAttribute("revenue", revenue);
                %>
                    <tr>
                        <td>${category}</td>
                        <td>$ ${revenue}</td>
                    </tr>
                <%     
                    }
                    
                %>
            </tbody>
        </table>
        <br>
        <br>

        <h2> Sales by Category by Product</h2>
        <p class="font-weight-light">Note: Categories where no products were sold will not appear in this report.</p>
        <%
            HashMap<String, ArrayList<ProductSummary>> salesByCategorybyProduct = report.getSalesBreakdown();
            Set<String> dictionaryKeys3 = salesByCategorybyProduct.keySet();

            for (String key : dictionaryKeys3) {
                String category = key;
                session.setAttribute("category", category);
        %>
            <h4> ${category}</h4>
            <div class="table-responsive-sm">
            <table class="table table-bordered table-sm table-dark">
                <thead class="thead-light">
                    <th scope="col">Product ID</th>
                    <th scope="col">Product Name</th>
                    <th scope="col">Units Sold</th>
                    <th scope="col">Total Revenue</th>
                </thead>
                <tbody>
            <%
                ArrayList<ProductSummary> list = salesByCategorybyProduct.get(category);
                for (ProductSummary p : list) {
                    String productID = p.getProductID();
                    String productName = p.getProductName();
                    String units = Integer.toString(p.getUnitsSold());
                    String revenue = revenueFormatter.format(p.getProductRevenue());

                    session.setAttribute("productID", productID);
                    session.setAttribute("productName", productName);
                    session.setAttribute("units", units);
                    session.setAttribute("revenue", revenue);
                
            %>
                <tr>
                    <td>${productID}</td>
                    <td>${productName}</td>
                    <td>${units}</td>
                    <td>$ ${revenue}</td>
                </tr>
            <%
                }
            %>
            </tbody>
            </table>
            </div>
        <%
            }

            } else {
        %>
                <p>No sales occured during the selected period. Please select a different time period and try again.</p>
        <% } %>
    </body>
        
    <jsp:include page="../templates/footer.jsp"/>
</html>

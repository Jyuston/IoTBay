<%--
  Created by IntelliJ IDEA.
  User: Kevin
  Date: 7/06/2020
  Time: 9:22 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<head>
    <title>Invoices</title>
</head>

<jsp:include page="templates/header.jsp"/>
<c:set var="orders" value="${user.orders}"/>
<c:if test="${not empty invoiceResults}">
    <c:set var="orders" value="${invoiceResults}"/>
</c:if>


<h1 class="mb-2">Invoice Statements</h1>

<h2 class="mb-5"> Search an Invoice</h2>


<div class="container-fluid">
    <div class="row">
        <div class="form-group"></div>
        <table>
            <form action="InvoiceServlet" method="POST">
                <tr>
                    <td>
                        <input class="form-control-sm mb-2 mr-2 border border-secondary ${not empty idVErr ? 'border border-danger' : ''}" name="orderId"
                               placeholder="Order Id">
                        <small class="form-text text-danger mb-1">
                            <c:out value="${idVErr}"/>
                        </small>
                    </td>
                </tr>
                <tr>
                    <td>
                        <button type="submit" class="btn btn-info btn-sm my-2">Search by ID</button>
                    </td>
                </tr>
            </form>
            <form action="/IoTBay/InvoiceServlet" method="GET">
                <tr>
                    <td>
                        <input type="date" class="form-control-sm mb-2 mr-2 border border-secondary"
                               name="startDate"
                               placeholder="Enter Date" required>
                    </td>
                    <td>
                        <input type="date" class="form-control-sm mb-2 mr-2 border border-secondary" name="endDate"
                               placeholder="Enter Date" required>
                    </td>
                </tr>
                <tr>
                    <td>
                        <button type="submit" class="btn btn-info btn-sm my-2">Search by Date</button>
                    </td>
                </tr>
            </form>
        </table>


        <c:if test="${not empty invoiceResult}">
            <div class="col-md-auto mx-auto pb-4 rounded text-light">

                <table class="my-auto">
                    <tr colspan="3" class="py-1">
                        <th><h4>${user.firstName} ${user.lastName}</h4></th>
                    </tr>
                    <tr>
                        <td colspan="3" class="py-1">#${invoiceResult.ID}</td>
                    </tr>
                    <tr>
                        <td colspan="3" class="py-1">Date: <fmt:formatDate value="${invoiceResult.orderedOn}"
                                                                           pattern="MM/dd/yyyy ' at ' HH:mm a"/></td>
                    </tr>
                    <tr>
                        <td colspan="3" class="py-1">Email: ${user.email}</td>
                    </tr>
                    <tr>
                        <td colspan="3" class="py-1">Contact Number: ${user.contactNumber}</td>
                    </tr>
                    <tr>
                        <td colspan="3" class="py-1">Address: ${user.address.addressLine1}</td>
                    </tr>
                    <tr>
                        <td colspan="3" class="py-1">Payment Info: ${user.paymentInfo.cardNumber}</td>
                    </tr>
                </table>
            </div>
        </c:if>

    </div>
</div>

<h2> ${user.firstName} ${user.lastName} </h2>
<div style="max-height: 400px;" class="table-responsive overflow-auto table-light">
    <table class="table text-dark">

        <tr class="d-flex table-info">
            <th class="col">OrderID #</th>
            <th class="col">Purchase Date</th>
            <th class="col">Email</th>
            <th class="col">Contact Number</th>
            <th class="col">Address</th>
            <th class="col">Payment Info</th>
        </tr>
    </table>
</div>

<div style="max-height: 400px" class="table-responsive overflow-auto mb-4 table-striped table-light">
    <table class="table text-dark">
        <!--Would have to make a for each loop for list of orders returned via the orderDAO -->
        <!--for each loop -->
        <c:forEach items="${orders}" var="order" varStatus="count">
            <tr class="d-flex">
                <td class="col">${order.ID}</td>
                <td class="col"><fmt:formatDate value="${order.orderedOn}" pattern="dd/MM/yyyy ' at ' HH:mm a"/></td>
                <td class="col">${user.email}</td>
                <td class="col">${user.contactNumber}</td>
                <td class="col">${user.address.addressLine1}</td>
                <td class="col">${user.paymentInfo.cardNumber}</td>
            </tr>
        </c:forEach>

    </table>
</div>
</div>
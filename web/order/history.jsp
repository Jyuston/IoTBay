<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%--Request Scope EL Vars--%>
<%--@elvariable id="product" type="uts.isd.model.Product"--%>

<%--Session Scope EL Vars--%>
<%--@elvariable id="user" type="uts.isd.model.Customer"--%>

<head>
    <title>Product Details</title>
</head>
<jsp:include page="../templates/header.jsp"/>

<c:if test="${not empty user && user.customer}">
    <c:set var="orders" value="${user.orders}" />
</c:if>
<c:if test="${not empty filtered}">
    <c:set var="orders" value="${filteredOrders}"/>
</c:if>


<c:choose>
    <c:when test="${user.staff}">
        <h1 class="mb-4">Orders</h1>
    </c:when>
    <c:otherwise>
        <h1 class="mb-4">Order History</h1>
    </c:otherwise>
</c:choose>

<h5>Filter</h5>
<form class="form" id="ordersTable" action="FilterOrdersServlet" method="get">
    <div class="row">
        <div class="col-2">Start Date</div>
        <div class="col-2 ml-3">End Date</div>
        <c:if test="${not empty user && user.staff}">
            <div class="col"></div>
            <div class="col-2 text-right">Customer ID</div>
        </c:if>
    </div>
    <div class="row">
        <input class="form-control col-2 mx-3" type="date" name="startDate" value="${param.startDate}">
        <input class="form-control col-2" type="date" name="endDate" value="${param.endDate}">
        <c:choose>
            <c:when test="${not empty user && user.staff}">
                <div class="col"></div>
                <input class="form-control col-2 mx-3" type="number" min="0" name="customerID" value="${param.customerID}">
            </c:when>
            <c:otherwise>
                <input type="hidden" value="${user.ID}" name="customerID">
            </c:otherwise>
        </c:choose>
    </div>

    <button type="submit" class="btn btn-outline-primary btn-sm px-4 mt-3">Search</button>
    <a href="${user.staff ? 'FilterOrdersServlet' : 'history.jsp'}" class="btn btn-link text-danger btn-sm px-4 mt-3">Clear</a>
</form>

<h2>Purchases</h2>
<div class="fix-table">
    <table class="table table-striped history-table">
        <thead class="table-info">
        <tr>
            <th scope="col bg-info">Order ID</th>
            <c:if test="${user.staff}">
                <th scope="col">Customer ID</th>
            </c:if>
            <th scope="col">Ordered On</th>
            <th scope="col">Status</th>
            <th scope="col">Total</th>
            <th scope="col">Details</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${orders}" var="order" varStatus="count">
            <c:choose>
                <c:when test="${order.status == 'pending'}"><c:set var="statusColour" value="info"/></c:when>
                <c:when test="${order.status == 'cancelled'}"><c:set var="statusColour" value="danger"/></c:when>
                <c:when test="${order.status == 'approved'}"><c:set var="statusColour" value="success"/></c:when>
            </c:choose>

            <tr>
                <th scope="row">#${order.ID}</th>
                <c:if test="${user.staff}">
                    <td>#${order.customer.ID}</td>
                </c:if>
                <td><fmt:formatDate value="${order.orderedOn}" pattern="dd/MM/yyyy ' at ' HH:mm a"/></td>
                <td class="text-${statusColour}">${order.status}</td>
                <td class="font-weight-bold">$<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${order.total}"/></td>
                <td><a href="OrderDetailsServlet?ID=${order.ID}">View</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<style>
    .history-table th, td {
        text-align: center;
    }

    .fix-table {
        display: flex;
        flex-flow: column;
        width: 100%;
    }

    .fix-table thead {
        flex: 0 0 auto;
    }

    .fix-table tbody {
        max-height: 53vh;
        flex: 1 1 auto;
        display: block;
        overflow-y: auto;
        overflow-x: hidden;
    }

    .fix-table tr {
        width: 100%;
        display: table;
        table-layout: fixed;
    }
</style>

<script>
    var myForm = document.getElementById('ordersTable');

    myForm.addEventListener('submit', function () {
        var allInputs = myForm.getElementsByTagName('input');

        for (var i = 0; i < allInputs.length; i++) {
            var input = allInputs[i];

            if (input.name && !input.value) {
                input.name = '';
            }
        }
    });
</script>

<jsp:include page="../templates/footer.jsp"/>
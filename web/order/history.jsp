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

<c:set var="orders" value="${user.orders}"/>
<c:if test="${not empty filtered}">
    <c:set var="orders" value="${filteredOrders}"/>
</c:if>

<h1 class="mb-3">Order History</h1>

<div class="dropdown">
    <button class="btn btn-outline-dark btn-sm px-4 mb-3 dropdown-toggle" type="button" id="dropdownMenuButton"
            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        Filter By
    </button>
    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
        <a class="dropdown-item" onclick="swapFilter('date')" href="#">Date Range</a>
        <a class="dropdown-item" onclick="swapFilter('id')" href="#">Order ID</a>
    </div>
</div>


<form class="form mt-2 ${not empty param.orderID ? 'd-none' : ''}" id="dateForm" action="FilterOrdersServlet" method="get">
    <div class="row">
        <div class="col-2">Start Date</div>
        <div class="col-2 ml-3">End Date</div>
    </div>
    <div class="row">
        <input class="form-control col-2 mx-3" type="date" name="startDate" value="${param.startDate}" required>
        <input class="form-control col-2" type="date" name="endDate" value="${param.endDate}" required>
    </div>

    <button type="submit" class="btn btn-outline-primary btn-sm px-4 mt-3">Search</button>
    <a href="history.jsp" class="btn btn-link text-danger btn-sm px-4 mt-3">Clear</a>
</form>

<form class="form ${empty param.orderID ? 'd-none' : ''}" id="idForm" action="FilterOrdersServlet" method="get">
    <label for="orderID">Order ID</label>
    <input type="text" class="form-control w-25 ${not empty idVErr ? 'border border-danger' : ''}"
           id="orderID" name="orderID" value="${param.orderID}">
    <small class="form-text text-danger">
        <c:out value="${idVErr}"/>
    </small>

    <button type="submit" class="btn btn-outline-primary btn-sm px-4 mt-3">Search</button>
    <a href="history.jsp" class="btn btn-link text-danger btn-sm px-4 mt-3">Clear</a>
</form>

<h2>Purchases</h2>
<div class="fix-table">
    <table class="table table-striped history-table">
        <thead class="table-info">
        <tr>
            <th scope="col bg-info">Order ID</th>
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
                <td><fmt:formatDate value="${order.orderedOn}" pattern="dd/MM/yyyy ' at ' HH:mm a"/></td>
                <td class="text-${statusColour}">${order.status}</td>
                <td class="font-weight-bold">$<fmt:formatNumber type="number" maxFractionDigits="2"
                                                                minFractionDigits="2" value="${order.total}"/></td>
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
    // Purely Aesthetic for swapping out forms
    function swapFilter(type) {
        if (type == "date") {
            document.querySelector("#idForm").classList.add("d-none");
            document.querySelector("#dateForm").classList.remove("d-none");
        } else {
            document.querySelector("#idForm").classList.remove("d-none");
            document.querySelector("#dateForm").classList.add("d-none");
        }
    }
</script>

<jsp:include page="../templates/footer.jsp"/>
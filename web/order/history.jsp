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

<c:if test="${param.successAdd}">
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        <p class="mb-0"><strong>Yipee! </strong>Added to cart!</p>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
</c:if>

<c:if test="${param.failAdd}">
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <p class="mb-0"><strong>Can't add to cart! </strong>We don't have enough left!</p>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
</c:if>

<h1 class="mb-4">Order History</h1>

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
        <c:forEach items="${user.orders}" var="order" varStatus="count">
            <c:choose>
                <c:when test="${order.status == 'pending'}"><c:set var="statusColour" value="info"/></c:when>
                <c:when test="${order.status == 'cancelled'}"><c:set var="statusColour" value="danger"/></c:when>
                <c:when test="${order.status == 'approved'}"><c:set var="statusColour" value="success"/></c:when>
            </c:choose>

            <tr>
                <th scope="row">#${order.ID}</th>
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

<jsp:include page="../templates/footer.jsp"/>
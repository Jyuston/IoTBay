<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="uts.isd.model.Log"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--@elvariable id="user" type="uts.isd.model.Account"--%>
<head>
    <title>Logs</title>
</head>
<jsp:include page="templates/header.jsp"/>

<div class="max-w-md">
    <h1 class="font-weight-bold">User Actions</h1>
    <h5>Actions for user # <c:out value="${userLogs[0].ID}"/></h5>
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th scope="col">Date/Time</th>
            <th scope="col">Action Type</th>
        </tr>
        </thead>
            <c:forEach items="${userLogs}" var="Log" varStatus="count">
        <tr>
            <td>${Log.performedOn}</td>
            <td>${Log.action}</td>
        </tr>
            </c:forEach>
    </table>
</div>
       

<jsp:include page="templates/footer.jsp"/>
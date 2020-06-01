<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>Home</title>
</head>
<jsp:include page="templates/header.jsp"/>

<div class="max-w-md">
    <h1 class="font-weight-bold">Profile Info</h1>
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th scope="col">First Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">Email</th>
            <th scope="col">Address</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
            <c:if test="${user.isCustomer()}">
                <td>${user.address}</td>
            </c:if>
        </tr>
        </tbody>
    </table>

    <p class="text-center">
        <a class="text-danger" href="logout.jsp">Logout</a>
    </p>

    <p class="text-center">
        <a href="index.jsp">Home</a>
    </p>
    
    <c:if test="${user.isStaff() && user.admin}">
        <form action="/IoTBay/UserManagementServlet" method="GET">
            <button class="btn btn-info" type="submit">User Management</button>
        </form>
    </c:if>
    
 
</div>


<jsp:include page="templates/footer.jsp"/>
<%-- 
    Document   : user_management_edit.jsp
    Created on : 31/05/2020, 12:15:54 PM
    Author     : justinhyuen
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="templates/header.jsp"/>
<head>
    <title>Edit Account</title>
</head>
<h1>Edit Account</h1>
<h2> User Management </h2>

<c:choose>
    
    <c:when test="${successEdit}">
        <div class="alert alert-success my-4" role="alert">
            Account # ${account.ID} has successfully been updated.
            <a class="btn btn-primary btn-lg btn-block" href="UserManagementServlet">Back to User Management</a>
        </div>
    </c:when>
            
    <c:when test="${!successEdit}">
        <form class="m-5" method="POST" action="/IoTBay/UserManagementEditServlet">
            <%--Details--%>
            <h4>Details for Account #${account.ID}</h4>
            <div class="form-group">
                <input type="hidden" name="ID" value="${account.ID}">
                <div class="row">         
                    <div class="col">
                        <label for="firstName">First Name</label>
                        <input type="text" class="form-control" name="firstName" value="${account.firstName}"
                               required>
                    </div>
                    <div class="col">
                        <label for="lastName">Last Name</label>
                        <input type="text" class="form-control" name="lastName" value="${account.lastName}" 
                               required>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" name="email" value="${account.email}"
                       aria-describedby="emailHelp" required>
                <small id="emailHelp" class="form-text text-muted">Must be a valid email address.</small>
            </div>
            <div class="form-group">
                <label for="contactNumber">Phone Number</label>
                <input type="text" class="form-control" name="contactNumber" value="${account.contactNumber}"required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input class="form-control" name="password" value="${account.password}" required>
            </div>

            <button type="submit" class="btn btn-success mt-4">Edit Account</button> 
        </form>
    </c:when>

</c:choose>
<jsp:include page="templates/footer.jsp"/>
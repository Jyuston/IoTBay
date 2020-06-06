<%-- 
    Document   : user_create
    Created on : 26/05/2020, 2:31:04 PM
    Author     : justinhyuen
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="templates/header.jsp"/>

<head>
    <title>Add Account</title>
</head>
<h1>Add Account</h1>

<h2> Add a new user account </h2>

<form class="m-5" method="POST" action="/IoTBay/UserManagementCreateServlet">
    <%--Details--%>
    <h4>Details</h4>
    <div class="form-group">
        <div class="row">
            <div class="col">
                <label for="firstName">First Name</label>
                <input type="text" class="form-control ${not empty nameVErr ? 'border border-danger' : ''}" name="firstName" id="firstName" placeholder="Mike"
                       required>
                <small class="form-text text-danger">
                    <c:out value="${nameVErr}"/>
                </small>                
            </div>
            <div class="col">
                <label for="lastName">Last Name</label>
                <input type="text" class="form-control ${not empty nameVErr ? 'border border-danger' : ''}" name="lastName" id="lastName" placeholder="Wazowski"
                       required>
                <small class="form-text text-danger">
                    <c:out value="${nameVErr}"/>
                </small> 
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="email">Email</label>
        <input type="email" class="form-control ${not empty emailVErr ? 'border border-danger' : ''}" name="email" id="email" placeholder="email@example.com"
               aria-describedby="emailHelp" required>
        <small class="form-text text-danger">
            <c:out value="${emailVErr}"/>
        </small>
    </div>
    <div class="form-group">
        <label for="contactNumber">Contact Number</label>
        <input type="text" class="form-control ${not empty contactNumberVErr ? 'border border-danger' : ''}"
               name="contactNumber" id="contactNumber" required>
        <small class="form-text text-danger">
            <c:out value="${contactNumberVErr}"/>
        </small>
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control ${not empty passwordVErr ? 'border border-danger' : ''}"
               name="password" required>
        <small class="form-text text-danger">
            <c:out value="${passwordVErr}"/>
        </small>
    </div>

    <div class="form-group">
        <label for="accountType">Account Type</label>
        <select class="custom-select" id="accountType" name="accountType" required>
            <option value="Customer">Customer</option>
            <option value="Staff">Staff</option>
            <option value="Staff-Admin">Staff-Admin</option>
        </select>
    </div>
    <button type="submit" class="btn btn-success mt-4">Add Account</button> 
</form>

<jsp:include page="templates/footer.jsp"/>


<%-- 
    Document   : user_create
    Created on : 26/05/2020, 2:31:04 PM
    Author     : justinhyuen
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <title>Add Account</title>
</head>
<jsp:include page="templates/header.jsp"/>

<h1>Add Account</h1>

<h2> Add a new user account </h2>

<form class="m-5" method="POST" action="/IoTBay/UserManagementCreateServlet">
    <%--Details--%>
    <h4>Details</h4>
    
    <c:if test="${success}">
                <div class="alert alert-success my-4" role="alert">
                     New account created.
                     <br>
                     <a class="text-info" href="UserManagementServlet">Back to User Management</a>
                </div>
    </c:if>
                
    <c:if test="${not empty errorUserManagementCreate}">
        <div class="alert alert-danger my-4" role="alert">
            ${errorUserManagementCreate}
        </div>
    </c:if>
    
    <div class="form-group">
        <div class="row">
            <div class="col">
                <label for="firstName">First Name</label>
                <input type="text" class="form-control ${not empty nameVErr ? 'border border-danger' : ''}" 
                       name="firstName" id="firstName" placeholder="Mike">
                <small class="form-text text-danger">
                    <c:out value="${nameVErr}"/>
                </small>                
            </div>
            <div class="col">
                <label for="lastName">Last Name</label>
                <input type="text" class="form-control ${not empty nameVErr ? 'border border-danger' : ''}"
                       name="lastName" id="lastName" placeholder="Wazowski">
                <small class="form-text text-danger">
                    <c:out value="${nameVErr}"/>
                </small> 
            </div>
        </div>
    </div>
                
    <div class="form-group">
        <label for="email">Email</label>
        <input type="text" class="form-control ${not empty emailVErr ? 'border border-danger' : ''}"
               name="email" id="email" placeholder="email@example.com">
        <small class="form-text text-danger">
            <c:out value="${emailVErr}"/>
        </small>
    </div>
        
    <div class="form-group">
        <label for="contactNumber">Contact Number</label>
        <input type="text" class="form-control ${not empty contactNumberVErr ? 'border border-danger' : ''}"
               name="contactNumber" id="contactNumber">
        <small class="form-text text-danger">
            <c:out value="${contactNumberVErr}"/>
        </small>
    </div>
        
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control ${not empty passVErr ? 'border border-danger' : ''}"
               name="password">
        <small class="form-text text-danger">
            <c:out value="${passVErr}"/>
        </small>
    </div>

    <div class="form-group">
        <label for="accountType">Account Type</label>
        <select class="custom-select mb-5" id="accountType" name="accountType">
            <option value="Customer">Customer</option>
            <option value="Staff">Staff</option>
            <option value="Staff-Admin">Staff-Admin</option>
        </select>
    </div>
    
    <a href="/IoTBay/UserManagementServlet" class="text-danger ">Cancel</a>
    <button type="submit" class="btn btn-success float-right">Add Account</button> 
</form>

<jsp:include page="templates/footer.jsp"/>


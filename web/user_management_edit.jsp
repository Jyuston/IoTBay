<%-- 
    Document   : user_management_edit.jsp
    Created on : 31/05/2020, 12:15:54 PM
    Author     : justinhyuen
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="templates/header.jsp"/>
<head>
    <title>Edit Account</title>
</head>
<h1>Edit Account</h1>

<h2> Edit Account </h2>

<form class="m-5" method="POST" action="/IoTBay/UserManagementCreateServlet">
    <%--Details--%>
    <h4>Details</h4>
    <div class="form-group">
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
        <input type="number" class="form-control" name="contactNumber" value="${account.contactNumber}"required>
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input class="form-control" name="password" value="${account.password}" required>
    </div>

    <button type="submit" class="btn btn-success mt-4">Add Account</button> 
</form>

<jsp:include page="templates/footer.jsp"/>
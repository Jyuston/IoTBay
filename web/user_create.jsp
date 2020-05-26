<%-- 
    Document   : user_create
    Created on : 26/05/2020, 2:31:04 PM
    Author     : justinhyuen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="templates/header.jsp"/>
<head>
    <title>Add Account</title>
</head>
<h1>Add Account</h1>

<h2> Add a new user account </h2>

        <form class="m-5">
            <%--Details--%>
            <h4>Details</h4>
            <div class="form-group">
                <div class="row">
                    <div class="col">
                        <label for="firstName">First Name</label>
                        <input type="text" class="form-control" name="firstName" id="firstName" placeholder="Mike"
                               required>
                    </div>
                    <div class="col">
                        <label for="lastName">Last Name</label>
                        <input type="text" class="form-control" name="lastName" id="lastName" placeholder="Wazowski"
                               required>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" name="email" id="email" placeholder="email@example.com"
                       aria-describedby="emailHelp" required>
                <small id="emailHelp" class="form-text text-muted">Must be a valid email address.</small>
            </div>
            <div class="form-group">
                <label for="contactNumber">Phone Number</label>
                <input type="number" class="form-control" name="contactNumber" id="contactNumber" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            
            <div class="form-group">
                <label for="accountType">Account Type</label>
                <select class="custom-select" id="accountType" name="accountType" required>
                    <option value="Customer">Customer</option>
                    <option value="Staff">Staff</option>
                    <option value="Staff-Admin">Staff-Admin</option>
                </select>
            </div>
            <button class="btn btn-success mt-4">Add Account</button> 
        </form>

<jsp:include page="templates/footer.jsp"/>
            
            
<%@ page import="uts.isd.controller.LoginController" %>
<%@ page import="uts.isd.model.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>Login</title>
</head>
<jsp:include page="templates/header.jsp"/>

<%
    String submitted = request.getParameter("submitted");

    if (submitted != null) {
        String enteredEmail = request.getParameter("email");
        String enteredPassword = request.getParameter("password");
        
        Customer customer = LoginController.login(enteredEmail, enteredPassword);
        
        if (customer != null) {
            session.setAttribute("user", customer);
%>

<div class="row">
    <div class="col"></div>
    <div class="col-md-auto">
        <div class="alert alert-success" role="alert">
            <h4 class="alert-heading"> Login Successful!</h4>
            <a class="btn btn-primary btn-lg btn-block" href="main.jsp"> Enter Site </a>
        </div>
    </div>
    <div class="col"></div>
</div>

<% } else { %>

<div class="row">
    <div class="col"></div>
    <div class="col-md-auto">
        <div class="alert alert-danger" role="alert">
            <h4 class="alert-heading">Login Unsuccessful!</h4>
            <p id="successAlertText">Incorrect email or password</p>
            <a class="btn btn-primary btn-lg btn-block" href="login.jsp"> Try Again </a>
        </div>
    </div>
    <div class="col"></div>
</div>

<% }
} else { %>

<form action="login.jsp" method="post" class="max-w-sm">
    <h1>Login</h1>
    <div class="form-group">
        <label for="email">Email address</label>
        <input type="email" class="form-control" name="email" id="email" placeholder="email@example.com"
               aria-describedby="emailHelp" required>
        <small id="emailHelp" class="form-text text-muted">Must be a valid email address.</small>
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
    </div>

    <%--Used to tell if the form has been submitted within Scriplet tag above--%>
    <input type="hidden" name="submitted" value="true">

    <button type="submit" class="btn btn-primary btn-block mt-3 mb-2">Login</button>

    <p class="text-center">
        Haven't made an account? <a href="register.jsp">Register here</a>
    </p>

    <a href="index.jsp" class="text-center d-block text-danger">Cancel</a>
</form>

<% } %>


<jsp:include page="templates/footer.jsp"/>
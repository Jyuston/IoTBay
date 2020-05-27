<%@ page import="uts.isd.model.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>Login</title>
</head>
<jsp:include page="templates/header.jsp"/>

<%
    Account user = (Account) session.getAttribute("user");
    String errorMsg = (String) request.getAttribute("errorLogin");
    // If user logged in
    if (user != null) {
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
<form action="LoginServlet" method="post" class="max-w-sm">
    <h1>Login</h1>

    <%-- If error message --%>
    <% if (errorMsg != null) { %>
    <div class="alert alert-danger my-4" role="alert">
        <%= errorMsg %>
    </div>
    <% } %>

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
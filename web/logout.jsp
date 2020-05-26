<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% session.invalidate(); %>

<head>
    <title>Log out</title>
</head>
<jsp:include page="templates/header.jsp"/>

<div class="alert alert-success max-w-sm" role="alert">
    <h4 class="alert-heading">Logged out!</h4>
    <p>
        You've been successfully logged out. Please return to the home page by clicking
        <a href="index.jsp" class="alert-link">
            here.
        </a>
    </p>
    <hr>
    <p class="mb-0">
        If you didn't mean to log out, you can log back in
        <a href="login.jsp" class="alert-link">
            here.
        </a>
    </p>
</div>

<jsp:include page="templates/footer.jsp"/>


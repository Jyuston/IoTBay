<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>Home</title>
</head>
<jsp:include page="templates/header.jsp"/>

<%--Save the database connection to the session--%>
<jsp:include page="/ConnServlet" flush="true"/>

<div class="text-center text-white max-w-sm">
    <em>Welcome to...</em>
    <h1 class="display-1 font-weight-bold">IoT Bay!</h1>
    <hr class="my-3 w-25 mx-auto">
    <h5 class="font-weight-normal">An online store for every<em>thing</em> "Internet of <em>Things</em>".</h5>

    <p class="mt-4 mb-1">
        To continue, please Login or Register below.
        <br />
        <a href="login.jsp">Login</a> |
        <a href="register.jsp">Register</a>
    </p>
</div>

<jsp:include page="templates/footer.jsp"/>
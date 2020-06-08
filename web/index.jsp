
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <title>Home</title>
</head>
<jsp:include page="templates/header.jsp"/>


<c:choose>
    <%-- Check if an error occured. If so, display the error message. --%>
    <c:when test="${not empty error}">
        <div class="alert alert-danger my-4" role="alert">
            <h1>Oops. Something went wrong.</h1>
            <p class="lead">An error occured whilst processing your request. Please see the attached error message.</p>
            <p>${error}</p>
        </div>
        <a href="index.jsp" class="btn btn-primary btn-lg btn-block">Exit</a>
    </c:when>

    <%-- No error detected - display the page as normal. --%>
    <c:otherwise>
        <div class="text-center max-w-sm">
            <em>Welcome to...</em>
            <h1 class="display-1 font-weight-bold">IoT Bay!</h1>
            <hr class="my-3 w-25 mx-auto">
            <h5 class="font-weight-normal">An online store for every<em>thing</em> "Internet of <em>Things</em>".</h5>
        
            <p class="mt-4 mb-1">
                To continue, please Login or Register below.
                <br/>
                <a href="login.jsp">Login</a> |
                <a href="products/CatalogueServlet">Catalogue</a> |
                <a href="register.jsp">Register</a>
            </p>
        </div>
    </c:otherwise>
</c:choose>

<%--We dont jsp:include the ConnServlet here because we access the single db connection directly in the DAOs and DAOUtils--%>

<jsp:include page="templates/footer.jsp"/>
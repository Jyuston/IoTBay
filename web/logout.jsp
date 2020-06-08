<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    if (request.getParameter("warnAnonymous") == null) {
        session.invalidate();
    }
%>

<head>
    <title>Log out</title>
</head>
<jsp:include page="templates/header.jsp"/>

<c:choose>
    <c:when test="${not empty param.warnAnonymous}">
        <div class="alert alert-warning w mx-auto" role="alert">
            <h4 class="alert-heading">Careful!</h4>
            <p>
                You are about to log out without finishing your account registration!
                You can complete your account setup <a class="alert-link" href="EditProfileServlet">here</a>.
            </p>
            <hr>
            <p class="mb-0">
                You can still <a class="alert-link" href="logout.jsp">logout</a>, but you will lose any orders made.
            </p>
        </div>
    </c:when>
    <c:otherwise>
        <div class="alert alert-success w mx-auto" role="alert">
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
    </c:otherwise>
</c:choose>

<jsp:include page="templates/footer.jsp"/>


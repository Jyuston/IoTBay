<%@ page import="uts.isd.model.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="uts.isd.model.Account" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    Account user = (Account) session.getAttribute("user");
%>

<header class="navbar navbar-expand-md navbar-dark bg-primary mb-5">
    <div class="container">
        <a class="navbar-brand h1 mb-0 font-weight-bold" href="${pageContext.request.contextPath}/index.jsp">IoT Bay</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <div class="navbar-nav w-100">
                <a class="nav-item nav-link" href="${pageContext.request.contextPath}/main.jsp">Main</a>
                <% if (user != null) { %>
                <a class="nav-item nav-link" href="${pageContext.request.contextPath}/EditProfileServlet">Edit Profile</a>
                <a class="nav-item nav-link" href="${pageContext.request.contextPath}/LogsServlet">Logs</a>
                <c:if test="${user.customer}">
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/invoice.jsp">Invoice</a>
                </c:if>
                <c:if test="${user.staff}">
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/ReportingServlet">Reporting</a>
                </c:if>
                <c:if test="${user.staff && user.admin}">
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/UserManagementServlet">User Management</a>
                </c:if>
                <% } else { %>
                <a class="nav-item nav-link" href="${pageContext.request.contextPath}/register.jsp">Register</a>
                <% } %>

                <a class="nav-item nav-link ml-auto mr-3" href="${pageContext.request.contextPath}/cart.jsp">Cart</a>

                <% if (user != null) { %>
                <form class="form-inline mb-0" action="${pageContext.request.contextPath}/LogoutServlet" method="post">
                    <button type="submit" class="btn btn-outline-light my-2 my-sm-0">Logout</button>
                </form>
                <% } else { %>
                <a class="btn btn-outline-light my-2 my-sm-0 px-4" href="${pageContext.request.contextPath}/login.jsp"
                   role="button">Login</a>
                <% } %>
            </div>
        </div>
    </div>
</header>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                <a class="nav-item nav-link" href="${pageContext.request.contextPath}/products/CatalogueServlet">Catalogue</a>
                <c:choose>
                    <c:when test="${not empty user}">
                        <c:if test="${user.staff}">
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/ReportingServlet">Reporting</a>
                        </c:if>
                        <c:if test="${user.staff && user.admin}">
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/UserManagementServlet">User Management</a>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/register.jsp">Register</a>
                    </c:otherwise>
                </c:choose>

                <a class="nav-item nav-link ml-auto mr-3" href="${pageContext.request.contextPath}/cart.jsp">
                    Cart
                    <svg class="bi bi-cart2 pb-1" font-size="1.25em" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5zM3.14 5l1.25 5h8.22l1.25-5H3.14zM5 13a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0z"></path>
                    </svg>
                </a>

                <c:choose>
                    <c:when test="${not empty user}">
                        <div class="btn-group">
                            <button type="button" class="btn btn-outline-light my-2 my-sm-0 dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Account
                            </button>
                            <div class="dropdown-menu dropdown-menu-right" style="width: 250px">
                                <p class="mx-4 my-2">Logged in as <strong>${(user.customer && user.anonymous) ? 'Anonymous' : user.firstName}</strong></p>
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/EditProfileServlet">Edit Profile</a>
                                <div class="dropdown-divider"></div>
                                <c:if test="${user.customer}">
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/invoice.jsp">Invoices</a>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/order/history.jsp">Order History</a>
                                    <div class="dropdown-divider"></div>
                                </c:if>
                                <form class="form-inline m-0 p-0" action="${pageContext.request.contextPath}/LogoutServlet" method="post">
                                    <button type="submit" class="dropdown-item">Logout</button>
                                </form>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <a class="btn btn-outline-light my-2 my-sm-0 px-4" href="${pageContext.request.contextPath}/login.jsp"
                           role="button">Login</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</header>

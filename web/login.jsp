<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="user" type="uts.isd.model.Account"--%>
<%--@elvariable id="loginErr" type="java.lang.String"--%>
<%--@elvariable id="emptyEmailPassVErr" type="java.lang.String"--%>
<%--@elvariable id="emailVErr" type="java.lang.String"--%>
<%--@elvariable id="passVErr" type="java.lang.String"--%>

<head>
    <title>Login</title>
</head>
<jsp:include page="templates/header.jsp"/>

<div class="w mx-auto">
        <c:choose>
            <c:when test="${not empty user}">
                <div class="alert alert-success py-3" role="alert">
                    <h5 class="alert-heading font-weight-bold text-center mb-3">Login Successful!</h5>
                    <a class="btn btn-outline-success btn-block" href="main.jsp">Enter Site</a>
                </div>
            </c:when>

            <c:otherwise>
                <form action="LoginServlet" method="post" class="validate">
                    <h1>Login</h1>

                        <%--General Errors--%>
                    <c:if test="${not empty emptyEmailPassVErr}">
                        <div class="alert alert-danger my-4" role="alert">
                            <c:out value="${emptyEmailPassVErr}"/>
                        </div>
                    </c:if>

                    <c:if test="${not empty loginErr}">
                        <div class="alert alert-danger my-4" role="alert">
                            <c:out value="${loginErr}"/>
                        </div>
                    </c:if>

                        <%--Login Form--%>
                    <div class="form-group">
                        <label for="email">Email address</label>
                        <input type="text" class="form-control ${not empty emailVErr ? 'border border-danger' : ''}"
                               name="email" id="email" placeholder="email@example.com">
                        <small class="form-text text-danger">
                            <c:out value="${emailVErr}"/>
                        </small>
                    </div>

                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password"
                               class="form-control ${not empty passVErr ? 'border border-danger' : ''}"
                               id="password" name="password" placeholder="Password">
                        <small class="form-text text-danger">
                            <c:out value="${passVErr}"/>
                        </small>
                    </div>

                    <button type="submit" class="btn btn-primary btn-block mt-4 mb-2">Login</button>

                    <p class="text-center">
                        Haven't made an account? <a href="register.jsp">Register here</a>
                    </p>

                    <a href="index.jsp" class="text-center d-block text-danger">Cancel</a>
                </form>
            </c:otherwise>
        </c:choose>
</div>

<jsp:include page="templates/footer.jsp"/>

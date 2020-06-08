<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="success" type="boolean"--%>
<%--@elvariable id="registerErr" type="java.lang.String"--%>
<%--@elvariable id="emptyEmailPassVErr" type="java.lang.String"--%>
<%--@elvariable id="nameVErr" type="java.lang.String"--%>
<%--@elvariable id="emailVErr" type="java.lang.String"--%>
<%--@elvariable id="passVErr" type="java.lang.String"--%>

<head>
    <title>Register</title>
</head>
<jsp:include page="templates/header.jsp"/>

<div class="row">
    <div class="col"></div>
    <div class="col-12 col-md-8 col-lg-6 col-xl-5">
        <c:choose>
            <c:when test="${success}">
                <div class="alert alert-success" role="alert">
                    <h4 class="alert-heading">Registration Successful!</h4>
                    <a class="btn btn-primary btn-lg btn-block" href="main.jsp">Enter Site</a>
                </div>
            </c:when>

            <c:otherwise>
                <form action="RegistrationServlet" method="post">
                    <h1>Register</h1>

                        <%--General Errors--%>
                    <c:if test="${not empty emptyEmailPassVErr}">
                        <div class="alert alert-danger my-4" role="alert">
                            <c:out value="${emptyEmailPassVErr}"/>
                        </div>
                    </c:if>

                    <c:if test="${not empty registerErr}">
                        <div class="alert alert-danger my-4" role="alert">
                            <c:out value="${registerErr}"/>
                        </div>
                    </c:if>

                        <%--Details--%>
                    <h4>Details</h4>
                    <div class="form-group">
                        <div class="row">
                            <div class="col">
                                <label for="firstName">First Name</label>
                                <input type="text" class="form-control ${not empty nameVErr ? 'border border-danger' : ''}"
                                       name="firstName" id="firstName" placeholder="Mike">
                                <small class="form-text text-danger">
                                    <c:out value="${nameVErr}"/>
                                </small>
                            </div>

                            <div class="col">
                                <label for="lastName">Last Name</label>
                                <input type="text" class="form-control ${not empty nameVErr ? 'border border-danger' : ''}"
                                       name="lastName" id="lastName" placeholder="Wazowski">
                                <small class="form-text text-danger">
                                    <c:out value="${nameVErr}"/>
                                </small>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input class="form-control ${not empty emailVErr ? 'border border-danger' : ''}"
                               name="email" id="email" placeholder="email@example.com">
                        <small class="form-text text-danger">
                            <c:out value="${emailVErr}"/>
                        </small>
                    </div>

                    <div class="form-group">
                        <label for="contactNumber">Phone Number</label>
                        <input type="tel" class="form-control ${not empty contactNumberVErr ? 'border border-danger' : ''}"
                               name="contactNumber" id="contactNumber"
                               placeholder="0123 456 789">
                        <small class="form-text text-danger">
                            <c:out value="${contactNumberVErr}"/>
                        </small>
                    </div>

                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control ${not empty passVErr ? 'border border-danger' : ''}"
                               id="password" name="password">
                        <small class="form-text text-danger">
                            <c:out value="${passVErr}"/>
                        </small>
                        <small class="form-text text-primary">
                            password must be greater than 4 characters
                        </small>
                    </div>

                        <%--TOS--%>
                    <div class="form-group form-check mt-5 mb-4">
                        <input type="checkbox" class="form-check-input" id="tos" name="tos" required>
                        <label class="form-check-label" for="tos">I Agree to the Terms of Service</label>
                    </div>

                        <%--Used to tell if the form has been submitted--%>
                    <input type="hidden" name="submitted" value="true">

                        <%--form actions--%>
                    <button type="submit" class="btn btn-primary btn-block my-3">Submit</button>
                    <a href="index.jsp" class="text-center d-block text-danger">Cancel</a>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="col"></div>
</div>

<jsp:include page="templates/footer.jsp"/>

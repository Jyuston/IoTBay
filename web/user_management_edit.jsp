<%-- 
    Document   : user_management_edit.jsp
    Created on : 31/05/2020, 12:15:54 PM
    Author     : justinhyuen
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="templates/header.jsp"/>
<head>
    <title>Edit Account</title>
</head>
<h1>Edit Account</h1>
<h2> User Management </h2>

<div class="row">

    <div class="col-lg-6">
        <c:choose>

            <c:when test="${successEdit}">
                <div class="alert alert-success my-4" role="alert">
                        <h4>Account # ${account.ID} has successfully been updated.</h4>
                    <a class="btn btn-primary btn-lg btn-block" href="UserManagementServlet">Back to User Management</a>
                </div>
            </c:when>

            <c:when test="${!successEdit}">
                <form class="m-5" method="POST" action="/IoTBay/UserManagementEditServlet">
                    <%--Details--%>
                    <h4>Details for Account #${account.ID}</h4>
                    <div class="form-group">
                        <input type="hidden" name="ID" value="${account.ID}">
                        <div class="row">         
                            <div class="col">
                                <label for="firstName">First Name</label>
                                <input type="text" class="form-control ${not empty nameVErr ? 'border border-danger' : ''}" 
                                       name="firstName" value="${account.firstName}">
                                <small class="form-text text-danger">
                                    <c:out value="${nameVErr}"/>
                                 </small> 
                            </div>
                            <div class="col">
                                <label for="lastName">Last Name</label>
                                <input type="text" class="form-control ${not empty nameVErr ? 'border border-danger' : ''}" 
                                       name="lastName" value="${account.lastName}">
                                <small class="form-text text-danger">
                                    <c:out value="${nameVErr}"/>
                                </small>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="text" class="form-control ${not empty emailVErr ? 'border border-danger' : ''}" 
                               name="email" value="${account.email}" aria-describedby="emailHelp">    
                        <small class="form-text text-danger">
                            <c:out value="${emailVErr}"/>
                        </small>
                    </div>
                    <div class="form-group">
                        <label for="contactNumber">Contact Number</label>
                        <input type="text" class="form-control ${not empty contactNumberVErr ? 'border border-danger' : ''}" 
                               name="contactNumber" value="${account.contactNumber}">
                        <small class="form-text text-danger">
                            <c:out value="${contactNumberVErr}"/>
                        </small>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input class="form-control ${not empty passVErr ? 'border border-danger' : ''}"
                               name="password" value="${account.password}">
                        <small class="form-text text-danger">
                            <c:out value="${passVErr}"/>
                        </small>
                    </div>
                        
                    <c:if test="${account.customer}">
                        <div class="form-group">
                        <label for="Address">Address</label>
                        <input class="form-control"
                               name="TestAdressform" value="">
                        </div>
                    </c:if>
                        
                    <a href="/IoTBay/UserManagementServlet" class="text-danger mt-5">Cancel</a>
                    <button type="submit" class="btn btn-success mt-4 float-right">Edit Account</button>
                    
                    
                    

                </form>
            </c:when>
        </c:choose>

    </div>

</div>
<jsp:include page="templates/footer.jsp"/>
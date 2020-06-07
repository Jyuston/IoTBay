<%-- 
    Document   : user_management_edit.jsp
    Created on : 31/05/2020, 12:15:54 PM
    Author     : justinhyuen
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="templates/header.jsp"/>
<head>
    <title>Edit Account</title>
</head>
<h1>Edit Account</h1>
<h2> User Management </h2>

<div class="row">

    <div class="col-lg-6">

        <c:if test="${successEdit}">
            <div class="alert alert-success my-4" role="alert">
                Account # ${account.ID} has successfully been updated.<br>

                <c:if test="${user.staff}">
                    <a class="text-info" href="UserManagementServlet">Back to User Management</a>
                </c:if>

                <c:if test="${user.customer}">
                    <a class="text-info" href="main.jsp">Back to Main</a>
                </c:if>
 
            </div>
        </c:if>


        <c:choose>
            <c:when test="${user.staff}">
                <form class="m-5" method="POST" action="/IoTBay/UserManagementEditServlet">
            </c:when>
            <c:otherwise>
                <form class="m-5" method="POST" action="/IoTBay/EditProfileServlet">    
            </c:otherwise>
        </c:choose>

            <h4>Account Details</h4> 
            <p>ID: #${account.ID}</p>
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
                <input type="text" class="form-control ${not empty passVErr ? 'border border-danger' : ''}"
                       name="password" value="${account.password}">
                <small class="form-text text-danger">
                    <c:out value="${passVErr}"/>
                </small>
            </div>

            <c:if test="${account.customer}">
                
                <h4 class="mt-5">Shipping Address</h4>
                <div class="form-group">
                    <label for="addressLine1">Address</label>
                    <input type="text" class="form-control" name="addressLine1" id="addressLine1"
                           value="${account.address.addressLine1}"
                           placeholder="Sherman 42 Wallaby Way, Sydney"
                           >
                </div>
                <div class="form-group">
                    <label for="addressLine2">Address 2 (Optional)</label>
                    <input type="text" class="form-control" name="addressLine2" id="addressLine2"
                           value="${account.address.addressLine2}"
                           >
                </div>
                <div class="form-group">
                    <label for="suburb">Suburb</label>
                    <input type="text" class="form-control" name="suburb" id="suburb"
                           value="${account.address.suburb}"
                           >
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col">
                            <label for="state">State</label>
                            <select class="form-control" name="state" id="state" selected="${account.address.state}">
                                <option>NSW</option>
                                <option>QLD</option>
                                <option>ACT</option>
                                <option>VIC</option>
                                <option>TAS</option>
                                <option>SA</option>
                                <option>WA</option>
                                <option>NT</option>
                            </select>
                        </div>
                        <div class="col-4">
                            <label for="postcode">Postcode</label>
                            <input type="text" class="form-control" name="postcode" id="postcode"
                                   value="${account.address.postcode}">
                        </div>
                    </div>
                </div>

                <%--Payment--%>
                <h4 class="mt-5">Billing Information</h4>
                <div class="form-group">
                    <label for="cardNumber">Card Number</label>
                    <input type="text" class="form-control" name="cardNumber" id="cardNumber" 
                        value="${account.paymentInfo.cardNumber}">
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col">
                            <label for="expiryMonth">Expiry Month</label>
                            <input type="text" class="form-control" name="expiryMonth" id="expiryMonth" 
                                   value="${account.paymentInfo.expiryMonth}">
                        </div>
                        <div class="col">
                            <label for="expiryYear">Expiry Year</label>
                            <input type="text" class="form-control" name="expiryYear" id="expiryYear" 
                                   value="${account.paymentInfo.expiryYear}">
                        </div>
                        <div class="col">
                            <label for="cvvNumber">CVV</label>
                            <input type="text" class="form-control mb-5" name="cvvNumber" id="cvvNumber"
                                   value="${account.paymentInfo.cvvNumber}">
                        </div>
                    </div>
                </div>
            </c:if>

            <a href="/IoTBay/UserManagementServlet" class=" mt-5 text-danger">Cancel</a>
            <button type="submit" class="btn btn-success float-right">Save Changes</button>

        </form>
    </div>

</div>
<jsp:include page="templates/footer.jsp"/>
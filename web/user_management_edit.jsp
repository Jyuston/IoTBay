<%-- 
    Document   : user_management_edit.jsp
    Created on : 31/05/2020, 12:15:54 PM
    Author     : justinhyuen
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<head>
    <title>Edit Account</title>
</head>
<jsp:include page="templates/header.jsp"/>

<div class="w mx-auto">
    <!--Anonymous User-->
    <c:if test="${not empty user && user.customer && user.anonymous}">
        <div class="alert alert-info alert-dismissible fade show" role="alert">
            <p class="mb-0"><strong>NOTE: </strong>Editing an anonymous account.</p>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:if>

    <h1>Edit Account</h1>
    <c:if test="${successEdit}">
        <div class="alert alert-success my-4" role="alert">
            Account # ${account.ID} has successfully been updated.<br>

            <c:if test="${user.staff}">
                <a class="alert-link" href="UserManagementServlet">Back to User Management</a>
            </c:if>

            <c:if test="${user.customer}">
                <a class="alert-link" href="main.jsp">Back to Main</a>
            </c:if>

        </div>
    </c:if>

    <form class="mt-3 mb-5" method="POST" action="${user.staff ? '/IoTBay/UserManagementEditServlet' : '/IoTBay/EditProfileServlet'}">
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
            <input type="password" class="form-control ${not empty passVErr ? 'border border-danger' : ''}"
                   name="password" value="${account.password}">
            <small class="form-text text-danger">
                <c:out value="${passVErr}"/>
            </small>
        </div>

        <c:if test="${account.customer}">

            <h4 class="mt-5">Shipping Address</h4>
            <div class="form-group">
                <label for="addressLine1">Address</label>
                <input type="text" class="form-control ${not empty addressVErr ? 'border border-danger' : ''}"
                       name="addressLine1" id="addressLine1"
                       value="${account.address.addressLine1}"
                       placeholder="42 Wallaby Way">
                <small class="form-text text-danger">
                    <c:out value="${addressVErr}"/>
                </small>
            </div>
            <div class="form-group">
                <label for="addressLine2">Address 2 (Optional)</label>
                <input type="text" class="form-control ${not empty address2VErr ? 'border border-danger' : ''}"
                       name="addressLine2" id="addressLine2"
                       value="${account.address.addressLine2}">
                <small class="form-text text-danger">
                    <c:out value="${address2VErr}"/>
                </small>
            </div>
            <div class="form-group">
                <label for="suburb">Suburb</label>
                <input type="text" class="form-control ${not empty suburbVErr ? 'border border-danger' : ''}"
                       name="suburb" id="suburb"
                       value="${account.address.suburb}">
                <small class="form-text text-danger">
                    <c:out value="${suburbVErr}"/>
                </small>
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
                        <input type="text"
                               class="form-control ${not empty postcodeVErr ? 'border border-danger' : ''}"
                               name="postcode" id="postcode"
                               value="${account.address.postcode}">
                        <small class="form-text text-danger">
                            <c:out value="${postcodeVErr}"/>
                        </small>
                    </div>
                </div>
            </div>

            <%--Payment--%>
            <h4 class="mt-5">Billing Information</h4>
            <div class="form-group">
                <label for="cardNumber">Card Number</label>
                <input type="text" class="form-control ${not empty cardNumberVErr ? 'border border-danger' : ''}"
                       name="cardNumber" id="cardNumber" placeholder="0000 0000 0000 0000"
                       value="${account.paymentInfo.cardNumber}">
                <small class="form-text text-danger">
                    <c:out value="${cardNumberVErr}"/>
                </small>
            </div>
            <div class="form-group">
                <div class="row">
                    <div class="col">
                        <label for="expiryMonth">Expiry Month</label>
                        <input type="text"
                               class="form-control ${not empty expiryMonthVErr ? 'border border-danger' : ''}"
                               name="expiryMonth" id="expiryMonth"
                               value="${account.paymentInfo.expiryMonth}">
                        <small class="form-text text-danger">
                            <c:out value="${expiryMonthVErr}"/>
                        </small>
                    </div>
                    <div class="col">
                        <label for="expiryYear">Expiry Year</label>
                        <input type="text"
                               class="form-control ${not empty expiryYearVErr ? 'border border-danger' : ''}"
                               name="expiryYear" id="expiryYear"
                               value="${account.paymentInfo.expiryYear}">
                        <small class="form-text text-danger">
                            <c:out value="${expiryYearVErr}"/>
                        </small>
                    </div>
                    <div class="col">
                        <label for="cvvNumber">CVV</label>
                        <input type="password" class="form-control ${not empty cvvVErr ? 'border border-danger' : ''}"
                               name="cvvNumber" id="cvvNumber"
                               value="${account.paymentInfo.cvvNumber}">
                        <small class="form-text text-danger mb-5">
                            <c:out value="${cvvVErr}"/>
                        </small>
                    </div>
                </div>
            </div>
        </c:if>

        <a href="${user.staff ? 'UserManagementServlet' : 'main.jsp'}" class="text-danger btn btn-link float-right">Cancel</a>
        <button type="submit" class="btn btn-success">Save Changes</button>
    </form>
</div>

<jsp:include page="templates/footer.jsp"/>
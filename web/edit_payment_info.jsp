<%--
    Document   : paymentmanagement
    Created on : 28/05/2020, 11:04:58 PM
    Author     : olive
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <title>Payment Management</title>
</head>
<jsp:include page="templates/header.jsp"/>

<div class="w mx-auto">
    <!--Success Edit-->
    <c:if test="${not empty successEdit}">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            <p class="mb-0">Payment Information updated successfully!</p>
            <p class="mb-0">
                <a class="alert-link" href="checkout.jsp">Back to Checkout</a>
            </p>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:if>

    <!--Error-->
    <c:if test="${not empty editErr}">
        <div class="alert alert-danger my-4" role="alert">
            <c:out value="${editErr}"/>
        </div>
    </c:if>

    <%--Shipping--%>
    <form action="EditPaymentInfoServlet" method="post">
        <h4>Shipping Address</h4>
        <div class="form-group">
            <label for="addressLine1">Address</label>
            <input type="text" class="form-control" name="addressLine1" id="addressLine1"
                   value="${user.address.addressLine1}" required>

        </div>
        <div class="form-group">
            <label for="addressLine2">Address 2 (Optional)</label>
            <input type="text" class="form-control" name="addressLine2" id="addressLine2"
                   value="${user.address.addressLine2}">
        </div>
        <div class="form-group">
            <label for="suburb">Suburb</label>
            <input type="text" class="form-control" name="suburb" id="suburb"
                   value="${user.address.suburb}" required>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col">
                    <label for="state">State</label>
                    <select class="form-control" name="state" id="state" value="${user.address.state}"
                            required>
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
                    <input type="number" class="form-control" name="postcode" id="postcode"
                           value="${user.address.postcode}" required>
                </div>
            </div>
        </div>

        <%--Payment information--%>
        <h4 class="mt-5">Billing Information</h4>
        <div class="form-group">
            <label for="cardNumber">Card Number</label>
            <input type="text" class="form-control" name="cardNumber" id="cardNumber"
                   value="${user.paymentInfo.cardNumber}" required>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col">
                    <label for="expiryMonth">Expiry Month</label>
                    <input type="text" class="form-control" name="expiryMonth" id="expiryMonth"
                           value="${user.paymentInfo.expiryMonth}" required>
                </div>
                <div class="col">
                    <label for="expiryYear">Expiry Year</label>
                    <input type="text" class="form-control" name="expiryYear" id="expiryYear"
                           value="${user.paymentInfo.expiryYear}" required>
                </div>
                <div class="col">
                    <label for="cvvNumber">CVV</label>
                    <input type="password" class="form-control" name="cvvNumber" id="cvvNumber"
                           value="${user.paymentInfo.cvvNumber}" required>
                </div>
            </div>
        </div>

        <div>
            <a href="checkout.jsp" class="text-danger float-right btn btn-link">Cancel</a>
            <button type="submit" class="btn btn-success px-4">Update</button>
        </div>
    </form>
</div>


<jsp:include page="templates/footer.jsp"/>
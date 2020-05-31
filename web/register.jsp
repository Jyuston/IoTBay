<%@ page import="uts.isd.model.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>Register</title>
</head>
<jsp:include page="templates/header.jsp"/>

<%
    Boolean success = (Boolean) request.getAttribute("success");
    String errorMsg = (String) request.getAttribute("errorRegister");

    // If user registration successful
    if (success != null) {
%>
<div class="row">
    <div class="col"></div>
    <div class="col-md-auto">
        <div class="alert alert-success" role="alert">
            <h4 class="alert-heading"> Registration Successful!</h4>
            <a class="btn btn-primary btn-lg btn-block" href="main.jsp"> Enter Site </a>
        </div>
    </div>
    <div class="col"></div>
</div>
<% } else { %>

<div class="row">
    <div class="col"></div>
    <div class="col-lg-6 col-md-auto">
        <form action="RegistrationServlet" method="post">
            <h1>Register</h1>

            <%-- If error message --%>
            <% if (errorMsg != null) { %>
            <div class="alert alert-danger my-4" role="alert">
                <%= errorMsg %>
            </div>
            <% } %>

            <%--Details--%>
            <h4>Details</h4>
            <div class="form-group">
                <div class="row">
                    <div class="col">
                        <label for="firstName">First Name</label>
                        <input type="text" class="form-control" name="firstName" id="firstName" placeholder="Mike"
                               required>
                    </div>
                    <div class="col">
                        <label for="lastName">Last Name</label>
                        <input type="text" class="form-control" name="lastName" id="lastName" placeholder="Wazowski"
                               required>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" name="email" id="email" placeholder="email@example.com"
                       aria-describedby="emailHelp" required>
                <small id="emailHelp" class="form-text text-muted">Must be a valid email address.</small>
            </div>
            <div class="form-group">
                <label for="contactNumber">Phone Number</label>
                <input type="number" class="form-control" name="contactNumber" id="contactNumber" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>

            <%--Shipping--%>
            <h4 class="mt-5">Shipping Address</h4>
            <div class="form-group">
                <label for="addressLine1">Address</label>
                <input type="text" class="form-control" name="addressLine1" id="addressLine1"
                       placeholder="Sherman 42 Wallaby Way, Sydney"
                       required>
            </div>
            <div class="form-group">
                <label for="addressLine2">Address 2 (Optional)</label>
                <input type="text" class="form-control" name="addressLine2" id="addressLine2">
            </div>
            <div class="form-group">
                <label for="suburb">Suburb</label>
                <input type="text" class="form-control" name="suburb" id="suburb" required>
            </div>
            <div class="form-group">
                <div class="row">
                    <div class="col">
                        <label for="state">State</label>
                        <select class="form-control" name="state" id="state" required>
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
                        <input type="number" class="form-control" name="postcode" id="postcode" required>
                    </div>
                </div>
            </div>

            <%--Payment--%>
            <h4 class="mt-5">Billing Information</h4>
            <div class="form-group">
                <label for="cardNumber">Card Number</label>
                <input type="number" class="form-control" name="cardNumber" id="cardNumber" required>
            </div>
            <div class="form-group">
                <div class="row">
                    <div class="col">
                        <label for="expiryMonth">Expiry Month</label>
                        <input type="text" class="form-control" name="expiryMonth" id="expiryMonth" required>
                    </div>
                    <div class="col">
                        <label for="expiryYear">Expiry Year</label>
                        <input type="text" class="form-control" name="expiryYear" id="expiryYear" required>
                    </div>
                    <div class="col">
                        <label for="cvvNumber">CVV</label>
                        <input type="password" class="form-control" name="cvvNumber" id="cvvNumber" required>
                    </div>
                </div>
            </div>

            <%--TOS--%>
            <div class="form-group form-check mt-5 mb-4">
                <input type="checkbox" class="form-check-input" id="tos" name="tos" required>
                <label class="form-check-label" for="tos">I Agree to the Terms of Service</label>
            </div>

            <%--Used to tell if the form has been submitted within Scriplet tag above--%>
            <input type="hidden" name="submitted" value="true">

            <%--form actions--%>
            <button type="submit" class="btn btn-primary btn-block my-3">Submit</button>
            <a href="index.jsp" class="text-center d-block text-danger">Cancel</a>
        </form>
    </div>
    <div class="col"></div>
</div>
<% } %>

<jsp:include page="templates/footer.jsp"/>

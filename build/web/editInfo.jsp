<%-- 
    Document    editInfo
    Created on  8 Apr 2020, 21907 pm
    Author      denni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/workshop.css">
        <title>IoT Bay</title>
    </head>
    
    <%
        Customer customer = (Customer)session.getAttribute("customer");
        String updated = request.getParameter("detailsUpdated");
    %>
    
    <body>
        <div class="container">        
            <nav class="navbar navbar-expand-lg fixed-top navbar-light bg-light" style="background-color: #e3f2fd;">
                <span class="navbar-brand mb-0 h1">IoTBay</span>
                <div class="navbar-nav mr-auto mt-2 mt-lg-0">
                    <a class="nav-item nav-link" href="mainPage.jsp">Home</a>
                    <a class="nav-item nav-link border-top active" href="editInfo.jsp">Edit Details</a> 
                </div>

                <a class="nav-item nav-link my-2 my-lg-0" href="logout.jsp">Logout</a>             
            </nav>
            
            <div class="row">
                <div class="col">                    
                </div>               
                <% if (updated != null && updated.equals("yes")) { 
                    String firstName = request.getParameter("firstName");
                    String lastName = request.getParameter("lastName");
                    String password = request.getParameter("password");
                    String dateOfBirth = request.getParameter("dob");
                    String email = request.getParameter("email");              
                    String contactNumber = request.getParameter("contactNumber");
                    String streetAddressLine1 = request.getParameter("streetAddressLine1");
                    String streetAddressLine2 = request.getParameter("streetAddressLine2");
                    String suburb = request.getParameter("suburb");
                    String postcode = request.getParameter("postCode");
                    String state = request.getParameter("state");
                    String cardNumber = request.getParameter("cardNumber");
                    String cardCVC = request.getParameter("cardCVC");
                    String expiryMonth = request.getParameter("expiryMonth");
                    String expiryYear = request.getParameter("expiryYear");

                    customer = new Customer(firstName, lastName, password, dateOfBirth, email, contactNumber, streetAddressLine1, streetAddressLine2, suburb, postcode, state, cardNumber, cardCVC, expiryMonth, expiryYear);
                    session.setAttribute("customer", customer);                
                %>
                    <div class="col-auto">
                        <div class="alert alert-success" role="alert">
                        <h4 class="alert-heading">Details Updated!</h4>
                        <p id="successAlertText">Your details have been updated.</p>
                        <a class="btn btn-primary btn-lg btn-block" href="mainPage.jsp">Return</a>                        
                        </div>                        
                    </div>
                    
                
                <% } else { %>
                    <div class="col-6">
                        <h1 id="registrationHeader"> Modify Details </h1>
                        <p id="pageDescription"> Edit your information </p>

                        <form method="post" action="editInfo.jsp">
                            <h2> Your Personal Info </h2> 
                            <div class="form-row">                                                                         
                                <div class="form-group col-md-6">                                           
                                    <label>First Name</label>
                                    <input class="form-control" type="text" name="firstName" value="${customer.firstName}" required">
                                </div>
                                <div class="form-group col-md-6">
                                    <label>Last Name</label> 
                                    <input class="form-control" type="text" name="lastName" value="${customer.lastName}" required">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">                                           
                                    <label>Email</label>
                                    <input class="form-control" type="email" name="email" value="${customer.email}" required>
                                </div>
                                <div class="form-group col-md-6">                                           
                                    <label>Contact Number</label>
                                    <input class="form-control" type="text" name="contactNumber" value="${customer.contactNumber}" required>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label>Date of Birth</label> 
                                    <input class="form-control" type="date" name="dob" value="${customer.dob}" required>
                                </div>
                                <div class="form-group col-md-6">
                                    <label>Password</label>
                                    <input class="form-control" type="password" name="password" value="${customer.password}" required>
                                </div>
                            </div>

                            <br>
                            <h2> Your Delivery Info </h2>
                            <div class="form-row">     
                                <div class="form-group col-md-8">
                                    <label>Address Line 1</label> 
                                    <input class="form-control" type="text"  name="streetAddressLine1" value="${customer.addressLine1}" required">
                                </div>                               
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-8">
                                    <label>Address Line 2</label> 
                                    <input class="form-control" type="text"  name="streetAddressLine2" value="${customer.addressLine2}">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label>Suburb</label> 
                                    <input class="form-control" type="text" name="suburb" value="${customer.suburb}" required">
                                </div>
                                <div class="form-group col-md-2">
                                    <label>Post Code</label> 
                                    <input class="form-control" type="text" name="postCode" value="${customer.postCode}" required">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label>State</label>
                                    <select class="form-control" name="state" required">
                                        <option hidden>${customer.state}</option>
                                        <option>QLD</option>
                                        <option>NSW</option>
                                        <option>ACT</option>
                                        <option>VIC</option>
                                        <option>TAS</option>
                                        <option>SA</option>
                                        <option>WA</option>
                                        <option>NT</option>
                                    </select>
                                </div>
                            </div>

                            <br>
                            <h2> Billing </h2>
                            <div class="form-row">
                                <div class="form-group col-md-8">
                                    <label>Card Number</label>
                                    <input class="form-control" type="text" name="cardNumber" value="${customer.customerPaymentInfo.cardNumber}" required>
                                </div>
                                <div class="form-group col-md-2">
                                    <label>CVC</label>
                                    <input class="form-control" type="password" name="cardCVC" size="3" value="${customer.customerPaymentInfo.cvc}" required>                                         
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label>Card Expiry Month</label>
                                    <select class="form-control" name="expiryMonth" value="${customer.customerPaymentInfo.expiryMonth}" required">                                       
                                        <option hidden>${customer.customerPaymentInfo.expiryMonth}</option>
                                        <option>01</option>
                                        <option>02</option>
                                        <option>03</option>
                                        <option>04</option>
                                        <option>05</option>
                                        <option>06</option>
                                        <option>07</option>
                                        <option>08</option>
                                        <option>09</option>
                                        <option>10</option>
                                        <option>11</option>
                                        <option>12</option>                                        
                                    </select>
                                </div>
                                <div class="form-group col-md-4">
                                    <label>Card Expiry Year</label>
                                    <select class="form-control" name="expiryYear" value="${customer.customerPaymentInfo.expiryYear}" required>
                                        <option hidden>${customer.customerPaymentInfo.expiryYear}</option>
                                        <option>20</option>
                                        <option>21</option>
                                        <option>22</option>
                                        <option>23</option>
                                        <option>24</option>                                      
                                    </select>
                                </div>
                            </div>                                                                            
                            <input class="form-control" type="hidden" name="detailsUpdated" value="yes">
                            <div class="form-row">
                                <div class="form-group col-md-4">                                    
                                    <input type="submit" class="btn btn-primary btn-lg btn-block" value="Save"></a>
                                </div>
                                <div class="form-group col-md-4">
                                    <a class="btn btn-primary btn-lg btn-block" href="mainPage.jsp">Return</a>
                                </div>
                            </div>
                        </form>                        
                    </div>
                
                <% } %>
                <div class="col">
                </div>
                
                
            </div>
        </div>
    </body>
</html>

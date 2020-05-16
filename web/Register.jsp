<%-- 
    Document    register
    Created on  8 Apr 2020, 21907 pm
    Author      denni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.Customer"%>
<%@page import="uts.isd.controller.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/workshop.css">
        <title>IoT Bay</title>
    </head>
    
    <body>
        <div class="container">        
            <nav class="navbar navbar-expand-lg fixed-top navbar-light bg-light" style="background-color: #e3f2fd;">
                <span class="navbar-brand mb-0 h1">IoTBay</span>
                <div class="navbar-nav mr-auto mt-2 mt-lg-0">
                    <a class="nav-item nav-link border-top active" href="index.jsp">Home</a>
                </div>
            </nav>
            
            <div class="row">
                <div class="col">
                    
                </div>

                <% 
                    String registrationSubmitted = request.getParameter("registrationFormSubmitted");
                        
                    if (registrationSubmitted != null && registrationSubmitted.equals("yes")) {
                        String firstName = request.getParameter("firstName");
                        String lastName = request.getParameter("lastName");
                        String password = request.getParameter("password");
                        String email = request.getParameter("email");              
                        String contactNumber = request.getParameter("contactNumber");
                        String streetAddressLine1 = request.getParameter("streetAddressLine1");
                        String streetAddressLine2 = request.getParameter("streetAddressLine2");
                        String suburb = request.getParameter("suburb");
                        String postcode = request.getParameter("postCode");
                        String state = request.getParameter("state");
                        String cardNumber = request.getParameter("cardNumber");
                        String cardCVV = request.getParameter("cardCVV");
                        String expiryMonth = request.getParameter("expiryMonth");
                        String expiryYear = request.getParameter("expiryYear");
                        
                        RegistrationController regController = new RegistrationController(firstName, lastName, password, email, contactNumber, streetAddressLine1, streetAddressLine2,  suburb, postcode, state, cardNumber, cardCVV, expiryMonth, expiryYear);

                        Customer customer = regController.RegisterCustomer();
                        
                        if (customer != null) {
                            session.setAttribute("customer", customer);
                %>

                            <div class="col-auto">
                                <div class="alert alert-success" role="alert">
                                <h4 class="alert-heading"> Registration Successful!</h4>
                                <a class="btn btn-primary btn-lg btn-block" href="mainPage.jsp"> Enter Site </a>                        
                                </div>                        
                            </div>
                
                        <% } } else { %>
                
                <div class="col--md-auto">
                    <h1 id="registrationHeader"> New Customer? </h1>
                    <p id="pageDescription"> We're always excited to welcome new customers. To register an account, we need to know a little about you! </p>
                    <form method="post" action="Register.jsp">
                        <h2> Your Personal Info </h2> 
                        <div class="form-row">                                                                         
                            <div class="form-group col-md-6">                                           
                                <label>First Name</label>
                                <input class="form-control" type="text" name="firstName" required">
                            </div>
                            <div class="form-group col-md-6">
                                <label>Last Name</label> 
                                <input class="form-control" type="text" name="lastName" required">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">                                           
                                <label>Email</label>
                                <input class="form-control" type="email" name="email" required>
                            </div>
                            <div class="form-group col-md-6">                                           
                                <label>Contact Number</label>
                                <input class="form-control" type="text" name="contactNumber" required">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label>Password</label>
                                <input class="form-control" type="password" name="password" required>
                            </div>
                        </div>

                        <br>
                        <h2> Your Delivery Info </h2>
                        <div class="form-row">     
                            <div class="form-group col-md-8">
                                <label>Address Line 1</label> 
                                <input class="form-control" type="text"  name="streetAddressLine1" required">
                            </div>                               
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-8">
                                <label>Address Line 2</label> 
                                <input class="form-control" type="text"  name="streetAddressLine2"">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label>Suburb</label> 
                                <input class="form-control" type="text" name="suburb" required">
                            </div>
                            <div class="form-group col-md-2">
                                <label>Post Code</label> 
                                <input class="form-control" type="text" name="postCode" required">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label>State</label>
                                <select class="form-control" name="state" required">
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
                                <input class="form-control" type="text" name="cardNumber" required>
                            </div>
                            <div class="form-group col-md-2">
                                <label>CVC</label>
                                <input class="form-control" type="password" name="cardCVV" size="3" required>                                         
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <label>Card Expiry Month</label>
                                <select class="form-control" name="expiryMonth" required">                                       
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
                                <select class="form-control" name="expiryYear" required>
                                    <option>20</option>
                                    <option>21</option>
                                    <option>22</option>
                                    <option>23</option>
                                    <option>24</option>                                      
                                </select>
                            </div>
                        </div>                            
                        <br>
                        <h2> Terms and Conditions </h2>
                        <div class="form-row">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" name="tos" value="agree" required>
                                <label class="form-check-label">Agree to Terms and Conditions</label>                                     
                            </div>
                        </div>
                        <input class="form-control" type="hidden" name="registrationFormSubmitted" value="yes">
                        <a href="Register.jsp"><input type="submit" class="btn btn-primary btn-lg btn-block" value="Register"></a>
                    </form>
                </div>
                <% } %>
                
                <div class="col">
                </div>
            </div>
        </div>
    </body>
</html>

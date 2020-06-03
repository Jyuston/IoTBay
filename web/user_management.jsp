<%-- 
    Document   : user_management
    Created on : 26/05/2020, 10:14:16 AM
    Author     : justinhyuen
--%>
<%@page import="uts.isd.model.Account"%>
<%@page import="uts.isd.model.Customer"%>
<%@page import="uts.isd.model.Staff"%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Will need to wrap this in an If statement to check the session if they are a Staff0-Admin TRUE-->
<jsp:include page="templates/header.jsp"/>
<head>
    <title>User Management</title>
</head>
<h1 class="mb-4">User Management</h1>

<h2> Search a User </h2>
<div class="container-fluid">
    <div class="row"> 
        <div class="form-group">
            <c:if test="${not empty errorUserManagement}">
                <div class="alert alert-danger my-4" role="alert">
                    ${errorUserManagement}
                </div>
            </c:if>
            <c:if test="${success}">
                <div class="alert alert-success my-4" role="alert">
                    <h2> YOUR CRAZZY You MADE A NEW ACCOUNT </h2>
                </div>
            </c:if>
            <form action="/IoTBay/UserManagementServlet" method="POST">
                <table>
                    <tr>
                        <td><input class="form-control-sm my-2 mr-1" name="firstName" placeholder="First Name" required></td>
                        <td><input class="form-control-sm" name="lastName" placeholder="Last Name" required></td>
                    </tr>
                    <tr>
                        <td><input class="form-control-sm m-r" name="contactNumber" placeholder="Contact Number" required></td> 
                    </tr>
                    <tr>
                        <td><button type="submit" class="btn btn-info btn-sm my-3">Search</button></td>
                    </tr>
                </table>
            </form>
        </div>

        <c:if test="${not empty resultAccount}">
            <div class="col-md-auto mx-auto my-2 p-4 border border-light rounded">
                <!--This will be updated by Servlet based on the Form--> 
                <table class="my-auto">
                    <tr colspan="3" class="py-1"><th><h4>${resultAccount.firstName} ${resultAccount.lastName}</h4></th></tr>
                    <tr><td colspan="3" class="py-1">${resultAccount.class.getSimpleName()}</td></tr>
                    <tr><td colspan="3" class="py-1">#${resultAccount.ID}</td></tr>
                    <tr><td colspan="3" class="py-1">${resultAccount.email}</td></tr>
                    <tr><td colspan="3" class="py-1">Active: ${resultAccount.active}</td></tr>

                    <tr>
                        <c:choose>
                            <c:when test="${resultAccount.active}">
                                <form action="/IoTBay/UserManagementActiveServlet" method="POST">
                                    <td><button class="btn btn-primary btn-sm my-2 mr-2">Deactivate</button></td>
                                    <input type="hidden" value="deactivate" name="action">
                                    <input type="hidden" name="accountID"  value="${resultAccount.ID}">
                                </form>                          
                            </c:when>
                            <c:when test="${!resultAccount.active}">
                                <form action="/IoTBay/UserManagementActiveServlet" method="POST">
                                    <td><button class="btn btn-warning btn-sm my-2 mr-2">Activate</button></td>
                                    <input type="hidden" value="activate" name="action">
                                    <input type="hidden" name="accountID"  value="${resultAccount.ID}">                            
                                </form>
                            </c:when>
                        </c:choose>
                    
                    <form action="/IoTBay/UserManagementEditServlet" method="POST">
                        <input type="hidden" name="accountID"  value="${resultAccount.ID}">
                        <input type="hidden" name="accountEmail" value="${resultAccount.email}">
                        <input type="hidden" name="accountPassword" value="${resultAccount.password}">
                        <td><button class="btn btn-info btn-sm my-2 mr-2">Edit</button></td>
                    </form>

                    <form action="/IoTBay/UserManagementDeleteServlet" method="GET">
                        <td><button class="btn btn-danger btn-sm my-2 float-right">Delete</button></td>
                    </form>   
                    </tr>

                </table>
            </div>  
        </c:if>

    </div>
</div>

<div class="my-4">
    <form action="/IoTBay/UserManagementCreateServlet" method="GET">
        <button type="submit" class="btn btn-success btn-sm"> + Add New Account</button>
    </form>
</div>


<h2> All Users </h2>
<div class="my-3">
    <h4 class="mb-3">Customer</h4>
    <div style="max-height: 400px" class="overflow-auto mb-4">
        <table class="table overflow-auto" style="max-height: 100px">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Email</th>
                    <th scope="col">Contact Number</th>
                    <th></th>
                </tr>
            </thead>
            <c:forEach items="${customerList}" var="Customer" varStatus="count">
                <tr>
                    <td>${Customer.ID}</td>
                    <td>${Customer.firstName} ${Customer.lastName}</td>
                    <td>${Customer.email}</td>
                    <td>${Customer.contactNumber}</td>
                    <td> 
                        <form action="/IoTBay/UserManagementEditServlet" method="POST">
                            <input type="hidden" name="accountID" value="${Customer.ID}">
                            <input type="hidden" name="accountEmail" value="${Customer.email}">
                            <input type="hidden" name="accountPassword" value="${Customer.password}">
                            <button type="submit">Edit</button>
                        </form> 
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <h4 class="mb-3">Staff</h4>
    <div style="max-height: 400px" class="overflow-auto mb-4">
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Email</th>
                    <th scope="col">Contact Number</th>
                    <th scope="col">Staff-Admin</th>
                    <th></th>
                </tr>
            </thead>
            <c:forEach items="${staffList}" var="Staff" varStatus="count">
                <tr>
                    <td>${Staff.ID}</td>
                    <td>${Staff.firstName} ${Staff.lastName}</td>
                    <td>${Staff.email}</td>
                    <td>${Staff.contactNumber}</td>
                    <td>${Staff.admin}</td>
                    <td>
                        <form action="/IoTBay/UserManagementEditServlet" method="POST">
                            <input type="hidden" name="accountID" value="${Staff.ID}">
                            <input type="hidden" name="accountEmail" value="${Staff.email}">
                            <input type="hidden" name="accountPassword" value="${Staff.password}">
                            <button type="submit">Edit</button>
                        </form> 
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<h2>Audit Logs</h2>
<div class="my-4">
    <table class="table">
        <thead>
            <tr>
                <th scope="col">Date</th>
                <th scope="col">Operation</th>
                <th scope="col">#</th>
                <th scope="col">Email</th>
                <th scope="col">Name</th>
                <th scope="col">Account</th>

            </tr>
        </thead>
    </table>
</div>



<jsp:include page="templates/footer.jsp"/>

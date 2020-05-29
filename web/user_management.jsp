<%-- 
    Document   : user_management
    Created on : 26/05/2020, 10:14:16 AM
    Author     : justinhyuen
--%>
<%@page import="uts.isd.model.Account"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="templates/header.jsp"/>
<head>
    <title>User Management</title>
</head>
<%
    List customerList = (List) request.getAttribute("customerList");
    List staffList = (List) request.getAttribute("staffList");
    Account searchedAccount = (Account) request.getAttribute("resultAccount");
%>
<h1 class="mb-4">User Management</h1>

<h2> Search a User </h2>
<div class="container-fluid">
    <div class="row"> 
        <div class="form-group">
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
        
<%
    if(searchedAccount != null) {
%>
        <div class="col-md-auto mx-auto my-2 p-4 border border-light rounded">
            <!--This will be updated by Servlet based on the Form-->  
            <table class="my-auto">
                <tr colspan="3" class="py-1"><h4>${searchedAccount.firstName} + ${searchedAccount.lastName}</h4></th></tr>
                <tr><td colspan="3" class="py-1">${searchedAccount.class}</td></tr>
                <tr><td colspan="3" class="py-1">${searchedAccount.ID}</td></tr>
                <tr><td colspan="3" class="py-1">${searchedAccount.email}</td></tr>
                <tr><td colspan="3" class="py-1">${searchedAccount.active}</td></tr>
                <tr>
                    <td><button class="btn btn-primary btn-sm my-2 mr-2">Activate</button></td>
                    <td><button class="btn btn-warning btn-sm my-2 mr-2">Deactivate</button></td>
                    <td><button class="btn btn-info btn-sm my-2 mr-2">Edit</button></td>
                    <td><button class="btn btn-danger btn-sm my-2 float-right">Delete</button></td>
                </tr>  
                <tr>
            </table>
        </div>  
<%}%>
    </div>
</div>

<div class="my-4">
    <button type="button" class="btn btn-success btn-sm"> + Add New Account</button>
</div>


<h2> All Users </h2>
<div class="my-3">
    <div style="max-height: 400px" class="overflow-auto mb-4">
    <table class="table overflow-auto" style="max-height: 100px">
        <h4>Customer<h4>
        <thead>
             <tr>
                <th scope="col">#</th>
                <th scope="col">Name</th>
                <th scope="col">Email</th>
                <th scope="col">Contact Number</th>
            </tr>
        </thead>
        <c:forEach items="${customerList}" var="Customer" varStatus="count">
            <tr>
                <td>${Customer.ID}</td>
                <td>${Customer.firstName} ${Customer.lastName}</td>
                <td>${Customer.email}</td>
                <td>${Customer.contactNumber}</td>
                
            </tr>
        </c:forEach>
    </table>
    </div>

    <div style="max-height: 400px" class="overflow-auto mb-4">
    <table class="table">
        <h4>Staff<h4>
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Name</th>
                <th scope="col">Email</th>
                <th scope="col">Contact Number</th>
                <th scope="col">Staff-Admin</th>
            </tr>
            </thead>
        <c:forEach items="${staffList}" var="Staff" varStatus="count">
            <tr>
                <td>${Staff.ID}</td>
                <td>${Staff.firstName} ${Staff.lastName}</td>
                <td>${Staff.email}</td>
                <td>${Staff.contactNumber}</td>
                <td>${Staff.admin}</td>
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

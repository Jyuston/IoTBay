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
<h1 class="mb-2">User Management</h1>

<h2 class="mb-3"> Search a User </h2>
<div class="container-fluid">
    <div class="row"> 
        <div class="form-group">

            <c:if test="${not empty errorUserManagement}">
                <c:if test="${successDelete != true}">
                    <div class="alert alert-danger my-4" role="alert">
                        ${errorUserManagement}
                    </div>
                </c:if>
            </c:if>

            <c:if test="${success}">
                <div class="alert alert-success my-4" role="alert">
                    <h2> New account created. </h2>
                </div>
            </c:if>

            <c:if test="${successDelete}">
                <div class="alert alert-success my-4" role="alert">
                    <h2> Account has been deleted. </h2>
                </div>
            </c:if>

            <form action="/IoTBay/UserManagementServlet" method="POST">
                <table>
                    <tr>
                        <td><input class="form-control-sm mb-2 mr-2  ${not empty nameVErr ? 'border border-danger' : ''}" name="firstName" placeholder="First Name">
                                <small class="form-text text-danger mb-1">
                                    <c:out value="${nameVErr}"/>
                                </small>
                        </td>
                        
                        <td><input class="form-control-sm mb-2 ${not empty nameVErr ? 'border border-danger' : ''}" name="lastName" placeholder="Last Name">
                                <small class="form-text text-danger mb-1">
                                    <c:out value="${nameVErr}"/>
                                </small>
                        </td>
                    </tr>
                    <tr>
                        <td><input class="form-control-sm ${not empty contactNumberVErr ? 'border border-danger' : ''}" name="contactNumber" placeholder="Contact Number">
                                <small class="form-text text-danger">
                                    <c:out value="${contactNumberVErr}"/>
                                </small>
                        </td> 
                    </tr>
                    <tr>
                        <td><button type="submit" class="btn btn-info btn-sm my-2">Search</button></td>
                    </tr>
                </table>
            </form>

        </div>

        <c:if test="${not empty resultAccount}">
            <div class="col-md-auto mx-auto my-2 p-4 border border-dark rounded">
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

                    <form action="/IoTBay/UserManagementEditServlet" method="GET">
                        <td><button class="btn btn-info btn-sm my-2 mr-2">Edit</button></td>
                        <input type="hidden" name="accountID"  value="${resultAccount.ID}">   
                    </form>

                    <form action="/IoTBay/UserManagementDeleteServlet" method="POST">
                        <td><button class="btn btn-danger btn-sm my-2 float-right" onclick="return confirm('Are you sure you want to delete this Account?')">Delete</button></td>
                        <input type="hidden" name="accountID"  value="${resultAccount.ID}">
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
    <div style="max-height: 400px;" class="table-responsive overflow-auto table-light">
        <table class="table text-dark">

                <tr class="d-flex table-info">
                    <th class="col-1">#</th>
                    <th class="col-3">Name</th>
                    <th class="col-4">Email</th>
                    <th class="col-2">Contact Number</th>
                    <th class="col-2"></th>
                </tr>
    
        </table>
    </div>

    <div style="max-height: 400px;" class="table-responsive overflow-auto mb-4 table-striped table-light">
        <table class="table text-dark">
            
            <c:forEach items="${customerList}" var="Customer" varStatus="count">
                <tr class="d-flex">
                    <td class="col-1">${Customer.ID}</td>
                    <td class="col-3">${Customer.firstName} ${Customer.lastName}</td>
                    <td class="col-4">${Customer.email}</td>
                    <td class="col-2">${Customer.contactNumber}</td>
                    <td class="col-2"> 
                        <form action="/IoTBay/UserManagementEditServlet" method="GET">
                            <input type="hidden" name="accountID"  value="${Customer.ID}">
                            <button type="submit">Edit</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
                
        </table>
    </div>

    <h4 class="mb-3">Staff</h4>
    <div style="max-height: 400px" class="table-responsive overflow-auto table-striped table-light">
        <table class="table text-dark">
            
                <tr class="d-flex table-info">
                    <th class="col-1">#</th>
                    <th class="col-2">Name</th>
                    <th class="col-3">Email</th>
                    <th class="col-2">Contact Number</th>
                    <th class="col-2">Staff-Admin</th>
                    <th class="col-2"></th>
                </tr>

        </table>
    </div>
            
    <div style="max-height: 400px" class="table-responsive overflow-auto mb-4 table-striped table-light">
        <table class="table text-dark">           
            
            <c:forEach items="${staffList}" var="Staff" varStatus="count">
                <tr class="d-flex">
                    <td class="col-1">${Staff.ID}</td>
                    <td class="col-2">${Staff.firstName} ${Staff.lastName}</td>
                    <td class="col-3">${Staff.email}</td>
                    <td class="col-2">${Staff.contactNumber}</td>
                    <td class="col-2">${Staff.admin}</td>
                    <td class="col-2">
                        <form action="/IoTBay/UserManagementEditServlet" method="GET">
                            <input type="hidden" name="accountID"  value="${Staff.ID}">
                            <button type="submit">Edit</button>
                        </form> 
                    </td>
                </tr>
            </c:forEach>
                
        </table>
    </div>
</div>

<h2>Audit Logs</h2>
<div class="my-4 table-striped table-light">
    <table class="table text-dark">
        <thead>
            <tr class="table-info">
                <th scope="col">ID Tampered with</th>
                <th scope="col">Action</th>
                <th scope="col">Performed On</th>
                <th scope="col">Email</th>
                <th scope="col">Name</th>
                <th scope="col">Account</th>              
            </tr>
        </thead>
    </table>
</div>

<jsp:include page="templates/footer.jsp"/>

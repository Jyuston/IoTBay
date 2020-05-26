<%-- 
    Document   : user_management
    Created on : 26/05/2020, 10:14:16 AM
    Author     : justinhyuen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="templates/header.jsp"/>
<head>
    <title>User Management</title>
</head>
<h1 class="mb-4">User Management</h1>

<h2> Add a new User Account </h2>
<div>
    <button type="button" class="btn btn-success btn-sm mt-2 mb-4"> + Add New Account</button>

</div>

<h2> Search a User </h2>
<div class="container-fluid">
    <div class="row"> 
        <div class="form-group">
            <table>
                <tr>
                    <td><input class="form-control-sm my-2 mr-1" id="firstName" name="firstName" placeholder="First Name" required></td>
                    <td><input class="form-control-sm" id="lastName" name="lastName" placeholder="Last Name" required></td>
                </tr>
                <tr>
                    <td><input class="form-control-sm m-r" id="phoneNumber" name="phoneNumber" placeholder="Phone Number" required></td> 
                </tr>
                <tr>
                    <td><button type="button" class="btn btn-info btn-sm my-2" id="submit" name="submit">Search</button></td>
                </tr>
            </table>
        </div>

        <div class="col-md-auto mx-auto">
            <!--This will be updates by Servlet based on the Form-->
            <table class="my-auto">
                <th colspan="3">John Smith</th></tr>
                <tr><td colspan="3">UD-999999999</td></tr>
                <tr><td colspan="3">JohnSmith@burrito.com</td></tr>
                <tr><td colspan="3">Staff-Admin</td></tr>
                <tr>
                    <td><button class="btn btn-primary btn-sm my-2 mr-2">Activate</button></td>
                    <td><button class="btn btn-danger btn-sm my-2 float-right">Delete</button></td>
                </tr>  
                <tr>
                    <td><button class="btn btn-warning btn-sm">Deactivate</button></td>
                </tr>
            </table>
        </div>        
    </div>
</div>


<h2> All Users </h2>

<div>
    <table class="table">
        <h4>Customer<h4>
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Email</th>
                        <th scope="col">Name</th>
                        <th scope="col">Account</th>
                    </tr>
                </thead>
                </table>

                <table class="table">
                    <h4>Staff<h4>
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Email</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Account</th>
                                </tr>
                            </thead>
                            </table>

                            </div>

                            <h2>Audit Logs</h2>
                            <div>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Email</th>
                                            <th scope="col">Name</th>
                                            <th scope="col">Account</th>
                                            <th scope="col">Operation</th>
                                        </tr>
                                    </thead>
                                </table>
                            </div>



                            <jsp:include page="templates/footer.jsp"/>
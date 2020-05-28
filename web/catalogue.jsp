<%@page import="uts.isd.model.Product" %>
<%@ page import="java.util.LinkedList" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="templates/header.jsp"/>

<head>
    <title>Search products from category</title>
</head>

<%
    LinkedList<Product> productList = (LinkedList<Product>) request.getAttribute("productList");
    Product productsNC = (Product) request.getAttribute("productsNC");
%>

<h2> Search a Product </h2>
<div class="container-fluid">
    <div class="row">
        <div class="form-group">
            <table>
                <tr>
                    <td><input class="form-control-sm my-2 mr-1" id="productName" name="productName"
                               placeholder="Product Name" required></td>
                    <td><input class="form-control-sm" id="pCat" name="productCat" placeholder="Product Category"
                               required></td>
                </tr>
                <tr>

                    <td>
                        <button type="button" class="btn btn-info btn-sm my-3" id="submit" name="submit">Search</button>
                    </td>
                </tr>
            </table>
        </div>

        <div class="col-md-auto mx-auto my-2 p-4 border border-light rounded">
            <!--This will be updated by Servlet based on the Form-->
            <table class="my-auto">
                <tr colspan="3" class="py-1"><h4>Product name</h4></th></tr>
                <tr>
                    <td colspan="3" class="py-1">product cat</td>
                </tr>
                <tr>
                    <td colspan="3" class="py-1">stock</td>
                </tr>
                <tr>
                    <td colspan="3" class="py-1">price</td>
                </tr>
                <tr>
                    <td>
                        <button class="btn btn-primary btn-sm my-2 mr-2">Edit</button>
                    </td>
                    <td>
                        <button class="btn btn-warning btn-sm my-2 mr-2">Delete</button>
                    </td>
                </tr>
                <tr>
            </table>
        </div>
    </div>
</div>


<div class="my-4">
    <button type="button" class="btn btn-success btn-sm"> + Add New Product</button>
</div>


<h2> All Products </h2>
<div class="my-3">
    <table class="table">
        <h4>Product
            <h4>
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Category</th>
                    <th scope="col">Stock</th>
                    <th scope="col">Description</th>
                    <th scope="col">Price</th>

                </tr>
                </thead>


                <c:forEach items="${productList}" var="Product" varStatus="count">
                <thead>

                <tr>
                    <td>${Product.ID}</td>
                    <td>${Product.name}</td>
                    <td>${Product.category}</td>
                    <td>${Product.stock}</td>
                    <td>${Product.description}</td>
                    <td>${Product.price}</td>


                </tr>
                </c:forEach>
    </table>


    </thead>


<jsp:include page="templates/footer.jsp"/>
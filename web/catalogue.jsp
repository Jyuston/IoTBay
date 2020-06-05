<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--@elvariable id="productList" type="java.util.List<Product>"--%>

<head>
    <title>Search products from category</title>
</head>
<jsp:include page="templates/header.jsp"/>

<h2>Search a Product</h2>
<div class="container-fluid">
    <div class="row">
        <div class="form-group">
            <form action="CatalogueServlet" method="post">
                <table>
                    <tr>
                        <td><input class="form-control-sm my-2 mr-1" id="name" name="name"
                                   placeholder="Product Name" required></td>
                        <td><input class="form-control-sm" id="pCat" name="category" placeholder="Product Category"
                                   required></td>
                    </tr>
                    <tr>

                        <td>
                            <button type="submit" class="btn btn-info btn-sm my-3" id="submit" name="submit">
                                Search
                            </button>
                        </td>
                    </tr>
                </table>
            </form>

        </div>

        <div class="col-md-auto mx-auto my-2 p-4 border border-light rounded">
            <!--This will be updated by Servlet based on the Form-->
            <table class="my-auto">
                <tr colspan="3" class="py-1"><h4>Product name</h4></tr>
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


<h2>All Products</h2>
<c:forEach items="${productList}" var="product" varStatus="count">
    <tr>
        <td>${product.ID}</td>
        <td>${product.name}</td>
        <td>${product.category}</td>
        <td>${product.stock}</td>
        <td>${product.description}</td>
        <td>${product.price}</td>
    </tr>
</c:forEach>


<jsp:include page="templates/footer.jsp"/>
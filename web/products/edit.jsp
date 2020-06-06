<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="templates/header.jsp"/>
<head>
    <title>Edit Product</title>
</head>
<h1>Edit Product</h1>
<h2> Edit Product </h2>

<div class="row">

    <div class="col-lg-6">
        <c:choose>

            <c:when test="${successEdit}">
                <div class="alert alert-success my-4" role="alert">
                        <h4>Account # ${product.ID} has successfully been updated.</h4>
                    <a class="btn btn-primary btn-lg btn-block" href="CatalogueServlet">Back to Catalogue</a>
                </div>
            </c:when>

            <c:when test="${!successEdit}">
                <form class="m-5" method="POST" action="/IoTBay/EditProductServlet">
                    <%--Details--%>
                    <h4>Details for Product #${product.ID}</h4>
                    <div class="form-group">
                        <input type="hidden" name="ID" value="${product.ID}">
                        <div class="row">         
                            <div class="col">
                                <label for="productName">Product Name</label>
                                <input type="text" class="form-control ${not empty nameVErr ? 'border border-danger' : ''}" 
                                       name="productName" value="${product.name}" required>
                            </div>
                            <div class="col">
                                <label for="productCategory">Category</label>
                                <input type="text" class="form-control ${not empty nameVErr ? 'border border-danger' : ''}" 
                                       name="productCategory" value="${product.category}" required>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="productStock">Stock</label>
                        <input type="text" class="form-control ${not empty stockVErr ? 'border border-danger' : ''}" 
                               name="productStock" value="${product.stock}" aria-describedby="emailHelp" required>    
                       
                    </div>
                    <div class="form-group">
                        <label for="productDescription">Description</label>
                        <input type="text" class="form-control ${not empty nameVErr ? 'border border-danger' : ''}" 
                               name="productDescription" value="${product.description}" required >
                    </div>
                    
                    <button type="submit" class="btn btn-success mt-4">Edit Product</button> 
                </form>
            </c:when>
        </c:choose>

    </div>

</div>
<jsp:include page="templates/footer.jsp"/>
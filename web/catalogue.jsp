<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--@elvariable id="productList" type="java.util.List<uts.isd.model.Product>"--%>

<head>
    <title>Search products from category</title>
</head>
<jsp:include page="templates/header.jsp"/>

<form class="form-inline">
    <label class="sr-only" for="searchQuery">Search</label>
    <input type="text" class="form-control mb-2 mr-2" id="searchQuery" placeholder="Search...">

    <button type="submit" class="btn btn-primary mb-2">Submit</button>
</form>

<h1>Products</h1>
<div class="product-list">
    <c:forEach items="${productList}" var="product" varStatus="count">
        <div class="product">
            <div class="img"></div>
            <div class="content card-body">
                <h5 class="card-title mr-2 d-inline">${product.name}</h5>
                <h6 class="d-inline px-2 py-1 mb-2 rounded bg-primary" style="font-size: 0.875rem; line-height: 2rem;">${product.category}</h6>

                <c:choose>
                    <c:when test="${product.stock > 0}">
                        <p class="text-success">${product.stock} currently in stock</p>
                    </c:when>
                    <c:otherwise>
                        <p class="text-danger">Out of stock</p>
                    </c:otherwise>
                </c:choose>

                <p class="font-weight-medium mb-0" style="font-size: 1.25rem">AU $${product.price}</p>
            </div>
        </div>
    </c:forEach>
</div>

<jsp:include page="templates/footer.jsp"/>
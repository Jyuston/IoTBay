<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--Request Scope EL Vars--%>
<%--@elvariable id="productList" type="java.util.List<uts.isd.model.Product>"--%>
<%--@elvariable id="prevQuery" type="java.lang.String"--%>

<head>
    <title>Search products from category</title>
</head>
<jsp:include page="templates/header.jsp"/>

<form class="form-inline mb-2" method="get" action="CatalogueServlet">
    <label class="sr-only" for="searchQuery">Search</label>
    <input type="text" class="form-control" id="searchQuery" name="query" placeholder="Search..." value="${prevQuery}">
    <%--select--%>

    <button type="submit" class="btn btn-primary mx-2">Submit</button>
    <a href="CatalogueServlet" class="text-danger">Clear</a>
</form>

<h1>Products</h1>
<div class="product-list">
    <c:forEach items="${productList}" var="product" varStatus="count">
        <div class="product">
            <div class="img"></div>
            <div class="content card-body d-flex flex-column">
                <div>
                    <a href="ProductDetailsServlet?id=${product.ID}" class="card-title mr-2" style="font-size: 1.25rem">${product.name}</a>
                    <h6 class="d-inline px-2 py-1 mb-3 rounded-pill bg-primary text-white"
                        style="font-size: 0.875rem; line-height: 2rem;">${product.category}</h6>
                </div>

                <c:choose>
                    <c:when test="${product.stock > 0}">
                        <p class="text-success flex-grow-1">${product.stock} currently in stock</p>
                    </c:when>
                    <c:otherwise>
                        <p class="text-danger flex-grow-1">Out of stock</p>
                    </c:otherwise>
                </c:choose>

                <p class="font-weight-medium mb-0" style="font-size: 1.25rem">AU $${product.price}</p>
            </div>
        </div>
    </c:forEach>
</div>

<jsp:include page="templates/footer.jsp"/>
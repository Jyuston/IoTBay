<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--Request Scope EL Vars--%>
<%--@elvariable id="productList" type="java.util.List<uts.isd.model.Product>"--%>
<%--@elvariable id="prevQuery" type="java.lang.String"--%>

<%--Session Scope EL Vars--%>
<%--@elvariable id="user" type="uts.isd.model.Account"--%>

<head>
    <title>Products Catalogue</title>
</head>
<jsp:include page="../templates/header.jsp"/>

<form class="form-inline mb-2" method="get" action="CatalogueServlet">
    <label class="sr-only" for="searchQuery">Search</label>
    <input type="text" class="form-control" id="searchQuery" name="query" placeholder="Search..." value="${prevQuery}">
    <%--select--%>

    <button type="submit" class="btn btn-primary mx-2">Submit</button>
    <a href="CatalogueServlet" class="text-danger">Clear</a>
</form>

<h1>Products</h1>

<div class="card-deck products-list">
    <c:forEach items="${productList}" var="product" varStatus="count">
        <div class="card">
            <svg class="card-img-top" style="text-anchor: middle" width="100%" height="160"
                 xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false">
                <title>Placeholder</title>
                <rect width="100%" height="100%" fill="#868e96"></rect>
                <text x="50%" y="50%" fill="#dee2e6" dy=".3em">Image</text>
            </svg>
            <div class="card-body">
                <h5 class="card-title"><a href="ProductDetailsServlet?ID=${product.ID}">${product.name}</a></h5>
                <p class="card-text price">$${product.price} AUD</p>

                <p class="card-text">
                    <c:choose>
                        <c:when test="${product.stock > 0}">
                            <small class="text-${product.stock <= 20 ? 'warning' : 'success'}">${product.stock}
                                currently in stock</small>
                        </c:when>
                        <c:otherwise>
                            <small class="text-danger">Out of stock</small>
                        </c:otherwise>
                    </c:choose>
                </p>

            </div>
            <div class="card-footer">
                <c:if test="${not empty user && user.staff}">
                    <small class="float-right">
                        <a href="EditProductServlet?ID=${product.ID}" class="text-info">Edit Product</a>
                    </small>
                </c:if>
                <form class="form-inline mb-0" method="get" action="CatalogueServlet">
                    <input type="hidden" name="category" value="${product.category}">
                    <button type="submit" class="btn btn-link text-muted p-0">
                        <small>${product.category}</small>
                    </button>
                </form>
            </div>
        </div>
    </c:forEach>
</div>

<jsp:include page="../templates/footer.jsp"/>
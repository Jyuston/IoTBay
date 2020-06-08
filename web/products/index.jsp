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

<%--Success Message--%>
<c:if test="${not empty successDelete}">
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        <p class="mb-0"><strong>Hooray! </strong>Product deleted successfully!</p>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
</c:if>

<div class="d-flex mb-2">
    <form class="form-inline mb-0" method="get" action="CatalogueServlet">
        <label class="sr-only" for="searchQuery">Search</label>
        <div class="input-group">
            <input type="text" class="form-control" id="searchQuery" name="query" placeholder="Search..." value="${prevQuery}">
            <%--select--%>
            <div class="input-group-append">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </div>
        <a href="CatalogueServlet" class="text-danger ml-2">Clear</a>
    </form>

    <c:if test="${not empty user && user.staff}">
        <a href="add.jsp" class="btn btn-success ml-auto mr-3">Add Product +</a>
    </c:if>
        
</div>

<form class="form-inline mb-0" method="get" action="CatalogueServlet">
    
    <select class="form-control" name="category" value="${product.category}">
        <option ${product.category == "Actuators" ? "selected" : ""}>Actuators</option>
        <option ${product.category == "Capacitors" ? "selected" : ""}>Capacitors</option>
        <option ${product.category == "Sensors" ? "selected" : ""}>Sensors</option>
        <option ${product.category == "Resistors" ? "selected" : ""}>Resistors</option>
    </select>

    <button type="submit" class="btn btn-primary my-2 mx-3">Sort</button>
    
</form>

<h1>Products</h1>

<c:choose>
    <c:when test="${empty productList}">
        <h5 class="my-5 text-center display-5 font-weight-normal">
            <strong>Oh no...</strong>
            <br>
            No products available 😔
        </h5>
    </c:when>

    <c:otherwise>
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
    </c:otherwise>
</c:choose>

<jsp:include page="../templates/footer.jsp"/>
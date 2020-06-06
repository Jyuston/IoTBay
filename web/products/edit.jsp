<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%--Request Scope EL Vars--%>
<%--@elvariable id="editProductErr" type="java.lang.String"--%>
<%--@elvariable id="product" type="uts.isd.model.Product"--%>
<%--@elvariable id="success" type="java.lang.Boolean"--%>

<%--Session Scope EL Vars--%>
<%--@elvariable id="user" type="uts.isd.model.Account"--%>

<head>
    <title>Edit Product</title>
</head>
<jsp:include page="../templates/header.jsp"/>

<c:choose>
    <c:when test="${empty user || not user.staff}">
        <jsp:include page="../templates/auth-error.jsp"/>
    </c:when>

    <c:otherwise>
        <div class="w mx-auto">
                <%--Success Message--%>
            <c:if test="${not empty success}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <p><strong>Product #${product.ID}</strong> updated successfully!</p>
                    <hr>
                    <p class="mb-0">
                        <a href="ProductDetailsServlet?ID=${product.ID}" class="alert-link">View product</a>
                        or return to catalogue <a href="CatalogueServlet" class="alert-link">here</a>
                    </p>
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:if>

                <%--General Errors--%>
            <c:if test="${not empty editProductErr}">
                <div class="alert alert-danger my-4" role="alert">
                    <c:out value="${editProductErr}"/>
                </div>
            </c:if>

            <h1>Edit Product #${product.ID}</h1>
            <form action="EditProductServlet" method="post" class="validate">
                <input type="hidden" name="ID" value="${product.ID}">

                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" class="form-control ${not empty emptyNameVErr ? 'border border-danger' : ''}"
                           name="name" id="name" value="${product.name}">
                    <small class="form-text text-danger">
                        <c:out value="${emptyNameVErr}"/>
                    </small>
                </div>

                <div class="form-group">
                    <label for="category">Category</label>
                    <input type="text" class="form-control ${not empty emptyCategoryVErr ? 'border border-danger' : ''}"
                           id="category" name="category" value="${product.category}">
                    <small class="form-text text-danger">
                        <c:out value="${emptyCategoryVErr}"/>
                    </small>
                </div>

                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea class="form-control ${not empty emptyDescriptionVErr ? 'border border-danger' : ''}"
                              id="description" name="description" rows="3">${product.description}</textarea>
                    <small class="form-text text-danger">
                        <c:out value="${emptyDescriptionVErr}"/>
                    </small>
                </div>

                <div class="row">
                    <div class="col">
                        <label for="price">Price</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <div class="input-group-text">$</div>
                            </div>
                            <input type="number"
                                   class="form-control ${not empty negativePriceVErr ? 'border border-danger' : ''}"
                                   id="price" name="price" value="${product.price}" required>
                            <small class="form-text text-danger">
                                <c:out value="${negativePriceVErr}"/>
                            </small>
                        </div>
                    </div>

                    <div class="col">
                        <label for="stock">Stock</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <div class="input-group-text">#</div>
                            </div>
                            <input type="number"
                                   class="form-control ${not empty negativeStockVErr ? 'border border-danger' : ''}"
                                   id="stock" name="stock" value="${product.stock}" required>
                            <small class="form-text text-danger">
                                <c:out value="${negativeStockVErr}"/>
                            </small>
                        </div>
                    </div>
                </div>


                <button type="submit" class="btn btn-${empty product ? 'secondary' : 'primary'} btn-block mt-4 mb-2"
                    ${empty product ? 'disabled' : ''}>Edit Product
                </button>
                <a href="CatalogueServlet" class="text-center d-block text-danger">Cancel</a>
            </form>
        </div>
    </c:otherwise>
</c:choose>

<jsp:include page="../templates/footer.jsp"/>
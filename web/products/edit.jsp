<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%--Request Scope EL Vars--%>
<%--@elvariable id="editProductErr" type="java.lang.String"--%>
<%--@elvariable id="product" type="uts.isd.model.Product"--%>
<%--@elvariable id="successEdit" type="java.lang.Boolean"--%>

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
            <c:if test="${not empty successEdit}">
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

            <div class="d-flex mb-2">
                <h1 class="mb-0 flex-grow-1">Edit Product #${product.ID}</h1>

                    <%--Delete form--%>
                <form class="form-inline mb-0" method="post" action="DeleteProductServlet"
                      onsubmit="return confirm('Are you sure you want to delete this product?');">
                    <input type="hidden" value="${product.ID}" name="ID">
                    <button type="submit" class="btn btn-sm btn-outline-${empty product ? 'secondary' : 'danger'}"
                        ${empty product ? 'disabled' : ''}>
                        Delete Product
                    </button>
                </form>
            </div>

            <form action="EditProductServlet" method="post" class="validate">
                <input type="hidden" name="ID" value="${product.ID}">

                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" class="form-control ${not empty emptyNameVErr ? 'border border-danger' : ''}"
                           name="name" id="name" value="${product.name}">
                    <small class="invalid-feedback d-block">
                        <c:out value="${emptyNameVErr}"/>
                    </small>
                </div>

                <div class="form-group">
                    <label for="category">Category</label>                   
                    <select class="form-control"
                            name="category" value="${product.category}">
                            <option ${product.category == "Actuators" ? "selected" : ""}>Actuators</option>
                            <option ${product.category == "Capacitors" ? "selected" : ""}>Capacitors</option>
                            <option ${product.category == "Sensors" ? "selected" : ""}>Sensors</option>
                            <option ${product.category == "Resistors" ? "selected" : ""}>Resistors</option>
                    </select> 
                </div>

                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea class="form-control ${not empty emptyDescriptionVErr ? 'border border-danger' : ''}"
                              id="description" name="description" rows="3">${product.description}</textarea>
                    <small class="invalid-feedback d-block">
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
                                   id="price" name="price" value="${product.price}" step="0.01" required>
                            <small class="invalid-feedback d-block">
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
                            <small class="invalid-feedback d-block">
                                <c:out value="${negativeStockVErr}"/>
                            </small>
                        </div>
                    </div>
                </div>


                <button type="submit" class="btn btn-${empty product ? 'secondary' : 'primary'} btn-block mt-4 mb-2"
                    ${empty product ? 'disabled' : ''}>
                    Edit Product
                </button>

                <a href="CatalogueServlet" class="text-center d-block text-danger">Cancel</a>
            </form>
        </div>
    </c:otherwise>
</c:choose>

<jsp:include page="../templates/footer.jsp"/>
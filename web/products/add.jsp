<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--Request Scope EL Vars--%>
<%--@elvariable id="addProductErr" type="java.lang.String"--%>
<%--@elvariable id="newProductID" type="java.lang.String"--%>

<%--Session Scope EL Vars--%>
<%--@elvariable id="user" type="uts.isd.model.Account"--%>

<head>
    <title>Add Product</title>
</head>
<jsp:include page="../templates/header.jsp"/>

<c:choose>
    <c:when test="${empty user || not user.staff}">
        <div class="alert alert-danger w-50 mx-auto" role="alert">
            <h4 class="alert-heading">Warning!</h4>
            <p>You aren't authorised to access this content. Only staff and admin are allowed to perform actions on this
                page.</p>
            <hr>
            <p class="mb-0">If you are a staff member, you need to log into your staff account
                <a class="alert-link" href="${pageContext.request.contextPath}/login.jsp">here</a>.
            </p>
        </div>
    </c:when>

    <c:otherwise>
        <div class="w mx-auto">
                <%--Success Message--%>
            <c:if test="${not empty newProductID}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <p><strong>Wow! Awesome!</strong> Product was successfully added.</p>
                    <hr>
                    <p class="mb-0">You can view the product <a href="ProductDetailsServlet?id=${newProductID}" class="alert-link">here</a>.</p>
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:if>

                <%--General Errors--%>
            <c:if test="${not empty addProductErr}">
                <div class="alert alert-danger my-4" role="alert">
                    <c:out value="${addProductErr}"/>
                </div>
            </c:if>

            <h1>Add New Product</h1>
            <form action="AddProductServlet" method="post" class="validate">
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" class="form-control ${not empty emptyNameVErr ? 'border border-danger' : ''}"
                           name="name" id="name">
                    <small class="form-text text-danger">
                        <c:out value="${emptyNameVErr}"/>
                    </small>
                </div>

                <div class="form-group">
                    <label for="category">Category</label>
                    <input type="text" class="form-control ${not empty emptyCategoryVErr ? 'border border-danger' : ''}"
                           id="category" name="category">
                    <small class="form-text text-danger">
                        <c:out value="${emptyCategoryVErr}"/>
                    </small>
                </div>

                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea class="form-control ${not empty emptyDescriptionVErr ? 'border border-danger' : ''}" id="description" name="description" rows="3"></textarea>
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
                            <input type="number" class="form-control ${not empty negativePriceVErr ? 'border border-danger' : ''}"
                                   id="price" name="price" required>
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
                            <input type="number" class="form-control ${not empty negativeStockVErr ? 'border border-danger' : ''}"
                                   id="stock" name="stock" required>
                            <small class="form-text text-danger">
                                <c:out value="${negativeStockVErr}"/>
                            </small>
                        </div>
                    </div>
                </div>


                <button type="submit" class="btn btn-primary btn-block mt-4 mb-2">Add Product</button>
            </form>
        </div>
    </c:otherwise>
</c:choose>

<jsp:include page="../templates/footer.jsp"/>
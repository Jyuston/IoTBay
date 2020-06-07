<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--Request Scope EL Vars--%>
<%--@elvariable id="product" type="uts.isd.model.Product"--%>

<%--Session Scope EL Vars--%>
<%--@elvariable id="user" type="uts.isd.model.Account"--%>

<head>
    <title>Product Details</title>
</head>
<jsp:include page="../templates/header.jsp"/>

<a href="CatalogueServlet" class="d-inline-block mb-2">
    <svg class="bi bi-arrow-left mb-1" width="1.5em" height="1.5em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
        <path fill-rule="evenodd" d="M5.854 4.646a.5.5 0 0 1 0 .708L3.207 8l2.647 2.646a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 0 1 .708 0z"></path>
        <path fill-rule="evenodd" d="M2.5 8a.5.5 0 0 1 .5-.5h10.5a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"></path>
    </svg>
    Back to Catalogue</a>

<c:choose>
    <c:when test="${empty product}">
        <h5 class="my-5 text-center display-5 font-weight-normal">
            <strong>Oh no...</strong>
            <br>
            That product doesn't exist 😔
        </h5>
    </c:when>

    <c:otherwise>
        <div class="row no-gutters mb-5">
            <aside class="col-md-6 rounded overflow-hidden">
                <svg class="card-img-top" style="text-anchor: middle" width="100%" height="100%"
                     xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false">
                    <title>Placeholder</title>
                    <rect width="100%" height="100%" fill="#868e96"></rect>
                    <text x="50%" y="50%" fill="#dee2e6" dy=".3em">Product Image</text>
                </svg>
            </aside>
            <main class="col-md-6">
                <article class="px-4">
                    <h2 class="title">${product.name}</h2>

                    <c:if test="${not empty user && user.staff}">
                        <a class="text-info" href="EditProductServlet?ID=${product.ID}">Edit Product Details</a>
                    </c:if>

                    <div class="rating-wrap my-3 text-warning">
                        <svg class="bi bi-star-fill" width="1em" height="1em" viewBox="0 0 16 16"
                             fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.283.95l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"></path>
                        </svg>
                        <svg class="bi bi-star-fill" width="1em" height="1em" viewBox="0 0 16 16"
                             fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.283.95l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"></path>
                        </svg>
                        <svg class="bi bi-star-fill" width="1em" height="1em" viewBox="0 0 16 16"
                             fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.283.95l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"></path>
                        </svg>
                        <svg class="bi bi-star-fill" width="1em" height="1em" viewBox="0 0 16 16"
                             fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.283.95l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"></path>
                        </svg>
                        <svg class="bi bi-star-half" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor"
                             xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M5.354 5.119L7.538.792A.516.516 0 0 1 8 .5c.183 0 .366.097.465.292l2.184 4.327 4.898.696A.537.537 0 0 1 16 6.32a.55.55 0 0 1-.17.445l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256a.519.519 0 0 1-.146.05c-.341.06-.668-.254-.6-.642l.83-4.73L.173 6.765a.55.55 0 0 1-.171-.403.59.59 0 0 1 .084-.302.513.513 0 0 1 .37-.245l4.898-.696zM8 12.027c.08 0 .16.018.232.056l3.686 1.894-.694-3.957a.564.564 0 0 1 .163-.505l2.906-2.77-4.052-.576a.525.525 0 0 1-.393-.288L8.002 2.223 8 2.226v9.8z"></path>
                        </svg>

                        <small class="label-rating text-muted">123 (fake) reviews</small>
                    </div>

                    <div class="mb-3">
                        <span class="price h4">$${product.price}</span>
                        <span class="text-muted">/ unit</span>
                    </div>

                    <p>${product.description}</p>


                    <dl class="row">
                        <dt class="col-sm-3">Product ID</dt>
                        <dd class="col-sm-9">#${product.ID}</dd>

                        <dt class="col-sm-3">Category</dt>
                        <dd class="col-sm-9">
                            <a href="CatalogueServlet?category=${product.category}">${product.category}</a>
                        </dd>

                        <dt class="col-sm-3">Available</dt>
                        <dd class="col-sm-9">
                            <c:choose>
                                <c:when test="${product.stock > 0}">
                                    <span class="text-${product.stock <= 20 ? 'warning' : 'success'}">${product.stock}
                                        left</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="text-danger">Out of stock</span>
                                </c:otherwise>
                            </c:choose>
                        </dd>
                    </dl>

                    <hr>

                    <!--TODO Ordering-->
                    <form class="form mb-0" method="post">
                        <div class="form-group" style="width: 30%">
                            <label>Quantity</label>
                            <div class="input-group mb-4">
                                <div class="input-group-prepend">
                                    <button class="btn btn-outline-dark" type="button"
                                            onclick="document.getElementById('quantity').stepUp(-1)">-
                                    </button>
                                </div>
                                <input type="number" id="quantity" value="1" min="0" required name="quantity"
                                       class="form-control border-dark text-center pl-4">
                                <div class="input-group-append">
                                    <button class="btn btn-outline-dark" type="button"
                                            onclick="document.getElementById('quantity').stepUp(1)">+
                                    </button>
                                </div>
                            </div>
                        </div>

                        <button class="btn btn-success mr-2">Buy now</button>
                        <button type="submit" class="btn btn-outline-primary"><span class="text">Add to cart</span>
                            <svg class="bi bi-cart-plus pb-1 ml-1" width="1.5rem" height="1.5rem" viewBox="0 0 16 16"
                                 fill="currentColor"
                                 xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M8.5 5a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1H8V5.5a.5.5 0 0 1 .5-.5z"></path>
                                <path fill-rule="evenodd" d="M8 7.5a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 0 1H9v1.5a.5.5 0 0 1-1 0v-2z"></path>
                                <path fill-rule="evenodd" d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm7 0a1 1 0 1 0 0 2 1 1 0 0 0 0-2z"></path>
                            </svg>
                        </button>
                    </form>
                </article>
            </main>
        </div>
    </c:otherwise>
</c:choose>

<jsp:include page="../templates/footer.jsp"/>
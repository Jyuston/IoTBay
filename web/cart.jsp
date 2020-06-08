<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--@elvariable id="cart" type="java.util.List<OrderLineItem>"--%>
<%--@elvariable id="lineItem" type="uts.isd.model.OrderLineItem"--%>

<head>
    <title>Cart</title>
</head>
<jsp:include page="templates/header.jsp"/>

<c:if test="${not empty param.failUpdate}">
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <p class="mb-0"><strong>Can't update cart! </strong>We don't have enough of that item left!</p>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
</c:if>

<c:choose>
    <c:when test="${empty cart}">
        <div class="text-center">
            <h2 class="mt-5 mb-1 display-5">Aww...</h2>
            <p class="mb-1">Your cart is empty 😔</p>
            <a href="products/CatalogueServlet">Lets add some items</a>
        </div>
    </c:when>

    <c:otherwise>
        <h1>Shopping Cart</h1>
        <form class="mb-0 inline-form" action="order/ClearCartServlet" method="post">
            <button type="submit" class="d-inline-block mb-2 float-right btn text-danger btn-link p-0">
                Clear Cart
                <svg class="bi bi-trash-fill mb-1" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7z"/>
                </svg>
            </button>
        </form>
        <a href="products/CatalogueServlet" class="d-inline-block mb-2">
            <svg class="bi bi-arrow-left mb-1" width="1.5em" height="1.5em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                <path fill-rule="evenodd" d="M5.854 4.646a.5.5 0 0 1 0 .708L3.207 8l2.647 2.646a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 0 1 .708 0z"></path>
                <path fill-rule="evenodd" d="M2.5 8a.5.5 0 0 1 .5-.5h10.5a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"></path>
            </svg>
            Continue Shopping
        </a>

        <div class="row mt-4">
            <h4 class="col-8">Item</h4>
            <h4 class="col-3 text-center" style="padding-right: 2.25rem">Quantity</h4>
            <h4 class="col text-right">Price</h4>
        </div>

        <c:set var="total" value="${0}"/>

        <c:forEach items="${cart}" var="lineItem" varStatus="count">
            <c:set var="total" value="${total + lineItem.sumPrice}"/>

            <div class="mb-3 py-3 border-bottom row" style="min-height: 180px">
                <aside class="col-2 rounded overflow-hidden">
                    <svg class="card-img-top" style="text-anchor: middle" width="100%" height="100%"
                         xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false">
                        <title>Placeholder</title>
                        <rect width="100%" height="100%" fill="#868e96"></rect>
                        <text x="50%" y="50%" fill="#dee2e6" dy=".3em">Image</text>
                    </svg>
                </aside>

                <div class="col d-flex flex-column align-items-start pr-0">
                    <div class="row mb-auto w-100 align-items-start">
                        <div class="col-8">
                            <h4 class="display-5 font-weight-bold p-0">${lineItem.product.name}
                                <small class="text-secondary" style="font-size: 0.9rem">#${lineItem.product.ID}</small>
                            </h4>
                            <a href="products/CatalogueServlet?category=${lineItem.product.category}">${lineItem.product.category}</a>

                            <div class="rating-wrap mb-2 text-warning">
                                <svg class="bi bi-star-fill" width="1em" height="1em" viewBox="0 0 16 16"
                                     fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path
                                        d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.283.95l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"></path>
                                </svg>
                                <svg class="bi bi-star-fill" width="1em" height="1em" viewBox="0 0 16 16"
                                     fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path
                                        d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.283.95l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"></path>
                                </svg>
                                <svg class="bi bi-star-fill" width="1em" height="1em" viewBox="0 0 16 16"
                                     fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path
                                        d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.283.95l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"></path>
                                </svg>
                                <svg class="bi bi-star-fill" width="1em" height="1em" viewBox="0 0 16 16"
                                     fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path
                                        d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.283.95l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"></path>
                                </svg>
                                <svg class="bi bi-star-half" width="1em" height="1em" viewBox="0 0 16 16"
                                     fill="currentColor"
                                     xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd"
                                          d="M5.354 5.119L7.538.792A.516.516 0 0 1 8 .5c.183 0 .366.097.465.292l2.184 4.327 4.898.696A.537.537 0 0 1 16 6.32a.55.55 0 0 1-.17.445l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256a.519.519 0 0 1-.146.05c-.341.06-.668-.254-.6-.642l.83-4.73L.173 6.765a.55.55 0 0 1-.171-.403.59.59 0 0 1 .084-.302.513.513 0 0 1 .37-.245l4.898-.696zM8 12.027c.08 0 .16.018.232.056l3.686 1.894-.694-3.957a.564.564 0 0 1 .163-.505l2.906-2.77-4.052-.576a.525.525 0 0 1-.393-.288L8.002 2.223 8 2.226v9.8z"></path>
                                </svg>

                                <small class="label-rating text-muted">123 (fake) reviews</small>
                            </div>

                            <c:choose>
                                <c:when test="${lineItem.product.stock > 0}">
                                    <p class="mb-0 text-${lineItem.product.stock <= 20 ? 'warning' : 'success'}">${lineItem.product.stock}
                                        left</p>
                                </c:when>
                                <c:otherwise>
                                    <p class="mb-0 text-danger">Out of stock</p>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <form class="col-2 text-center form mb-0" action="order/UpdateCartItemServlet" method="post">
                            <input type="hidden" name="ID" value="${lineItem.product.ID}">
                            <input class="text-center pl-3 rounded border w-50" type="number" name="quantity"
                                   value="${lineItem.quantity}" min="1"/>
                            <br>
                            <button type="submit" class="btn btn-link">Update</button>
                        </form>

                        <h3 class="col text-right display-5 font-weight-bold pr-0">
                            $<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${lineItem.sumPrice}"/>
                            <br>
                            <small class="text-secondary" style="font-size: 0.8rem">
                                $<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${lineItem.product.price}"/> / item</small>
                        </h3>
                    </div>

                    <form class="align-self-end mr-3 mb-0" action="order/RemoveFromCartServlet" method="post">
                        <input type="hidden" name="ID" value="${lineItem.product.ID}">
                        <button type="submit" class="btn btn-link text-danger p-0">Remove</button>
                    </form>
                </div>
            </div>
        </c:forEach>

        <div class="text-right mt-5">
            <h1>
                <small class="text-secondary" style="font-size: 0.6em">Total:</small> $<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${total}"/>
            </h1>
            <a href="checkout.jsp" class="btn btn-success btn-lg px-5 mt-2 mb-5">
                Checkout
                <svg class="bi bi-cart-check ml-1 pb-1" style="font-size: 1.5em;" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M11.354 5.646a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L8 8.293l2.646-2.647a.5.5 0 0 1 .708 0z"></path>
                    <path fill-rule="evenodd" d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm7 0a1 1 0 1 0 0 2 1 1 0 0 0 0-2z"></path>
                </svg>
            </a>
        </div>
    </c:otherwise>
</c:choose>

<jsp:include page="templates/footer.jsp"/>
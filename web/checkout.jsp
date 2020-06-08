<%--
  Created by IntelliJ IDEA.
  User: Kevin
  Date: 5/06/2020
  Time: 2:56 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%--@elvariable id="user" type="Account"--%>
<%--@elvariable id="cart" type="java.util.List<OrderLineItem>"--%>
<%--@elvariable id="lineItem" type="uts.isd.model.OrderLineItem"--%>

<head>
    <title>Checkout</title>
</head>
<jsp:include page="templates/header.jsp"/>

<div class="w mx-auto">
    <!--Delete-->
    <c:if test="${not empty successDelete}">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            <p class="mb-0">Payment Information deleted successfully!</p>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:if>

    <!--Anonymous User-->
    <c:if test="${not empty user && user.anonymous}">
        <div class="alert alert-info alert-dismissible fade show" role="alert">
            <p class="mb-0"><strong>NOTE: </strong>Ordering as an anonymous user.</p>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:if>

    <h1 class="font-weight-bold">Checkout</h1>
    <a href="cart.jsp" class="d-inline-block">
        <svg class="bi bi-arrow-left mb-1" width="1.5em" height="1.5em" viewBox="0 0 16 16" fill="currentColor"
             xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd"
                  d="M5.854 4.646a.5.5 0 0 1 0 .708L3.207 8l2.647 2.646a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 0 1 .708 0z"></path>
            <path fill-rule="evenodd" d="M2.5 8a.5.5 0 0 1 .5-.5h10.5a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"></path>
        </svg>
        Back to Cart
    </a>

    <!--Calculate Total-->
    <c:set var="total" value="${0}"/>
    <c:forEach items="${cart}" var="lineItem" varStatus="count">
        <c:set var="total" value="${total + lineItem.sumPrice}"/>
    </c:forEach>

    <h4 class="mt-2 mb-1">Details</h4>
    <div class="rounded border p-3 mt-2">
        <p class="d-flex">
            <strong class="mr-auto">Sub Total Inc GST:</strong>
            <span>$<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2"
                                     value="${total}"/></span>
        </p>
        <p class="d-flex">
            <strong class="mr-auto">Shipping Cost:</strong>
            <span class="text-right">$0.00 <small
                class="text-secondary"><br>Free shipping cause we're nice 😁</small></span>
        </p>
        <p class="d-flex">
            <strong class="mr-auto">Payment Method:</strong>
            <c:choose>
                <c:when test="${user.staff || empty user.paymentInfo.cardNumber}">
                    <span class="text-danger float-right">No Billing Information</span>
                </c:when>
                <c:otherwise>
                    <span class="float-right">${user.paymentInfo.cardNumber}</span>
                </c:otherwise>
            </c:choose>
        </p>
        <p class="d-flex">
            <strong class="mr-auto">Shipping To:</strong>
            <c:choose>
                <c:when test="${user.staff || empty user.address.addressLine1}">
                    <span class="text-danger float-right">No Shipping Address</span>
                </c:when>
                <c:otherwise>
                    <span class="float-right">${user.address.addressLine1}</span>
                </c:otherwise>
            </c:choose>
        </p>

        <!--Edit or Add Details Button-->
        <c:choose>
            <c:when test="${not empty user && user.customer && (empty user.paymentInfo.cardNumber && empty user.address.addressLine1)}">
                <!--User is logged in but doesn't have any info-->
                <a href="edit_payment_info.jsp" class="btn btn-outline-primary btn-block">Add Payment Info +</a>
            </c:when>
            <c:when test="${not empty user && user.customer && (not empty user.paymentInfo.cardNumber || not empty user.address.addressLine1)}">
                <!--If user is logged in but has SOME info-->
                <a href="edit_payment_info.jsp" class="btn btn-outline-primary btn-block">Edit Payment Info</a>
                <form class="form-inline mt-2 mb-0" action="DeletePaymentServlet" method="post">
                    <button type="submit" class="btn btn-outline-danger btn-block">Remove Payment Info</button>
                </form>
            </c:when>
            <c:when test="${empty user}">
                <!--If user is anonymous-->
                <a href="edit_payment_info.jsp" class="btn btn-outline-primary btn-block">Add Anonymous Payment Info +</a>
            </c:when>
        </c:choose>
    </div>

    <h1 class="text-right my-5">Total: $<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${total}"/></h1>

    <form action="order/PlaceOrderServlet" method="post">
        <c:choose>
            <c:when test="${user.staff}">
                <!--If user is staff-->
                <button type="submit" class="btn btn-success btn-block btn-lg mt-4 mb-3" disabled>
                    Purchase
                    <svg class="bi bi-check2-circle ml-1 pb-1" style="font-size: 1.5em" width="1em" height="1em"
                         viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd"
                              d="M15.354 2.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3-3a.5.5 0 1 1 .708-.708L8 9.293l6.646-6.647a.5.5 0 0 1 .708 0z"></path>
                        <path fill-rule="evenodd"
                              d="M8 2.5A5.5 5.5 0 1 0 13.5 8a.5.5 0 0 1 1 0 6.5 6.5 0 1 1-3.25-5.63.5.5 0 1 1-.5.865A5.472 5.472 0 0 0 8 2.5z"></path>
                    </svg>
                </button>
                <p class="text-danger text-center w-75 mx-auto">
                    <small>You need to logged in as a customer to purchase.<br> Please register a customer account
                        <a href="register.jsp" class="text-danger font-weight-bold">here</a>.
                    </small>
                </p>
            </c:when>
            <c:when test="${empty user.paymentInfo.cardNumber || empty user.address.addressLine1}">
                <!--If missing request payment info or address-->
                <button type="submit" class="btn btn-success btn-block  btn-lg mt-4 mb-3" disabled>
                    Purchase
                    <svg class="bi bi-check2-circle ml-1 pb-1" style="font-size: 1.5em" width="1em" height="1em"
                         viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd"
                              d="M15.354 2.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3-3a.5.5 0 1 1 .708-.708L8 9.293l6.646-6.647a.5.5 0 0 1 .708 0z"></path>
                        <path fill-rule="evenodd"
                              d="M8 2.5A5.5 5.5 0 1 0 13.5 8a.5.5 0 0 1 1 0 6.5 6.5 0 1 1-3.25-5.63.5.5 0 1 1-.5.865A5.472 5.472 0 0 0 8 2.5z"></path>
                    </svg>
                </button>
                <p class="text-danger text-center w-75 mx-auto">
                    <small>You need to have <strong>valid payment information</strong> & a <strong>shipping
                        address</strong> before you can purchase</small>
                </p>
            </c:when>
            <c:otherwise>
                <button type="submit" class="btn btn-success btn-block btn-lg mt-4 mb-3">
                    Purchase
                    <svg class="bi bi-check2-circle ml-1 pb-1" style="font-size: 1.5em" width="1em" height="1em"
                         viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd"
                              d="M15.354 2.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3-3a.5.5 0 1 1 .708-.708L8 9.293l6.646-6.647a.5.5 0 0 1 .708 0z"></path>
                        <path fill-rule="evenodd"
                              d="M8 2.5A5.5 5.5 0 1 0 13.5 8a.5.5 0 0 1 1 0 6.5 6.5 0 1 1-3.25-5.63.5.5 0 1 1-.5.865A5.472 5.472 0 0 0 8 2.5z"></path>
                    </svg>
                </button>
            </c:otherwise>
        </c:choose>
    </form>
</div>

<jsp:include page="templates/footer.jsp"/>

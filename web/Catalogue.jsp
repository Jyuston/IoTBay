<%@ page import="uts.isd.model.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>Search for a product</title>
</head>
<jsp:include page="templates/header.jsp"/>

<%
    Product product = (Product)request.getAttribute("product");
    String result = (String)request.getAttribute("result");

%>
<div class="row">
    <div class="col"></div>
    <div class="col-md-auto">
        <div class="alert alert-success" role="alert">
            <h4 class="alert-heading"> Cannot find product</h4>
            <a class="btn btn-primary btn-lg btn-block" href="main.jsp"> Enter Site </a>
        </div>
    </div>
    <div class="col"></div>
</div>

<form action="ProductServlet" method="post" class="max-w-sm">
    <h1>Search a product</h1>



    <div class="form-group">
        <label for="name">Product Name:</label>
        <input type="text" class="form-control" name="name" id="email" placeholder="Enter name"
              >
    </div>
    <div class="form-group">
        <label for="cat">Product Category: </label>
        <input type="text" class="form-control" id="cat" name="cat" placeholder="Cat" required>
    </div>

    <%--Used to tell if the form has been submitted within Scriplet tag above--%>
    <input type="hidden" name="submitted" value="true">

    <button type="submit" class="btn btn-primary btn-block mt-3 mb-2">Search</button>
              <br>
              <label hidden="true" id="resultLabel"> <%request.getAttribute("result");%></label>
  

    <a href="index.jsp" class="text-center d-block text-danger">Cancel</a>
</form>
<% } %>

<jsp:include page="templates/footer.jsp"/>
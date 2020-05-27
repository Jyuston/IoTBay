<%@page import="java.util.List"%>
<%@page import="uts.isd.model.Product"%>
<%@ page import="uts.isd.controller.ProductServlet.*" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search products from category</title>
    </head>
    
    <%
        Product product = (Product)request.getAttribute("product");
        
        
       
    
    %>
    
    <body>
        <h1>Search Movies</h1>
        <div name="search_part">
            
        
         <div align = "center" style="border:1px solid orange">
         <form action="search" method="post" >
                
                
                <table cellpadding="10">
                    <tr>
                        <td>
                            <label for="movieTitleInput">Title:</label>
                        </td>
                        <td>
                         <input type="text" class="form-control" name="movieTitleInput" id="movieTitleInput" placeholder="Movie Name" required>
                  
                        </td>
                    </tr>
                    <tr>
                        <td>
                             <label for="movieGenreInput">Genre:</label>
                        </td>
                        <td>
                               <input type="text" class="form-control" name="movieGenreInput" id="movieGenreInput" placeholder="Genre" required>
                
                        </td>
                    </tr>
                </table>
             
              <button type="submit" class="btn btn-primary">Search Movie</button>
              <br>
              <label hidden="true" id="resultLabel"> <%request.getAttribute("product");%></label>
    
         </form>
                
     <!--           
              <button class="btn btn-primary" type="submit" name = "backhome" onclick="location.href('category.jsp')">Back to Category </button>
   -->
         </div>
        <br><br><br>
        
        <%
         if(product !=null){
            
            request.setAttribute("result", "No Movie Found!");
               // out.println("MovieSearchResult:" + movieSearchResult.size());
               //response.sendRedirect("search.jsp");

               
               
               
        
        %>
        <div align = "center" >
            
        <%--  searchResult is created in MovieServet: searching from title or genre --%>
        <table style="border: 1px solid greenyellow" cellpadding="10">
           
                        <tr>
                            <th scope ="col">ID</th>
                            <th scope ="col">Title</th>
                            <th scope ="col">Release Year</th>
                            <th scope ="col">Genre</th>
                            <th scope ="col">Price</th>
                            <th scope ="col">Description</th>
                            <th scope ="col">Action</th>
                        </tr>
                    
 
                <tr>
                                <td><%= product.getID()%></td>
                                <td><%= product.getName()%> </td>
                               <td> <%= product.getStock()%></td>
                                <td><%= product.getCategory()%></td>
                                <td><%= product.getDescription()%></td>
                                <td><%= product.getPrice()%></td>

<!-- for use to add to cart:  -->
                                <td><a href="" >Add to Cart</a></td>

                                
                            </tr>
                        <%
            }
        
        
                            
                            %>
                            
             </table>                          
                                       
       </div>    
        
        <%
        }
        %>
        
        
        
        
       </div>
    </body>
    
    <%
        
    %>
</html>

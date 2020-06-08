package uts.isd.controller.order;

import uts.isd.controller.Validator;
import uts.isd.model.OrderLineItem;
import uts.isd.model.Product;
import uts.isd.model.dao.DAOException;
import uts.isd.model.dao.ProductDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.LinkedList;

public class AddToCartServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        HttpSession session = request.getSession();
        LinkedList<OrderLineItem> cart = (LinkedList<OrderLineItem>) session.getAttribute("cart");

        // Create validator for request
        Validator validator = new Validator(request);

        int ID = Integer.parseInt(request.getParameter("ID"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        try {
            Product product = ProductDAO.get(ID);

            // Check if user is trying to add more than available
            validator.validateCartUpdate(product, quantity);

            if (validator.failed()) {
                response.sendRedirect("../products/ProductDetailsServlet?ID=" + ID + "&failAdd=true");
                return;
            }

            OrderLineItem existingLineItem = productInCart(cart, product);
            if (existingLineItem != null) {
                int currentQuantity = existingLineItem.getQuantity();

                // Check if user is trying to add more than available
                validator.validateCartUpdate(product, currentQuantity + quantity);

                if (validator.failed()) {
                    response.sendRedirect("../products/ProductDetailsServlet?ID=" + ID + "&failAdd=true");
                    return;
                }

                existingLineItem.setQuantity(currentQuantity + quantity);
            } else {
                OrderLineItem lineItem = new OrderLineItem();

                lineItem.setProduct(product);
                lineItem.setQuantity(quantity);

                cart.add(lineItem);
            }

            session.setAttribute("cart", cart);

            response.sendRedirect("../products/ProductDetailsServlet?ID=" + ID + "&successAdd=true");
        } catch (DAOException err) {
            request.setAttribute("cartAddErr", err.getMessage());
            response.sendRedirect("../products/ProductDetailsServlet?ID=" + ID + "&error=true");
        } catch (SQLException err) {
            request.setAttribute("cartAddErr", err.getMessage());
            err.printStackTrace();
            response.sendRedirect("../products/ProductDetailsServlet?ID=" + ID + "&error=true");
        }
    }

    private OrderLineItem productInCart(LinkedList<OrderLineItem> cart, Product product) {
        for (OrderLineItem lineItem : cart) {
            if (lineItem.getProduct().getID() == product.getID()) {
                return lineItem;
            }
        }

        return null;
    }
}
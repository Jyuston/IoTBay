package uts.isd.controller.order;

import uts.isd.controller.Validator;
import uts.isd.model.OrderLineItem;
import uts.isd.model.Product;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;

public class UpdateCartItemServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        HttpSession session = request.getSession();
        LinkedList<OrderLineItem> cart = (LinkedList<OrderLineItem>) session.getAttribute("cart");

        Validator validator = new Validator(request);

        int ID = Integer.parseInt(request.getParameter("ID"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        if (validator.failed()) {
            response.sendRedirect("../products/ProductDetailsServlet?ID=" + ID + "&failAdd=true");
            return;
        }

        for (OrderLineItem lineItem : cart) {
            Product currentProduct = lineItem.getProduct();

            if (currentProduct.getID() == ID) {
                validator.validateCartUpdate(currentProduct, quantity);

                if (validator.failed()) {
                    response.sendRedirect("../cart.jsp?failUpdate=true");
                    return;
                } else lineItem.setQuantity(quantity);

            }
        }

        session.setAttribute("cart", cart);

        response.sendRedirect("../cart.jsp");
    }
}
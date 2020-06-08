package uts.isd.model.dao;

import uts.isd.model.Customer;
import uts.isd.model.Order;
import uts.isd.model.OrderLineItem;
import uts.isd.model.Product;

import java.sql.*;
import java.util.LinkedList;

public class OrderDAO {
    public static Order get(int orderID) throws SQLException, DAOException {
        String getStaffDataQuery = "SELECT * FROM ORDERS WHERE ID = " + orderID;
        PreparedStatement st = DAOUtils.prepareStatement(getStaffDataQuery, false);
        ResultSet orderRs = st.executeQuery();

        if (!orderRs.next())
            throw new DAOException("No Order with that ID exists.");

        return createOrderObject(orderRs);
    }

    public static LinkedList<Order> getAll() throws SQLException {
        LinkedList<Order> orders = new LinkedList<>();

        String getOrdersQuery = "SELECT * FROM ORDERS";
        PreparedStatement st = DAOUtils.prepareStatement(getOrdersQuery, false);
        ResultSet ordersRs = st.executeQuery();

        while (ordersRs.next()) {
            orders.add(createOrderObject(ordersRs));
        }

        return orders;
    }

    public static LinkedList<Order> getAllByCustomer(int customerID) throws SQLException {
        LinkedList<Order> orders = new LinkedList<>();

        String getOrdersQuery = "SELECT * FROM ORDERS WHERE CUSTOMER_ID = " + customerID;
        PreparedStatement st = DAOUtils.prepareStatement(getOrdersQuery, false);
        ResultSet ordersRs = st.executeQuery();

        while (ordersRs.next()) {
            orders.add(createOrderObject(ordersRs));
        }

        return orders;
    }

    public static LinkedList<Order> getAllByCustomer(Customer customer) throws SQLException {
        LinkedList<Order> orders = new LinkedList<>();

        String getOrdersQuery = "SELECT * FROM ORDERS WHERE CUSTOMER_ID = " + customer.getID();
        PreparedStatement st = DAOUtils.prepareStatement(getOrdersQuery, false);
        ResultSet ordersRs = st.executeQuery();

        while (ordersRs.next()) {
            orders.add(createOrderObjectWithCustomer(ordersRs, customer));
        }

        return orders;
    }

    public static LinkedList<Order> getAllByCustomer(int customerID, String startDate, String endDate) throws SQLException{
        LinkedList<Order> invoices = new LinkedList<>();

        Timestamp firstDate = Timestamp.valueOf(startDate+ " 00:00:00");
        Timestamp secondDate = Timestamp.valueOf(endDate + " 23:59:59");

        String getOrdersQuery =
                "SELECT * FROM ORDERS " +
                "WHERE CUSTOMER_ID = ? " +
                "AND ORDERED_ON >= ? " +
                "AND ORDERED_ON <= ?";

        PreparedStatement st = DAOUtils.prepareStatement(getOrdersQuery, false,
                customerID,
                firstDate,
                secondDate
        );
        ResultSet ordersRs = st.executeQuery();

        while (ordersRs.next()) {
            invoices.add(createOrderObject(ordersRs));
        }

        return invoices;
    }

    public static int save(Order order) throws SQLException, DAOException {
        LinkedList<OrderLineItem> orderedProducts = order.getOrderedProducts();

        double total = 0;
        for (OrderLineItem lineItem : orderedProducts) {
            total += lineItem.getSumPrice();
        }

        order.setTotal(total);
        order.setStatus("pending");
        order.setOrderedOn(new Timestamp(System.currentTimeMillis()));

        String orderInsertQuery =
                "INSERT INTO ORDERS (CUSTOMER_ID, ORDERED_ON, SHIPPING_ADDRESS, TOTAL, STATUS, TRACKING_ID) " +
                "VALUES (?, ?, ?, ?, ?, ?) ";

        PreparedStatement orderInsertSt = DAOUtils.prepareStatement(orderInsertQuery, true,
                order.getCustomer().getID(),
                order.getOrderedOn(),
                order.getShippingAddress(),
                order.getTotal(),
                order.getStatus(),
                order.getTrackingID()
        );

        int rowsChanged = orderInsertSt.executeUpdate();
        if (rowsChanged == 0)
            throw new DAOException("Failed to create order. Please try again.");

        int newOrderID = DAOUtils.getGeneratedID(orderInsertSt);
        order.setID(newOrderID);

        String lineItemInsertQuery =
                "INSERT INTO ORDER_LINE (ORDER_ID, PRODUCT_ID, QUANTITY_ORDERED, PRICE) " +
                "VALUES (?, ?, ?, ?)";

        for (OrderLineItem lineItem : orderedProducts) {
            Product currentProduct = lineItem.getProduct();

            PreparedStatement itemLineInsertSt = DAOUtils.prepareStatement(lineItemInsertQuery, false,
                    order.getID(),
                    currentProduct.getID(),
                    lineItem.getQuantity(),
                    lineItem.getSumPrice()
            );

            int rowsChanges = itemLineInsertSt.executeUpdate();
            if (rowsChanges == 0)
                throw new DAOException("Failed to add order list item. Please try again");

            currentProduct.setStock(currentProduct.getStock() - lineItem.getQuantity());
            ProductDAO.update(currentProduct);
        }

        return newOrderID;
    }

    /**
     * Update a single order from the database.
     *
     * @param order The instantiated order to update. Must have an ID.
     */
    public static void update(Order order) throws SQLException, DAOException {
        LinkedList<OrderLineItem> orderedProducts = order.getOrderedProducts();

        double newTotal = 0;
        for (OrderLineItem lineItem : orderedProducts) {
            newTotal += lineItem.getSumPrice();
        }

        String updateQuery =
                "UPDATE ORDERS SET STATUS = ?, SHIPPING_ADDRESS = ?, TOTAL = ?, TRACKING_ID = ? " +
                "WHERE ID = " + order.getID();

        PreparedStatement updateSt = DAOUtils.prepareStatement(updateQuery, false,
                order.getStatus(),
                order.getShippingAddress(),
                newTotal,
                order.getTrackingID()
        );

        int rowsChanged = updateSt.executeUpdate();
        if (rowsChanged == 0)
            throw new DAOException("Failed to update order details. Please try again.");
    }

    // Helpers
    private static Order createOrderObjectWithCustomer(ResultSet orderRs, Customer customer) throws SQLException, DAOException {
        Order order = new Order();

        order.setID(orderRs.getInt("ID"));
        order.setCustomer(customer);
        order.setOrderedOn(orderRs.getTimestamp("ORDERED_ON"));
        order.setTotal(orderRs.getDouble("TOTAL"));
        order.setTrackingID(orderRs.getString("TRACKING_ID"));
        order.setStatus(orderRs.getString("STATUS"));
        order.setShippingAddress(orderRs.getString("SHIPPING_ADDRESS"));

        LinkedList<OrderLineItem> orderedProducts = new LinkedList<>();

        String getProductsQuery = "SELECT * FROM ORDER_LINE OL WHERE OL.ORDER_ID = " + order.getID();
        PreparedStatement getProductsSt = DAOUtils.prepareStatement(getProductsQuery, false);
        ResultSet productsRs  = getProductsSt.executeQuery();

        while (productsRs.next()) {
            Product product = ProductDAO.get(productsRs.getInt("PRODUCT_ID"));
            int quantity = productsRs.getInt("QUANTITY_ORDERED");

            OrderLineItem lineItem = new OrderLineItem();

            lineItem.setProduct(product);
            lineItem.setQuantity(quantity);

            orderedProducts.add(lineItem);
        }

        order.setOrderedProducts(orderedProducts);

        return order;
    }

    public static Order createOrderObject(ResultSet orderRs) throws SQLException {
        Customer customer = CustomerDAO.get(orderRs.getInt("CUSTOMER_ID"));
        return createOrderObjectWithCustomer(orderRs, customer);
    }
}

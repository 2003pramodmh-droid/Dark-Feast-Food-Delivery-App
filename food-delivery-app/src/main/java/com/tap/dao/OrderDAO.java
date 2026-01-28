package com.tap.dao;

import com.tap.model.CartItem;
import com.tap.Util.*;
import java.sql.*;
import java.util.List;

/**
 * OrderDAO - Data Access Object for Order operations
 * Handles order placement and history
 */
public class OrderDAO {

    /**
     * Place an order
     * 
     * @param userId      User ID
     * @param cart        List of cart items
     * @param totalAmount Total order amount
     * @param location    Delivery location
     * @return Order ID if successful, -1 otherwise
     */
    public int placeOrder(int userId, List<CartItem> cart, double totalAmount, String location) {
        String orderSql = "INSERT INTO orders (user_id, total_amount, location) VALUES (?, ?, ?)";
        String itemSql = "INSERT INTO order_items (order_id, item_name, price, quantity) VALUES (?, ?, ?, ?)";

        Connection con = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false); // Start transaction

            // Insert order
            PreparedStatement orderPs = con.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);
            orderPs.setInt(1, userId);
            orderPs.setDouble(2, totalAmount);
            orderPs.setString(3, location);
            orderPs.executeUpdate();

            // Get generated order ID
            ResultSet rs = orderPs.getGeneratedKeys();
            int orderId = -1;
            if (rs.next()) {
                orderId = rs.getInt(1);

                // Insert order items
                PreparedStatement itemPs = con.prepareStatement(itemSql);
                for (CartItem item : cart) {
                    itemPs.setInt(1, orderId);
                    itemPs.setString(2, item.getName());
                    itemPs.setDouble(3, item.getPrice());
                    itemPs.setInt(4, item.getQuantity());
                    itemPs.addBatch();
                }
                itemPs.executeBatch();
            }

            con.commit(); // Commit transaction
            return orderId;

        } catch (Exception e) {
            e.printStackTrace();
            if (con != null) {
                try {
                    con.rollback(); // Rollback on error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

    /**
     * Get order count for a user
     * 
     * @param userId User ID
     * @return Number of orders
     */
    public int getOrderCount(int userId) {
        String sql = "SELECT COUNT(*) FROM orders WHERE user_id=?";
        try (Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}

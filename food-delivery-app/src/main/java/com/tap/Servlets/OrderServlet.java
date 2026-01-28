package com.tap.Servlets;

import java.io.IOException;
import java.util.List;

import com.tap.dao.OrderDAO;
import com.tap.model.CartItem;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * OrderServlet - Handles order placement
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private OrderDAO orderDAO = new OrderDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();

        // Check if user is logged in
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        // Get cart
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            resp.sendRedirect("home");
            return;
        }

        // Get location
        String location = req.getParameter("location");
        if (location == null || location.trim().isEmpty()) {
            resp.sendRedirect("location.jsp?error=Please enter delivery location");
            return;
        }

        // Calculate total
        double total = 0;
        for (CartItem item : cart) {
            total += item.getSubtotal();
        }

        // Place order
        int orderId = orderDAO.placeOrder(user.getId(), cart, total, location);

        if (orderId > 0) {
            // Clear cart
            session.removeAttribute("cart");

            // Set order confirmation details
            session.setAttribute("lastOrderId", orderId);
            session.setAttribute("lastOrderTotal", total);

            // Redirect to thank you page
            resp.sendRedirect("thankyou.jsp");
        } else {
            resp.sendRedirect("location.jsp?error=Order placement failed. Please try again.");
        }
    }
}

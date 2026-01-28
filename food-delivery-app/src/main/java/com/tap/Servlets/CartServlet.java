package com.tap.Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.RestaurantDAO;
import com.tap.model.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * CartServlet - Manages shopping cart operations
 * Uses session to store cart items
 */
@jakarta.servlet.annotation.WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private RestaurantDAO restaurantDAO = new RestaurantDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Just display the cart page
        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();

        // Check if user is logged in
        if (session.getAttribute("loggedInUser") == null) {
            resp.sendRedirect("login.jsp?error=Please login to add items to cart");
            return;
        }

        String action = req.getParameter("action");

        if ("add".equals(action)) {
            addToCart(req, session);
            // Redirect back to menu
            String resId = req.getParameter("resId");
            resp.sendRedirect("menu?id=" + resId);

        } else if ("update".equals(action)) {
            updateCart(req, session);
            resp.sendRedirect("cart");

        } else if ("remove".equals(action)) {
            removeFromCart(req, session);
            resp.sendRedirect("cart");

        } else if ("clear".equals(action)) {
            session.removeAttribute("cart");
            resp.sendRedirect("cart");

        } else {
            resp.sendRedirect("cart");
        }
    }

    /**
     * Add item to cart
     */
    private void addToCart(HttpServletRequest req, HttpSession session) {
        // Get cart from session (or create new one)
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        // Get item details from request
        int itemId = Integer.parseInt(req.getParameter("itemId"));
        String itemName = req.getParameter("itemName");
        double price = Double.parseDouble(req.getParameter("price"));
        int restaurantId = Integer.parseInt(req.getParameter("resId"));
        String restaurantName = req.getParameter("resName");

        // Check if item already exists in cart
        boolean itemExists = false;
        for (CartItem item : cart) {
            if (item.getItemId() == itemId) {
                item.setQuantity(item.getQuantity() + 1);
                itemExists = true;
                break;
            }
        }

        // If item doesn't exist, add new item
        if (!itemExists) {
            CartItem newItem = new CartItem(itemId, itemName, price, 1, restaurantId, restaurantName);
            cart.add(newItem);
        }

        // Save cart back to session
        session.setAttribute("cart", cart);
    }

    /**
     * Update item quantity in cart
     */
    private void updateCart(HttpServletRequest req, HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null)
            return;

        int itemId = Integer.parseInt(req.getParameter("itemId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        for (CartItem item : cart) {
            if (item.getItemId() == itemId) {
                if (quantity > 0) {
                    item.setQuantity(quantity);
                } else {
                    cart.remove(item);
                }
                break;
            }
        }

        session.setAttribute("cart", cart);
    }

    /**
     * Remove item from cart
     */
    private void removeFromCart(HttpServletRequest req, HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null)
            return;

        int itemId = Integer.parseInt(req.getParameter("itemId"));

        cart.removeIf(item -> item.getItemId() == itemId);

        session.setAttribute("cart", cart);
    }
}

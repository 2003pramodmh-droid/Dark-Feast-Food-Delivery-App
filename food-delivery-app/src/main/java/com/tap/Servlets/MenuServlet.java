package com.tap.Servlets;

import java.io.IOException;
import java.util.List;

import com.tap.dao.RestaurantDAO;
import com.tap.model.MenuItem;
import com.tap.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * MenuServlet - Displays menu items for a selected restaurant
 */
@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
    private RestaurantDAO restaurantDAO = new RestaurantDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Get restaurant ID from parameter
        String idParam = req.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            resp.sendRedirect("home");
            return;
        }

        int restaurantId = Integer.parseInt(idParam);

        // Fetch restaurant details
        Restaurant restaurant = restaurantDAO.getRestaurantById(restaurantId);

        // Fetch menu items for this restaurant
        List<MenuItem> menuItems = restaurantDAO.getMenuByRestaurantId(restaurantId);

        // Set as request attributes
        req.setAttribute("restaurant", restaurant);
        req.setAttribute("menuItems", menuItems);

        // Forward to JSP
        req.getRequestDispatcher("menu.jsp").forward(req, resp);
    }
}

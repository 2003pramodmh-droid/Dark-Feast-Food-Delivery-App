package com.tap.Servlets;

import java.io.IOException;
import java.util.List;

import com.tap.dao.RestaurantDAO;
import com.tap.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * HomeServlet - Displays all restaurants on the home page
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private RestaurantDAO restaurantDAO = new RestaurantDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Fetch all restaurants from database
        List<Restaurant> restaurants = restaurantDAO.getAllRestaurants();

        // Set as request attribute
        req.setAttribute("restaurants", restaurants);

        // Forward to JSP
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
}

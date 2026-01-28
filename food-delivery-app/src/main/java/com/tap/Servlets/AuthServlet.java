package com.tap.Servlets;

import java.io.IOException;

import com.tap.dao.UserDAO;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * AuthServlet - Handles user authentication (Login & Registration)
 */
@jakarta.servlet.annotation.WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("register".equals(action)) {
            handleRegistration(req, resp);
        } else if ("login".equals(action)) {
            handleLogin(req, resp);
        } else {
            resp.sendRedirect("login.jsp");
        }
    }

    /**
     * Handle user registration
     */
    private void handleRegistration(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String address = req.getParameter("address");

        // Validate inputs
        if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            resp.sendRedirect("register.jsp?error=Please fill all required fields");
            return;
        }

        // Check if username exists
        if (userDAO.usernameExists(username)) {
            resp.sendRedirect("register.jsp?error=Username already exists");
            return;
        }

        // Create user object
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // In production, hash this!
        user.setEmail(email);
        user.setAddress(address);

        // Register user
        if (userDAO.register(user)) {
            resp.sendRedirect("login.jsp?msg=Registration successful! Please login.");
        } else {
            resp.sendRedirect("register.jsp?error=Registration failed. Please try again.");
        }
    }

    /**
     * Handle user login
     */
    private void handleLogin(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Validate inputs
        if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            resp.sendRedirect("login.jsp?error=Please enter both username and password");
            return;
        }

        // Authenticate user
        User user = userDAO.login(username, password);

        if (user != null) {
            // Create session
            HttpSession session = req.getSession();
            session.setAttribute("loggedInUser", user);
            session.setAttribute("userId", user.getId());
            session.setAttribute("username", user.getUsername());

            // Redirect to home
            resp.sendRedirect("home");
        } else {
            resp.sendRedirect("login.jsp?error=Invalid username or password");
        }
    }
}

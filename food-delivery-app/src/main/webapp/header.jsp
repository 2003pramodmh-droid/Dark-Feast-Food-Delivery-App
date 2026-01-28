<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="com.tap.model.User" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="css/style.css">
            <title>Dark Feast Delivery - Fantasy Food at Your Doorstep</title>
        </head>

        <body>
            <header>
                <div class="logo">⚔ Feast of Shadows</div>
                <nav>
                    <a href="home">🏰 Home</a>
                    <% java.util.List cart=(java.util.List) session.getAttribute("cart"); int cartSize=(cart !=null) ?
                        cart.size() : 0; %>
                        <a href="cart">
                            🛒 Cart
                            <% if(cartSize> 0) { %>
                                <span class="cart-badge">
                                    <%= cartSize %>
                                </span>
                                <% } %>
                        </a>
                        <% User loggedInUser=(User) session.getAttribute("loggedInUser"); if(loggedInUser==null) { %>
                            <a href="login.jsp">🔐 Login</a>
                            <% } else { %>
                                <a href="#" style="color: var(--accent-cyan);">👤 <%= loggedInUser.getUsername() %></a>
                                <a href="logout">🚪 Logout</a>
                                <% } %>
                </nav>
            </header>
            <div class="container">
<%@ include file="header.jsp" %>
    <%@ page import="com.tap.model.MenuItem, com.tap.model.Restaurant, java.util.List" %>

        <% Restaurant restaurant=(Restaurant) request.getAttribute("restaurant"); List<MenuItem> menuItems = (List
            <MenuItem>) request.getAttribute("menuItems");

            if(restaurant == null) {
            response.sendRedirect("home");
            return;
            }
            %>

            <div style="text-align: center; margin-bottom: 2rem;">
                <h1><%= restaurant.getName() %>
                </h1>
                <div style="font-size: 1.3rem;">
                    <span class="rating" style="justify-content: center;">
                        <%= restaurant.getRating() %>
                    </span>
                </div>
                <p class="desc" style="max-width: 600px; margin: 1rem auto;">
                    <%= restaurant.getDescription() %>
                </p>
            </div>

            <h2 style="text-align: center; margin: 2rem 0;">Our Magical Menu </h2>

            <% if(menuItems !=null && !menuItems.isEmpty()) { %>
                <div class="card-grid">
                    <% for(MenuItem item : menuItems) { %>
                        <div class="card">
                            <h2>
                                <%= item.getName() %>
                            </h2>
                            <div class="rating">
                                <%= item.getRating() %>
                            </div>
                            <div class="price">Rs.<%= String.format("%.2f", item.getPrice()) %>
                            </div>
                            <p class="desc">
                                <%= item.getDescription() %>
                            </p>

                            <form action="cart" method="post" style="margin:0;">
                                <input type="hidden" name="action" value="add">
                                <input type="hidden" name="itemId" value="<%= item.getId() %>">
                                <input type="hidden" name="itemName" value="<%= item.getName() %>">
                                <input type="hidden" name="price" value="<%= item.getPrice() %>">
                                <input type="hidden" name="resId" value="<%= restaurant.getId() %>">
                                <input type="hidden" name="resName" value="<%= restaurant.getName() %>">
                                <button type="submit" class="btn btn-buy">Add to Cart</button>
                            </form>
                        </div>
                        <% } %>
                </div>
                <% } else { %>
                    <div class="text-center mt-2">
                        <p style="color: var(--text-secondary); font-size: 1.2rem;">
                            No menu items available for this restaurant.
                        </p>
                    </div>
                    <% } %>

                        <div class="text-center mt-2">
                            <a href="home" class="btn">Back to Restaurants</a>
                        </div>

                        <%@ include file="footer.jsp" %>
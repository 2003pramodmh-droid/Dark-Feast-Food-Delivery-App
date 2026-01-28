<%@ include file="header.jsp" %>
    <%@ page import="com.tap.model.Restaurant, java.util.List" %>

        <% List<Restaurant> restaurants = (List<Restaurant>) request.getAttribute("restaurants"); %>

                <h1>Explore Our Mystical Realms of Taste</h1>

                <% if(restaurants !=null && !restaurants.isEmpty()) { %>
                    <div class="card-grid">
                        <% for(Restaurant r : restaurants) { %>
                            <div class="card">
                                <h2>
                                    <%= r.getName() %>
                                </h2>
                                <div class="rating">
                                    <%= r.getRating() %>
                                </div>
                                <p class="desc">
                                    <%= r.getDescription() %>
                                </p>
                                <a href="menu?id=<%= r.getId() %>" class="btn btn-buy">Enter This Realm</a>
                            </div>
                            <% } %>
                    </div>
                    <% } else { %>
                        <div class="text-center mt-2">
                            <p style="color: var(--text-secondary); font-size: 1.2rem;">
                                No restaurants available at the moment. The kitchens are being prepared...
                            </p>
                        </div>
                        <% } %>

                            <%@ include file="footer.jsp" %>
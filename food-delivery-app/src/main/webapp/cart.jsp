<%@ include file="header.jsp" %>
    <%@ page import="com.tap.model.CartItem, java.util.List" %>

        <h1>Your Shopping Cart</h1>

        <% List<CartItem> carts = (List<CartItem>) session.getAttribute("cart");

                if(cart == null || cart.isEmpty()) {
                %>
                <div class="form-card" style="text-align: center; padding: 3rem;">
                    <div style="font-size: 5rem; margin-bottom: 1rem;"></div>
                    <h2>Your cart is empty!</h2>
                    <p class="desc" style="margin: 1rem 0;">Time to fill your satchel with delicious provisions.</p>
                    <a href="home" class="btn btn-primary" style="margin-top: 1rem;">Explore Restaurants</a>
                </div>
                <% } else { double total=0; %>
                    <div style="max-width: 900px; margin: 0 auto;">
                        <table>
                            <thead>
                                <tr>
                                    <th>Item</th>
                                    <th>Restaurant</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Subtotal</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for(CartItem item : carts) { total +=item.getSubtotal(); %>
                                    <tr>
                                        <td><strong>
                                                <%= item.getName() %>
                                            </strong></td>
                                        <td style="color: var(--text-secondary);">
                                            <%= item.getRestaurantName() %>
                                        </td>
                                        <td style="color: var(--accent-cyan);">Rs.<%= String.format("%.2f",
                                                item.getPrice()) %>
                                        </td>
                                        <td>
                                            <div style="display: flex; gap: 0.5rem; align-items: center;">
                                                <button
                                                    onclick="updateQuantity(<%= item.getItemId() %>, <%= item.getQuantity() - 1 %>)"
                                                    class="btn" style="padding: 0.3rem 0.8rem;">-</button>
                                                <span style="font-weight: bold;">
                                                    <%= item.getQuantity() %>
                                                </span>
                                                <button
                                                    onclick="updateQuantity(<%= item.getItemId() %>, <%= item.getQuantity() + 1 %>)"
                                                    class="btn" style="padding: 0.3rem 0.8rem;">+</button>
                                            </div>
                                        </td>
                                        <td style="color: var(--accent-gold); font-weight: bold;">
                                            Rs.<%= String.format("%.2f", item.getSubtotal()) %>
                                        </td>
                                        <td>
                                            <form action="cart" method="post" style="margin: 0;">
                                                <input type="hidden" name="action" value="remove">
                                                <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                                                <button type="submit" class="btn"
                                                    style="padding: 0.5rem 1rem; background: var(--accent-red); color: white; border: none;">
                                                
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                    <% } %>
                            </tbody>
                        </table>

                        <div class="cart-total">
                            <h2>Total: Rs.<%= String.format("%.2f", total) %>
                            </h2>
                        </div>

                        <div class="flex-center mt-2">
                            <a href="home" class="btn">Continue Shopping</a>
                            <a href="location.jsp?total=<%= total %>" class="btn btn-primary">
                                Proceed to Checkout
                            </a>
                            <form action="cart" method="post" style="margin: 0;">
                                <input type="hidden" name="action" value="clear">
                                <button type="submit" class="btn"
                                    style="background: var(--accent-red); color: white; border: none;">
                                    Clear Cart
                                </button>
                            </form>
                        </div>
                    </div>

                    <script>
                        function updateQuantity(itemId, newQuantity) {
                            if (newQuantity < 1) {
                                if (!confirm('Remove this item from cart?')) return;
                            }

                            const form = document.createElement('form');
                            form.method = 'POST';
                            form.action = 'cart';

                            const actionInput = document.createElement('input');
                            actionInput.type = 'hidden';
                            actionInput.name = 'action';
                            actionInput.value = 'update';

                            const itemIdInput = document.createElement('input');
                            itemIdInput.type = 'hidden';
                            itemIdInput.name = 'itemId';
                            itemIdInput.value = itemId;

                            const quantityInput = document.createElement('input');
                            quantityInput.type = 'hidden';
                            quantityInput.name = 'quantity';
                            quantityInput.value = newQuantity;

                            form.appendChild(actionInput);
                            form.appendChild(itemIdInput);
                            form.appendChild(quantityInput);

                            document.body.appendChild(form);
                            form.submit();
                        }
                    </script>
                    <% } %>

                        <%@ include file="footer.jsp" %>